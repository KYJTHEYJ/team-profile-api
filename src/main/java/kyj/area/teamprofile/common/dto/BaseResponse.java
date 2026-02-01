package kyj.area.teamprofile.common.dto;

import lombok.Getter;

@Getter
public abstract class BaseResponse<T> {
    protected boolean success;
    protected String status;
    protected T data;
    protected String message;
}
