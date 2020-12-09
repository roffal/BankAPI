package bank.net;

import com.sun.xml.internal.ws.api.message.Message;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return status == response.status &&
                Objects.equals(Message, response.Message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, Message);
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", Message='" + Message + '\'' +
                '}';
    }
}
