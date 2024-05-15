package com.university.msclient.response;

public class RegisterClientResponse {
    private String result;

    public RegisterClientResponse(String result) {
        this.result = result;
    }

    public RegisterClientResponse() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
