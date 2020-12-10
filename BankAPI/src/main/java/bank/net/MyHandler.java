package bank.net;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyHandler {
    public void handleGetCommon(HttpExchange exchange, String command) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            String uri = exchange.getRequestURI().toString();
            InputStream is = exchange.getRequestBody();
            Response response = Response.getResponse(uri, command);
            htmlBuilder.append("<html><body><p>")
                    .append(response.message)
                    .append("</p></body><html>");

            System.out.print("Request : " + uri + "\nResponse : " + response.toString() +"\n");
            exchange.sendResponseHeaders(response.status, htmlBuilder.toString().getBytes().length);
            outputStream.write(htmlBuilder.toString().getBytes(StandardCharsets.UTF_8));
        }
    }

    public void handlePostCommon(HttpExchange exchange, String command) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            String uri = exchange.getRequestURI().toString();
            InputStream is = exchange.getRequestBody();
            Response response = Response.getResponse(uri, command);
            //response.
            htmlBuilder.append("<html><body><p>")
                    .append(response.message)
                    .append("</p></body><html>");

            System.out.print("Request : " + uri + "\nResponse : " + response.toString() +"\n");
            exchange.sendResponseHeaders(response.status, htmlBuilder.toString().getBytes().length);
            outputStream.write(htmlBuilder.toString().getBytes(StandardCharsets.UTF_8));
        }
    }

}
