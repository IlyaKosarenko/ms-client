package com.university.msclient.response;

public class DeleteClientResponse {
    private String result;

    public DeleteClientResponse(String result) {
        this.result = result;
    }

    public DeleteClientResponse() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
