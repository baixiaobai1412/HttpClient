package org.geekcamp.http.client;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Http {
    private String httpVersion;
    private final Map<String, String> headers = new HashMap<>();
    private ByteBuffer rawData;
    private List<Byte> rawData2;

    public String getHttpVersion() {
        return httpVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public ByteBuffer getRawData() {
        return rawData;
    }

    public List<Byte> getRawData2() {
        return rawData2;
    }

    public void setHttpVersion(String v) {
        this.httpVersion = v;
    }

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public String getBody() {
        return this.rawData.toString();
    }


    public void setRawData(ByteBuffer v) {
        this.rawData = v;
    }

    public void setRawData2(List<Byte> v) {
        this.rawData2 = v;
    }
}