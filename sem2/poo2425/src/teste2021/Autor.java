package teste2021;

public class Autor {
    private String name;
    private int birthYear;

    public Autor(String name, int birthYear){
        this.name = name;
        this.birthYear = birthYear;
    }


    public String getName(){
        return name;
    }

    public int getBirthYear(){
        return birthYear;
    }

    @Override

    public boolean equals(Object object){
        if(object.getClass()!=this.getClass()){return false;}

        Autor obj = (Autor) object;

        return this.name.equals(obj.getName()) && this.birthYear==obj.getBirthYear();
    }

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append(this.name).append(" (").append(this.birthYear).append("-)");
        return retString.toString();
    }

}
