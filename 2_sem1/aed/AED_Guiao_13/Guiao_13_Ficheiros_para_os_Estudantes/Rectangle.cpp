//
// Algoritmos e Estruturas de Dados - 2024/2025
//
// J. Madeira - April/May 2022
//
// COMPLETE the code, according to Rectangle.h
//

#include "Rectangle.h"

#include <cassert>
#include <iostream>
#include <string>

#include "Point.h"

Rectangle::Rectangle(void) : Figure(Point(0,0), "black"), width_(1), height_(1)  {
  // Rectangle of width=1 and height=1 and centered at (0,0)
}

Rectangle::Rectangle(Point center, const std::string& color, double width,
                     double height) : Figure(center, color), width_(width>0 ? width : 1.0), height_(height>0 ? height : 1.0) {
  // Ensure that the width and height are larger than 0.0
}

Rectangle::Rectangle(double x, double y, const std::string& color, double width,
                     double height) : Figure(Point(x,y), color), width_(width>0 ? width : 1.0), height_(height>0 ? height : 1.0) {
  // Ensure that the width and height are larger than 0.0
}

double Rectangle::GetHeight(void) const { return height_; }
void Rectangle::SetHeight(double length) {
  // Ensure that the height is larger than 0.0
  if(length<=0) return;
  height_=length;
}

double Rectangle::GetWidth(void) const { return width_; }
void Rectangle::SetWidth(double length) {
  // Ensure that the width is larger than 0.0
  if(length<=0) return;
  width_=length;
}


std::string Rectangle::GetClassName(void) const { return "Rectangle"; }

double Rectangle::Area(void) const {
  return height_*width_;
}

double Rectangle::Perimeter(void) const {
  return 2*(height_+width_);
}

std::ostream& operator<<(std::ostream& os, const Rectangle& obj) {
  os << "Center: " << obj.GetCenter() << std::endl;
  os << "Color: " << obj.GetColor() << std::endl;
  os << "Height = " << obj.GetHeight() << std::endl;
  os << "Width = " << obj.GetWidth() << std::endl;
  return os;

}

std::istream& operator>>(std::istream& is, Rectangle& obj) {
  double center_x;
  double center_y;
  std::string colour;
  double height;
  double width;

  std::cout << "Center X = ";
  is >> center_x;
  std::cout << "Center Y = ";
  is >> center_y;
  std::cout << "Colour = ";
  is >> colour;
  std::cout << "Height = ";
  is >> height;
  std::cout << "Width = ";
  is >> width;

  obj.SetCenter(Point(center_x, center_y));
  obj.SetColor(colour);
  obj.SetHeight(height);
  obj.SetWidth(width);

  return is;
}