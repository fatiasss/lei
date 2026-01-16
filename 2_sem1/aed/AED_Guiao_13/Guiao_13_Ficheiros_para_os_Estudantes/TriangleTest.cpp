//
// Algoritmos e Estruturas de Dados - 2024/2025
//
// J. Madeira - May 2022
//

#include <iostream>

#include "Point.h"
#include "Triangle.h"

int main(void) {
  Triangle t1;
  std::cout << t1 << std::endl;

  Triangle t2(0.0, 0.0, "yellow", 1.0, 2.0, 1.0); 
  std::cout << t2 << std::endl;

  Triangle t3(1.0, 0.0, "red", 3.0, 4.0, 0.9);
  std::cout << t3 << std::endl;

  Point p1(2, 2);
  Triangle t4(p1, "blue", 1.0, 1.5, 1.0), t5;
  std::cout << "Insert Triangle Info:" << std::endl;
  std::cin >> t5;
  std::cout << std::endl;

  std::cout << "t4: " << t4 << std::endl << "t5: " << t5 << std::endl;
  std::cout << "Area: t4 = " << t4.Area() << "; t5 = " << t5.Area() << std::endl;
  std::cout << "Perimeter: t4 = " << t4.Perimeter() << "; t5 = " << t5.Perimeter() << std::endl;
  std::cout << std::endl;

  Triangle t6(t2);
  Triangle t7 = t6;
  std::cout << "t6: " << t6 << std::endl << "t7: " << t7 << std::endl;

  return 0;
}