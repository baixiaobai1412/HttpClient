package org.geekcamp.http.MessgeProcess;

import org.geekcamp.http.client.HttpResponse;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("UnnecessaryLocalVariable")
public class ResponseMessage {

    public  static  HttpResponse ResponseSaveObject(String v){
        final int bodyIndex = v.indexOf("\r\n\r\n");
        final String body = v.substring(bodyIndex + 4);

        final int startLineIndex = v.indexOf("\r\n");
        final String startLine = v.substring(0, startLineIndex);

        final int headersBeginIndex = startLineIndex + 2;
        final int headersEndIndex = bodyIndex;
        final String headers = v.substring(headersBeginIndex, headersEndIndex);

        final ByteBuffer buffer = ByteBuffer.wrap(body.getBytes(StandardCharsets.UTF_8));
        HttpResponse response = new  HttpResponse();
        ResponseMessage.StartLine(startLine,response);
        ResponseMessage.headerLine(headers,response);
        response.setRawData(buffer);
        return response;
    }


    private static int i =0;

    private static void StartLine(String v,HttpResponse response){
        final int SpaceSeparate = v.indexOf(" ");
        if (SpaceSeparate == -1){
            response.setMessage(v);
        }else if (i == 0){
            final String StartLine = v.substring(0, SpaceSeparate);
            response.setHttpVersion(StartLine);
            i++;
            
            final String EndLine = v.substring(SpaceSeparate+1);
            ResponseMessage.StartLine(EndLine,response);
            
        }else if (i == 1 ) {
            final String StartLine = v.substring(0, SpaceSeparate);
            final int StatusCode=Integer.parseInt(StartLine);
            response.setStatusCode(StatusCode);
            i++;

            final String EndLine = v.substring(SpaceSeparate+1);
            ResponseMessage.StartLine(EndLine,response);
        }


    }

    private static void headerLine(String s,HttpResponse response){
        final int HeaderColonSeparate = s.indexOf(":");
        final int HeaderEnd = s.indexOf("\r\n");
        if (HeaderEnd != -1) {
            final String headerKey = s.substring(0, HeaderColonSeparate);

            final String HeaderValue = s.substring(HeaderColonSeparate + 1, HeaderEnd);
            response.addHeader (headerKey, HeaderValue);


            final String headerOther = s.substring(HeaderEnd + 2);
            ResponseMessage.headerLine(headerOther,response);

        }else {
            //最后一个参数，传入时没有/r/n
            final String headerKey = s.substring(0, HeaderColonSeparate);
            final String HeaderDetail = s.substring(HeaderColonSeparate+2);
            response.addHeader(headerKey, HeaderDetail);
        }
    }
}
