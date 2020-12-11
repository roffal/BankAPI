package bank.net;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IssueCardHandler extends MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange){
        try {
        OutputStream outputStream = exchange.getResponseBody();
        InputStream is = exchange.getRequestBody();
        handlePostCommon(exchange, "ISSUE_CARD");
        outputStream.flush();
        outputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
