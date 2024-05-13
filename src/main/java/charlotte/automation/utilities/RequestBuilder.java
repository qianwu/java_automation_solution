package charlotte.automation.utilities;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {
    private String url;
    private Map<String, String> headers;
    private Map<String, String> params;
    private String body;

    public RequestBuilder(String url) {
        this.url = url;
        this.headers = new HashMap<>();
        this.params = new HashMap<>();
    }

    public RequestBuilder addHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public RequestBuilder addParam(String name, String value) {
        params.put(name, value);
        return this;
    }

    public RequestBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public HttpGet buildGetRequest() {
        HttpGet httpGet = new HttpGet(url);
        headers.forEach(httpGet::addHeader);
        return httpGet;
    }

    public HttpPost buildPostRequest() {
        HttpPost httpPost = new HttpPost(url);
        if (body != null) {
            // Set the body of the request
            HttpEntity entity = new StringEntity(body, "UTF-8");
            httpPost.setEntity(entity);
            headers.forEach(httpPost::addHeader);
        }
        return httpPost;
    }


    public class ResponseProcessor {
        public ApiResponse process(HttpResponse httpResponse) {
            // Process the HttpResponse and return ApiResponse object
            return new ApiResponse();
        }
    }
}
