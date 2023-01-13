package com.cbatech.springsecu.auth.dto;

public class JwtResponseDto {

    private String accessToken;
    private String tokeType = "Bearer";

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokeType() {
        return tokeType;
    }

    public void setTokeType(String tokeType) {
        this.tokeType = tokeType;
    }
}
