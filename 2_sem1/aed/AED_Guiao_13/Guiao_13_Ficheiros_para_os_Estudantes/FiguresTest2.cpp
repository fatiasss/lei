//
// Algoritmos e Estruturas de Dados - 2024/2025
//
// J. Madeira - May 2022
//

#include <iomanip>
#include <iostream>
#include <vector>
#include <random>

#include "Circle.h"
#include "Figure.h"
#include "Rectangle.h"
#include "Square.h"

enum Shapes {SQUARE, RECTANGLE, CIRCLE};

int main(void) {
  std::vector<Figure*> figures;
  Square square_1;
  Rectangle rect_1;
  Circle circle_1;
  srand(time(NULL));
  for(int i=0; i<5; i++){
    int random =std::rand()% 3;
    switch ((Shapes)random)
    {
    case SQUARE:
      std::cout << square_1;
      std::cout << std::endl;
      figures.push_back(&square_1);
      break;
    
    case RECTANGLE:
      std::cout << rect_1;
      std::cout << std::endl;
      figures.push_back(&rect_1);
      break;
    
    case CIRCLE:
      std::cout << circle_1;
      std::cout << std::endl;
      figures.push_back(&circle_1);
      break;
    
    default:
      break;
    }
  }



  std::cout << std::endl;
  for (auto ptr : figures) {
    std::cout << ptr->GetClassName() << " - Area = " << ptr->Area()
              << " - Perimeter = " << ptr->Perimeter() << std::endl;
  }

  return 0;
}