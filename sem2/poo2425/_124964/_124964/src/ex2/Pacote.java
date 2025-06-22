package ex2;

public abstract class Pacote {
    private String source;
    private String destination;

    public Pacote(String source, String destination){
        if(addressValidate(source) && addressValidate(destination) ){
            this.source = source;
            this.destination = destination;
        }
        else{throw new IllegalArgumentException("Invalid source or destination");}
    }

    public boolean addressValidate(String address){
        return address.matches("^([0-255][.]){3}[0-255]$");
    }
    public String getSource(){
        return this.source;
    }
    public String getDestination(){
        return this.destination;
    }
    public abstract int getMessageLength();
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Pacote other = (Pacote) object;
        return this.getSource().equals(other.getSource()) &&
               this.getDestination().equals(other.getDestination());
    }


}
