package bank.net;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ShowCardsHandler extends MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        InputStream is = exchange.getRequestBody();
        handleGetCommon(exchange, "SHOW_CARDS");
        outputStream.flush();
        outputStream.close();
    }
}
