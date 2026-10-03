package Creational_Design_Pattern.Builder_Design_Pattern;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class HTTPRequest{
    // Required
    private final String url;

    // Optional
    private final String method;
    private final Map<String, String> headers;
    private final Map<String, String> queryParams;
    private final String body;
    private final int timeout;

    private HTTPRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = Map.copyOf(builder.headers);
        this.queryParams = Map.copyOf(builder.queryParams);
        this.body = builder.body;
        this.timeout = builder.timeout;
    }

    public String getUrl() { return url; }
    public String getMethod() { return method; }
    public Map<String, String> getHeaders() { return headers; }
    public Map<String, String> getQueryParams() { return queryParams; }
    public String getBody() { return body; }
    public int getTimeout() { return timeout; }

    @Override
    public String toString() {
        return "HttpRequest{url='" + url + "', method='" + method +
                "', headers=" + headers + ", queryParams=" + queryParams +
                ", body='" + body + "', timeout=" + timeout + "}";
    }

    public static class Builder{
        private final String url; // required fields become final
        private String method = "GET";
        private Map<String, String> headers = new HashMap<>();
        private Map<String, String> queryParams = new HashMap<>();
        private String body;
        private int timeout = 30000;

        public Builder(String url) { // constructor gets all required parameters
            this.url = url;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder addHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder addQueryParam(String key, String value) {
            this.queryParams.put(key, value);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public HTTPRequest build() {
            return new HTTPRequest(this);
        }
    }

}

public class HTTP_Request_Builder {
    public static void main(String[] args) {
        // Simple GET request - just the URL
        HTTPRequest get = new HTTPRequest.Builder("https://api.example.com/users")
                .build();

        // POST with body and custom timeout
        HTTPRequest post = new HTTPRequest.Builder("https://api.example.com/users")
                .method("POST")
                .addHeader("Content-Type", "application/json")
                .body("{\"name\":\"Alice\",\"email\":\"alice@example.com\"}")
                .timeout(5000)
                .build();

        // Authenticated PUT with query parameters
        HTTPRequest put = new HTTPRequest.Builder("https://api.example.com/config")
                .method("PUT")
                .addHeader("Authorization", "Bearer token123")
                .addHeader("Content-Type", "application/json")
                .addQueryParam("env", "production")
                .addQueryParam("version", "2")
                .body("{\"feature_flag\":true}")
                .timeout(10000)
                .build();

        System.out.println(get);
        System.out.println(post);
        System.out.println(put);
    }
}
