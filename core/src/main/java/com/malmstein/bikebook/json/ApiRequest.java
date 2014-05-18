package com.malmstein.bikebook.json;

public abstract class ApiRequest {

    public String toQuery() {
        return getParams();
    }

    public abstract String getParams();
}
