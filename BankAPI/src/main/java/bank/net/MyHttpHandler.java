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

public class MyHttpHandler implements HttpHandler {
    @Override
    public synchronized void handle(HttpExchange exchange) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")){
        String uri = exchange.getRequestURI().toString();
        System.out.println("read uri");
        //Response response = new Response();
        Response response = Response.getResponse(uri, "SHOW_CARDS");
        System.out.println(response.toString());
        htmlBuilder.append("<html><body><p>")
                    .append(response.status)
                    .append("</p></body><html>");
            //getRequestBody if any
            //InputStream is = exchange.getRequestBody();

            //mapping of headers
            //Headers requestHeaders = exchange.getRequestHeaders();
            //Set<Map.Entry<String, List<String>>> entries = requestHeaders.entrySet();

            //not quite sure what this part about
            byte[] data = new byte[10];
            System.out.print(new String(data));

            exchange.sendResponseHeaders(response.status, htmlBuilder.toString().getBytes().length);
            outputStream.write(htmlBuilder.toString().getBytes(StandardCharsets.UTF_8));
        }
        outputStream.flush();
        outputStream.close();
    }
}
