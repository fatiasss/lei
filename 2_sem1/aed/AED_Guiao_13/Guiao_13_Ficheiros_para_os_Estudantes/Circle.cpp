//
// Algoritmos e Estruturas de Dados - 2024/2025
//
// J. Madeira - April/May 2022, December 2024
//
// COMPLETE the code, according to Circle.h
//

#include "Circle.h"

#define _USE_MATH_DEFINES

#include <cassert>
#include <cmath>
#include <iostream>
#include <string>

#include "Figure.h"
#include "Point.h"

Circle::Circle(void) : Figure(Point(0.0, 0.0), ("black")), radius_(1)  {}

Circle::Circle(Point center, const std::string& color, double length) : Figure(center, color), radius_(length>0 ? length : 1.0) {
  // Ensure that the radius is larger than 0.
}

Circle::Circle(double x, double y, const std::string& color, double length) : Figure(Point(x,y), color), radius_(length>0 ? length : 1.0) {
  // Ensure that the radius is larger than 0.0
}

double Circle::GetRadius(void) const { return radius_; }
void Circle::SetRadius(double length) {
  // Ensure that the radius is larger than 0.0
  if(length<=0) return;
  radius_=length;
}

std::string Circle::GetClassName(void) const { return "Circle"; }

double Circle::Area(void) const {
  return M_PI*pow(radius_, 2);
}

double Circle::Perimeter(void) const {
  return 2*M_PI*radius_;
}

bool Circle::Intersects(const Circle& c) const {
  // dist(C1,C2) <= r1 + r2
  // if dist(C1,C2) == r1 + r2, then circles touch at a single point

  double distance_between_centers = GetCenter().DistanceTo(c.GetCenter());

  double sum_of_radii = radius_ + c.radius_;

  return (distance_between_centers < sum_of_radii);
}

std::ostream& operator<<(std::ostream& os, const Circle& obj) {
  os << "Center: " << obj.GetCenter() << std::endl;
  os << "Color: " << obj.GetColor() << std::endl;
  os << "Radius = " << obj.radius_ << std::endl;
  return os;
}

std::istream& operator>>(std::istream& is, Circle& obj) {
  double center_x;
  double center_y;
  std::string colour;
  double radius;

  std::cout << "Center X = ";
  is >> center_x;
  std::cout << "Center Y = ";
  is >> center_y;
  std::cout << "Colour = ";
  is >> colour;
  std::cout << "Radius = ";
  is >> radius;

  obj.SetCenter(Point(center_x, center_y));
  obj.SetColor(colour);
  obj.SetRadius(radius);

  return is;
}
