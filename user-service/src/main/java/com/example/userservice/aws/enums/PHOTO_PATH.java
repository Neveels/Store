package com.example.userservice.aws.enums;

public enum PHOTO_PATH {
    USER("user/"),
    PRODUCT("product/"),
    DEFAULT_PATH("default_path.jpg");

    private final String url;

    PHOTO_PATH(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
