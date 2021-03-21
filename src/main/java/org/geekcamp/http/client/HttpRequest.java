package org.geekcamp.http.client;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest extends Http {
    private HttpMethodEnum method;
    private String uri;
    private final Map<String, String> parameters= new HashMap<>();
    private static final Map<String, HttpMethodEnum> methodEnumMap;

    static {
        methodEnumMap = new HashMap<>();

        methodEnumMap.put("GET", HttpMethodEnum.GET);
        methodEnumMap.put("POST", HttpMethodEnum.POST);
        methodEnumMap.put("PUT", HttpMethodEnum.PUT);
        methodEnumMap.put("DELETE", HttpMethodEnum.DELETE);
        methodEnumMap.put("OPTIONS", HttpMethodEnum.OPTIONS);
        methodEnumMap.put("CONNECTION", HttpMethodEnum.CONNECTION);
        methodEnumMap.put("HEAD", HttpMethodEnum.HEAD);
        methodEnumMap.put("TRACE", HttpMethodEnum.TRACE);
        methodEnumMap.put("PATCH", HttpMethodEnum.PATCH);
    }

    public HttpMethodEnum getMethod() {
        return method;
    }

    public void setMethod(String v) throws Exception {
        if (methodEnumMap.containsKey(v)) {
            this.method = methodEnumMap.get(v);
        } else {
            throw new Exception("无法识别的HTTP Method");
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String v) {
        this.uri = v;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void addParameter(String name, String value) {
        this.parameters.put(name, value);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method=" + method +
                ", uri='" + uri +
                ", httpVersion="+ getHttpVersion()+
                ", parameters=" + parameters +
                ", headers=" + getHeaders() +
                ", rawData=" + getRawData() +
                ", rawData2=" + getRawData2() +
                '}';
    }
}
