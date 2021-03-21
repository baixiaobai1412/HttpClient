package org.geekcamp;

import org.geekcamp.http.MessgeProcess.RequestMessage;
import org.geekcamp.http.MessgeProcess.ResponseMessage;
import org.geekcamp.http.client.HttpRequest;
import org.geekcamp.http.client.HttpResponse;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpClientApplication {
    public static void main(String[] args) throws Exception {
        final String requestFilename = "src/main/resources/request.txt";
        final String requestStream = Files.readString(Path.of(requestFilename));
        //处理request
        HttpRequest httpRequest =  RequestMessage.RequestSaveObject(requestStream);
        System.out.println(httpRequest.toString());

        final String ResponseFilename = "src/main/resources/response.txt";
        final String ResponseStream = Files.readString(Path.of(ResponseFilename));
//        ResponseMessage response =  new ResponseMessage(ResponseStream);
        HttpResponse httpResponse = ResponseMessage.ResponseSaveObject(ResponseStream);
        System.out.println(httpResponse.toString());

        String converted = new String(httpResponse.getRawData().array(), StandardCharsets.UTF_8);
        System.out.println(converted);

    }
}
