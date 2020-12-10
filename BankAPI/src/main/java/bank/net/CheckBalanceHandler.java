package bank.net;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class CheckBalanceHandler extends MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        handleGetCommon(exchange, "CHECK_BALANCE");
        outputStream.flush();
        outputStream.close();
    }
}
