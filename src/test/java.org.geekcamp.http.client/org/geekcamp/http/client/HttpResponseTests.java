package org.geekcamp.http.client;

import org.geekcamp.http.MessgeProcess.ResponseMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class HttpResponseTests {
    @Test
    void HttpResponseTest() throws IOException {
//        HttpResponse httpResponse = new HttpResponse();
        final String ResponseFilename = "src/main/resources/response.txt";
        final String ResponseStream = Files.readString(Path.of(ResponseFilename));
//        ResponseMessage response =  new ResponseMessage(ResponseStream);
        HttpResponse httpResponse = ResponseMessage.ResponseSaveObject(ResponseStream);

        assertEquals("HTTP/1.1", httpResponse.getHttpVersion());

        assertEquals(200, httpResponse.getStatusCode());


        httpResponse.setMessage("OK");
        assertEquals("OK",httpResponse.getMessage());



        final ByteBuffer buffer = ByteBuffer.wrap("Hello world!".getBytes(StandardCharsets.UTF_8));
        httpResponse.setRawData(buffer);
        assertEquals(buffer, httpResponse.getRawData());

    }
}
