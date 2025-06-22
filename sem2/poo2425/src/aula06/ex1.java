package aula06;

import myutils.DateYMD;

public class ex1 {
    public static class Pessoa{
        private String nome;
        private int cc;
        private DateYMD nasc;
        public Pessoa(String nome, int cc, DateYMD nasc){
            this.nome = nome;
            this.cc = cc;
            this.nasc = nasc;
        }
        public String getNome(){
            return nome;
        }
        public int getCC(){
            return cc;
        }
        public DateYMD getNasc(){
            return nasc;
        }
        public String toString(){
            return "Pessoa: " + nome + " CC: " + cc + " Nascimento: " + nasc.toString();
        }
    }
    public static class Aluno extends Pessoa{
        private int nmec;
        private DateYMD mat;
        static int lastnmec = 99;
        public Aluno(String nome, int cc, DateYMD nasc, DateYMD mat){
            super(nome, cc, nasc);
            lastnmec++;
            this.nmec=lastnmec;
            this.mat=mat;
        }
        public int getNMec(){
            return nmec;
        }
        public DateYMD getMat(){
            return mat;
        }
        @Override
        public String toString(){
            return "Aluno: " + super.getNome() + " CC: " + super.getCC() + " Nascimento: " + super.getNasc().toString() + " Nmec: " + this.getNMec() + " Matricula: " + this.getMat();
        }

    }
    public static class Professor extends Pessoa{
        private String position;
        private String departamento;
        public Professor(String nome, int cc, DateYMD nasc, String position, String departamento){
            super(nome, cc, nasc);
            this.position = position;
            this.departamento = departamento;
        }
        public String getPosition(){
            return position;
        }
        public String getDep(){
            return departamento;
        }
        public String getNome(){
            return super.getNome();
        }


    }
    public static class Bolseiro extends Aluno{
        private Professor orientador;
        private double bolsa;
        public Bolseiro(String nome, int cc, DateYMD nasc, DateYMD mat, Professor orientador, double bolsa){
            super(nome, cc, nasc, mat);
            this.orientador = orientador;
            this.bolsa = bolsa;
        }
        public Professor getOrientador() {
            return orientador;
        }
        public double getBolsa(){
            return bolsa;
        }
        public void setBolsa(double value){
            this.bolsa = value;
        }
    }
    public static void main(String[] args) {
        Aluno al = new Aluno ("Andreia Melo", 9855678,
         new DateYMD(18, 7, 1990), new DateYMD(1, 9, 2018));
        Professor p1 = new Professor("Jorge Almeida", 3467225, new DateYMD(13, 3, 1967), 
        "Associado", "Informática");
        Bolseiro bls = new Bolseiro ("Igor Santos", 8976543, new DateYMD(11, 5, 1985), new DateYMD(11, 5, 1985),  p1,900);

        bls.setBolsa(1050);
        System.out.println("Aluno: " + al.getNome());
        System.out.println(al);
         
        System.out.println("Bolseiro: " + bls.getNome() + ", NMec: " 
        + bls.getNMec() + ", Bolsa: " + bls.getBolsa() + ", Orientador: " + 
        bls.getOrientador());
        System.out.println(bls);
         }
    
}
