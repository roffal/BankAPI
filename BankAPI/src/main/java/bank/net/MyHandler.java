package bank.net;

import bank.service.CheckInquiry;
import bank.service.Command;
import bank.service.Inquiry;
import bank.service.Response;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyHandler {
    public void handleGetCommon(HttpExchange exchange, String command) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        //if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            String uri = exchange.getRequestURI().toString();
            Response response = Response.getResponse(uri, command);
            if (response.gson != null) {
                htmlBuilder.append("<html><body><p>")
                        .append(response.gson)
                        .append("</p></body><html>");
                exchange.sendResponseHeaders(response.status, response.gson.getBytes().length);
                outputStream.write(response.gson.getBytes(StandardCharsets.UTF_8));
            } else {
                htmlBuilder.append("<html><body><p>")
                        .append(response.message)
                        .append("</p></body><html>");
                exchange.sendResponseHeaders(response.status, htmlBuilder.toString().getBytes().length);
                outputStream.write(htmlBuilder.toString().getBytes(StandardCharsets.UTF_8));
            }
            System.out.print("Request : " + uri + "\nResponse : " + response.toString() +"\n");
       // }
    }

    public void handlePostCommon(HttpExchange exchange, String command) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        //if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            String uri = exchange.getRequestURI().toString();
            InputStream is = exchange.getRequestBody();
            BufferedReader read = new BufferedReader(new InputStreamReader(is));
            StringBuilder gsonBuild = new StringBuilder();
            String line;
            while ((line = read.readLine()) != null){
                gsonBuild.append(line);
            }
            String gson = gsonBuild.toString();
            Inquiry inquiry = new Inquiry(gson);
            inquiry.command = command;
            CheckInquiry checkInquiry = new CheckInquiry(inquiry, "prod");
            Response response = new Response();
            if (checkInquiry.isChecked)
                response = Command.execute(inquiry, "prod");

            htmlBuilder.append("<html><body><p>")
                    .append(response.message)
                    .append("</p></body><html>");

            System.out.print("Request : " + uri + "\nResponse : " + response.toString() +"\n");
            exchange.sendResponseHeaders(response.status, htmlBuilder.toString().getBytes().length);
            outputStream.write(htmlBuilder.toString().getBytes(StandardCharsets.UTF_8));
        //}
    }

}
