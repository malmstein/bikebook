package com.malmstein.bikebook.json;

import com.google.gson.Gson;

public abstract class ApiRequest {

    public String toJson() {
        return new Gson().toJson(this);
    }
}
