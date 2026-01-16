
#include "Triangle.h"

#include <cassert>
#include <iostream>
#include <string>
#include <cmath>


#include "Point.h"

Triangle::Triangle(void) : Figure(Point(0,0), "black"), side1_(1), side2_(1), angle_(90)  {
  // Rectangle of width=1 and height=1 and centered at (0,0)
}

Triangle::Triangle(Point center, const std::string& color, double side1,
            double side2, double angle) : Figure(center, color), side1_(side1>0 ? side1 : 1.0), side2_(side2>0 ? side2 : 1.0), angle_(angle) {
  // Ensure that the width and height are larger than 0.0
  assert(side1 < side2*sin(angle));
}

Triangle::Triangle(double x, double y, const std::string& color, double side1,
            double side2, double angle) : Figure(Point(x,y), color), side1_(side1>0 ? side1 : 1.0), side2_(side2>0 ? side2 : 1.0), angle_(angle) {
  // Ensure that the width and height are larger than 0.0height_*width_;
    assert(side1 < side2*sin(angle));
}

double Triangle::GetSide1(void) const { return side1_; }
void Triangle::SetSide1(double length) {
  // Ensure that the height is larger than 0.0
  if(length<=0) return;
  side1_=length;
}

double Triangle::GetSide2(void) const { return side2_; }
void Triangle::SetSide2(double length) {
  // Ensure that the width is larger than 0.0
  if(length<=0) return;
  side2_=length;
}

double Triangle::GetAngle(void) const { return angle_; }
void Triangle::SetAngle(double angle) {
  // Ensure that the width is larger than 0.0
  if(angle<=0) return;
  angle_=angle;
}


std::string Triangle::GetClassName(void) const { return "Triangle"; }

double Triangle::Area(void) const {
  return 0.5*side1_*side2_*sin(angle_);
}

double Triangle::Perimeter(void) const {
  return side1_ + side2_ + sqrt(pow(side1_,2) + pow(side2_,2) -2*side1_*side2_*cos(angle_));
}

std::ostream& operator<<(std::ostream& os, const Triangle& obj) {
  os << "Center: " << obj.GetCenter() << std::endl;
  os << "Color: " << obj.GetColor() << std::endl;
  os << "Side1 = " << obj.GetSide1() << std::endl;
  os << "Side2 = " << obj.GetSide2() << std::endl;
  os << "Angle = " << obj.GetAngle() << std::endl;
  return os;

}

std::istream& operator>>(std::istream& is, Triangle& obj) {
  double center_x;
  double center_y;
  std::string colour;
  double side1;
  double side2;
  double angle;

  std::cout << "Center X = ";
  is >> center_x;
  std::cout << "Center Y = ";
  is >> center_y;
  std::cout << "Colour = ";
  is >> colour;
  std::cout << "Side1 = ";
  is >> side1;
  std::cout << "Side2 = ";
  is >> side2;
  std::cout << "Angle = ";
  is >> angle;

  obj.SetCenter(Point(center_x, center_y));
  obj.SetColor(colour);
  obj.SetSide1(side1);
  obj.SetSide2(side2);
  obj.SetAngle(angle);

  return is;
}