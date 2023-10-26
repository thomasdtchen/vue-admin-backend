package thomas.spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Result {
    private int code;
    private String message;
    private boolean success;
    private Map<String, Object> data = new HashMap<>();

    public static Result ok(){
        return Result.builder().success(true).code(ResultCode.SUCCESS).message("Success").build();
    }

    public static Result error(){
        return Result.builder().success(false).code(ResultCode.ERROR).message("Error").build();
    }

    public Result data(String key, String value){
        this.data.put(key, value);
        return this;
    }

}
