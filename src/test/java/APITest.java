import charlotte.automation.configurations.ConfigManager;
import charlotte.automation.utilities.RequestBuilder;
import charlotte.automation.utilities.RestAssureRequestBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class APITest {

    @Test(description = "use configuration settings in Tests")
    public void testAPI() {
        Map<String, Object> config = ConfigManager.getConfigurations();
        Map<String, Object> apiConfig = (Map<String, Object>) config.get("api");
        String baseUrl = (String) apiConfig.get("base_url");
        System.out.println("baseUrl: " + baseUrl);
    }

    @Test(description = "use yaml path in Tests")
    public void testAPIByYamlPath() {
        String baseUrl = (String) ConfigManager.getConfigurations("api.base_url");
        assert Objects.equals(baseUrl, "https://api.zippopotam.us");
    }

    @Test(description = " rest assured test http get")
    public void testRestAssuredGet() {
        Map<String, Object> header = ConfigManager.getConfigurationsMap("api.headers");
        System.out.println("header: " + header);
        RequestSpecification requestSpec = new RestAssureRequestBuilder()
                .withBaseUrl("https://api.zippopotam.us")
                .withHeaders(header)
                .build();
        System.out.println(requestSpec.toString());
        given(requestSpec)
                .when()
                .get("/us/90210")
                .then()
                .statusCode(200);
    }
}
