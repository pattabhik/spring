package com.hackerrank.sample.dto;

public class StringResponse {
    private String echo;

    public StringResponse(String echo) {
        this.echo = echo;
    }

    public String getEcho() {
        return echo;
    }

    public void setEcho(String echo) {
        this.echo = echo;
    }
}
