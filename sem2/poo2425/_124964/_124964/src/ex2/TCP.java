package ex2;

public class TCP extends Pacote {
    static int idcounter = 100;
    private int id;
    private String message;

    public TCP(String source, String destination, String message){
        super(source, destination);
        if(message.length()<=1460){
            this.message = message;
            idcounter+=1;
            this.id=idcounter;
        }
        else{throw new IllegalArgumentException("Message too long");}
    }
    public int getMessageLength(){
        return message.length();
    }
    public int getId(){
        return this.id;
    }
    public String getMessage(){
        return this.message;
    }
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        TCP other = (TCP) object;
        return super.equals(object) && this.getMessage().equals(other.getMessage());
    }
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("TCP ");
        retString.append("(Source : " + this.getSource() + "; ");
        retString.append("Destination: " + this.getDestination() + "; ");
        retString.append("Id: " + this.getId() + "; ");
        retString.append("Message: [" + this.getMessage() + "];)\n");

        return retString.toString();

    }
        
        
    }


