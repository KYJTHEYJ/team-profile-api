package kyj.area.teamprofile.common.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "success"
        , "status"
        , "data"
        , "message"
})
public class MainResponse<T> extends BaseResponse<T> {
    private MainResponse() {}

    public static <T> MainResponse<T> success(String status, T data) {
        MainResponse<T> response = new MainResponse<>();
        response.success = true;
        response.status = status;
        response.data = data;
        response.message = null;
        return response;
    }

    public static <T> MainResponse<T> fail(String status, String message) {
        MainResponse<T> response = new MainResponse<>();
        response.success = false;
        response.status = status;
        response.data = null;
        response.message = message;
        return response;
    }
}
