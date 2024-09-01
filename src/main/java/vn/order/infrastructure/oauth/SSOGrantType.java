package vn.order.infrastructure.oauth;

import lombok.Getter;

@Getter
public enum SSOGrantType {
    password,
    client_credentials;
}
