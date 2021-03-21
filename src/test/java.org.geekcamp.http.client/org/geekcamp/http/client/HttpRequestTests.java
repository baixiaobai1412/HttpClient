package org.geekcamp.http.client;

import org.geekcamp.http.MessgeProcess.RequestMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;


public class HttpRequestTests {
    @Test
    void allTests() throws Exception {
//        HttpRequest httpRequest = new HttpRequest();
        final String requestFilename = "src/main/resources/request.txt";
        final String requestStream = Files.readString(Path.of(requestFilename));

        final int bodyIndex = requestStream.indexOf("\r\n\r\n");
        final String body = requestStream.substring(bodyIndex + 4);
        //处理requst

        HttpRequest httpRequest =  RequestMessage.RequestSaveObject(requestStream);
        assertThrows(Exception.class, () -> httpRequest.setMethod("FOO"));

        assertNotNull(httpRequest.getMethod());

        assertEquals("/api/login", httpRequest.getUri());

        assertEquals("HTTP/1.1", httpRequest.getHttpVersion());

        final ByteBuffer buffer = ByteBuffer.wrap(body.getBytes(StandardCharsets.UTF_8));

        assertEquals(buffer, httpRequest.getRawData());

        // TODO 将HTTP请求报文解析成HttpRequest对象
        // TODO 完成HTTP响应报文的解析，以及单元测试
    }
}
