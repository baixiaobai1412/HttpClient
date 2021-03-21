package org.geekcamp.http.MessgeProcess;

import org.geekcamp.http.client.HttpRequest;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


@SuppressWarnings("UnnecessaryLocalVariable")
public class RequestMessage {
    public static HttpRequest RequestSaveObject(String v) throws Exception {
        final int bodyIndex = v.indexOf("\r\n\r\n");
        final String body = v.substring(bodyIndex + 4);

        final int startLineIndex = v.indexOf("\r\n");
        final String startLine = v.substring(0, startLineIndex);

        final int headersBeginIndex = startLineIndex + 2;
        final int headersEndIndex = bodyIndex;
        final String headers = v.substring(headersBeginIndex, headersEndIndex);

        HttpRequest request = new HttpRequest();
        RequestMessage.StartLine(startLine, request);
        RequestMessage.headerLine(headers, request);
        final ByteBuffer buffer = ByteBuffer.wrap(body.getBytes(StandardCharsets.UTF_8));
        request.setRawData(buffer);


        return request;
    }

    private static int i = 0;

    private static void StartLine(String s, HttpRequest request) throws Exception {
        final int SpaceEnd = s.indexOf(" ");

        if (SpaceEnd == -1) {
            request.setHttpVersion(s);
        } else if (i == 0) {
            final String StartLine = s.substring(0, SpaceEnd);
            final String EndLine = s.substring(SpaceEnd + 1);

                request.setMethod(StartLine);
                i++;
                RequestMessage.StartLine(EndLine, request);

        } else if (i == 1) {

            final String UriLine = s.substring(0, SpaceEnd);
            final String other = s.substring(SpaceEnd + 1);
            final int ParametersEnd = UriLine.indexOf("?");

            if (ParametersEnd == -1) {
                request.setUri(UriLine);
                i++;
            } else {
                final String startLine = UriLine.substring(0, ParametersEnd);
                request.setUri(startLine);
                i++;
                final String parameters = UriLine.substring(ParametersEnd + 1);
                ParameterProcess(parameters , request);
            }
            RequestMessage.StartLine(other, request);
        }
    }


    private static void headerLine(String v, HttpRequest request) {
        final int HeaderColonSeparate = v.indexOf(":");
        final int HeaderEnd = v.indexOf("\r\n");
        if (HeaderEnd != -1) {
            final String headerKey = v.substring(0, HeaderColonSeparate);

            final String HeaderValue = v.substring(HeaderColonSeparate + 1, HeaderEnd);
            request.addHeader(headerKey, HeaderValue);

            final String headerOther = v.substring(HeaderEnd + 2);
            RequestMessage.headerLine(headerOther, request);

        } else {
            final String headerKey = v.substring(0, HeaderColonSeparate);
            final String HeaderValue = v.substring(HeaderColonSeparate + 2);
            request.addHeader(headerKey, HeaderValue);
        }
    }


    private static void ParameterProcess(String v, HttpRequest request) {
        final int HeaderEqual = v.indexOf("=");
        final int HeaderJoiner = v.indexOf("&");

        if (HeaderJoiner == -1) {
            final String HeaderKey = v.substring(0, HeaderEqual);
            final String HeaderValue = v.substring(HeaderEqual + 1);
            request.addParameter(HeaderKey, HeaderValue);

        } else {

            final String HeaderKey = v.substring(0, HeaderEqual);
            final String HeaderValue = v.substring(HeaderEqual + 1, HeaderJoiner);
            request.addParameter(HeaderKey, HeaderValue);

            final String headerOther = v.substring(HeaderJoiner + 1);
            RequestMessage.ParameterProcess(headerOther, request);
        }
    }

}
