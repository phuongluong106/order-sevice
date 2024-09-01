package vn.order.infrastructure.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientCredentialsConfig {
    private String clientId;
    private String clientSecret;
}
