package charlotte.automation.utilities;

public class ApiResponse <T>{
    private int statusCode;
    private T body;
    private String message;

    public ApiResponse() {
        this.statusCode = statusCode;
        this.body = body;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getBody() {
        return body;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "statusCode=" + statusCode +
                ", body=" + body +
                ", message='" + message + '\'' +
                '}';
    }
}
