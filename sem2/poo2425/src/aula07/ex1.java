package aula07;
import java.lang.Math;

public class ex1 {
    public class Forma{
        protected double area;
        protected double perimetro;
        private String cor;

        public double getArea(){
            return area;
        } 
        public double getPerimeter(){
            return perimetro;
        }
        public String getCor(){
            return cor;
        }
        @Override
        public boolean equals(Object obj){
            if(this == obj){
                return true;
            }
            if(obj == null || this.getClass()!= obj.getClass()){
                return false;
            }
            Forma object = (Forma) obj;
            if(this.cor == object.getCor() && this.area == object.getArea() && this.perimetro == object.getPerimeter()){
                return true;
            }
            else{
                return false;
            }
    }
    public class Circulo extends Forma{
        public Circulo(double raio, String cor){
            super();
            this.area = Math.PI*Math.pow(raio, 2);
            this.perimetro = 2*Math.PI*raio;
        }


        }


    }
    public class Triangulo extends Forma{
        public Triangulo(double lado1, double lado2, double lado3, String cor){
            super();
            this.perimetro = lado1 + lado2 + lado3;
            double s = perimetro/2;
            this.area = Math.sqrt(s*(s-lado1)*(s-lado2)*(s-lado3));
        }

    }
    public class Retangulo extends Forma{
        public Retangulo(double l1, double l2, String cor){
            super();
            this.area = l1*l2;
            this.perimetro = 2*(l1+l2);
        }

    }
    
}
