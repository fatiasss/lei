//
// Algoritmos e Estruturas de Dados - 2024/2025
//
// J. Madeira - April/May 2022
//
// COMPLETE the code, according to Square.h
//

#include "Square.h"

#include <cassert>
#include <iostream>
#include <string>

#include "Point.h"
#include "Rectangle.h"

Square::Square(void) : Rectangle(Point(0,0), "black", 1, 1) {
  // Rectangle of edge=1 and centered at (0,0)
}

Square::Square(Point center, const std::string& color, double length) : Rectangle(center, color, length>0? length : 1.0, length>0? length : 1.0) {
  // Ensure that the width and height are larger than 0.0    
}


Square::Square(double x, double y, const std::string& color, double length) : Rectangle(Point(x,y), color, length>0? length : 1.0, length>0? length : 1.0) {
  // Ensure that the width and height are larger than 0.0
                    
}

double Square::GetEdgeLength(void) const { return Rectangle::GetHeight(); }
void Square::SetEdgeLength(double length) {
  // Ensure that the height is larger than 0.0
  if(length<=0) return;
  Rectangle::SetHeight(length);
  Rectangle::SetWidth(length);
}


std::string Square::GetClassName(void) const { return "Square"; }

std::ostream& operator<<(std::ostream& os, const Square& obj) {
  os << "Center: " << obj.GetCenter() << std::endl;
  os << "Color: " << obj.GetColor() << std::endl;
  os << "Edge Length = " << obj.GetHeight() << std::endl;
  return os;

}

std::istream& operator>>(std::istream& is, Square& obj) {
  double center_x;
  double center_y;
  std::string colour;
  double edge;

  std::cout << "Center X = ";
  is >> center_x;
  std::cout << "Center Y = ";
  is >> center_y;
  std::cout << "Colour = ";
  is >> colour;
  std::cout << "Edge Length = ";
  is >> edge;

  obj.SetCenter(Point(center_x, center_y));
  obj.SetColor(colour);
  obj.SetEdgeLength(edge);

  return is;
}
