package bank.net;

import bank.service.CheckInquiry;
import bank.service.Command;
import com.sun.xml.internal.ws.api.message.Message;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

public class Response {
    public int status = 404;
    String Message;

    public Response(){
    }

    public Response(int status, String Message){
        this.status = status;
        this.Message = Message;
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
        return "{" +
                "status=" + status +
                ", message='" + Message + '\'' +
                '}';
    }

    public static Response getResponse(String uri_str, String command){
        Inquiry inquiry = new Inquiry(uri_str, command);
        CheckInquiry checkInquiry = new CheckInquiry(inquiry);
        Response response = new Response(404, "Oops, we can't seem to find the page you're looking for. Check in with our service team to help you find it");
        if (checkInquiry.isChecked)
            response = Command.execute(inquiry);
        return response;
    }
}
