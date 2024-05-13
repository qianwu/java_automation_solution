package charlotte.automation.utilities;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssureRequestBuilder {
    private RequestSpecification requestSpec;

    public RestAssureRequestBuilder() {
        this.requestSpec = RestAssured.given();
    }

    public RestAssureRequestBuilder withBaseUrl(String baseUrl) {
        this.requestSpec.baseUri(baseUrl);
        return this;
    }

    public RestAssureRequestBuilder withHeaders(Map<String, Object> headers) {
        this.requestSpec.headers(headers);
        return this;
    }

    public RestAssureRequestBuilder withQueryParam(String param, String value) {
        this.requestSpec.queryParam(param, value);
        return this;
    }

    public RestAssureRequestBuilder withBody(Object body) {
        this.requestSpec.body(body);
        return this;
    }

    public RequestSpecification build() {
        return this.requestSpec;
    }


}
