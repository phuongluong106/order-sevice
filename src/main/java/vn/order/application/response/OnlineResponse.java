package vn.order.application.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OnlineResponse<T> {
	@JsonProperty("data")
    private T data;
    @JsonProperty("responseCode")
    private int responseCode;
    @JsonProperty("responseMessage")
    private String responseMessage;
    @JsonProperty("errors")
    private Map<String, String> errors;
}
