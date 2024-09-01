package vn.order.infrastructure.oauth;

import lombok.Getter;

@Getter
public class PasswordTypeConfig extends ClientCredentialsConfig {
    
    private String username;
    private String password;

    public PasswordTypeConfig(String clientId, String clientSecret, String username, String password) {
        super(clientId, clientSecret);
        
        this.username = username;
        this.password = password;
    }

}
