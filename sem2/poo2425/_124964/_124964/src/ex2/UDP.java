package ex2;

public class UDP extends Pacote {
    private String message;
    public UDP(String source, String destination, String message){
        super(source, destination);
        if(message.length()<=1480){
            this.message = message;
        }
        else{throw new IllegalArgumentException("Message too long");}
    }
    public int getMessageLength(){
        return message.length();
    }
    public String getMessage(){
        return this.message;
    }
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        UDP other = (UDP) object;
        return super.equals(object) && this.getMessage().equals(other.getMessage());
    }

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("UDP ");
        retString.append("(Source : " + this.getSource() + "; ");
        retString.append("Destination: " + this.getDestination() + "; ");
        retString.append("Message: [" + this.getMessage() + "];)\n");

        return retString.toString();

    }
        
        
    }



