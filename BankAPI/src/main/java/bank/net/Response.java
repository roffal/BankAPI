package bank.net;

import bank.service.CheckInquiry;
import bank.service.Command;

import java.util.Objects;

public class Response {
    public int status = 404;
    String message = "Oops, we can't seem to find the page you're looking for. Check in with our service team to help you find it";

    public Response(){
    }

    public Response(int status, String Message){
        this.status = status;
        this.message = Message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return status == response.status &&
                Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }

    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }

    public static Response getResponse(String uri_str, String command){
        Inquiry inquiry = new Inquiry(uri_str, command);
        CheckInquiry checkInquiry = new CheckInquiry(inquiry);
        Response response = new Response();
        if (checkInquiry.isChecked)
            response = Command.execute(inquiry);
        return response;
    }
}
