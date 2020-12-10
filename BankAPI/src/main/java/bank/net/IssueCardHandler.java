package bank.net;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class IssueCardHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
       /* OutputStream outputStream = exchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            String uri = exchange.getRequestURI().toString();
            InputStream is = exchange.getRequestBody();

            Response response = Response.getResponse(uri, "SHOW_CARDS");
            htmlBuilder.append("<html><body><p>")
                        .append(response.getMessage())
                        .append("</p></body><html>");

            //Headers requestHeaders = exchange.getRequestHeaders();
            //Set<Map.Entry<String, List<String>>> entries = requestHeaders.entrySet();

            //not quite sure what this part about
            //byte[] data = new byte[10];

            System.out.print("Request : " + uri + "\nResponse : " + response.toString() );
            exchange.sendResponseHeaders(response.status, htmlBuilder.toString().getBytes().length);
            outputStream.write(htmlBuilder.toString().getBytes(StandardCharsets.UTF_8));
        }
        outputStream.flush();
        outputStream.close();*/
    }
}
