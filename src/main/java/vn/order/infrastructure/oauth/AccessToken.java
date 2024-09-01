package vn.order.infrastructure.oauth;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken {
    public String access_token;
    public int expires_in;
    public int refresh_expires_in;
    public String refresh_token;
    public String token_type;
    @JsonPropertyDescription("not-before-policy")
    public int notBeforePolicy;
    public String session_state;
    public String scope;

    public ZonedDateTime expires_time;

    public void setExpires_time() {
        this.expires_time = ZonedDateTime.now().plusSeconds(expires_in);
    }
}
