package com.wjnovoa.app.security;

/**
 * @author William Johan Novoa Melendrez
 * @date 4/08/2022
 */
public class JWTAuthResponseDTO {

    private String tokenOfAccess;
    private String tokenType = "Bearer";

    public JWTAuthResponseDTO(String tokenOfAccess) {
        this.tokenOfAccess = tokenOfAccess;
    }
    public JWTAuthResponseDTO(String tokenOfAccess, String tokenType) {
        this.tokenOfAccess = tokenOfAccess;
        this.tokenType = tokenType;
    }

    public String getTokenOfAccess() {
        return tokenOfAccess;
    }

    public void setTokenOfAccess(String tokenOfAccess) {
        this.tokenOfAccess = tokenOfAccess;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}