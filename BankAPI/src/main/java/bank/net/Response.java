package bank.net;

import com.sun.xml.internal.ws.api.message.Message;

public class Response {
    boolean status;
    String Message;

    public Response(){

    }

    public Response(boolean status, String Message){
        this.status = status;
        this.Message = Message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

}
