package org.geekcamp.http.client;

public class HttpResponse extends Http {
    private Integer statusCode;
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer v) {
        this.statusCode = v;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String v) {
        this.message = v;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                ", httpVersion="+ getHttpVersion()+
                ", statusCode=" + statusCode +
                ", message=" + message +
                ", headers="+getHeaders()+
                ", rawData="+getRawData()+
                ", rawData2="+getRawData2()+
                ", Body="+getBody()+
                '}';
    }
}