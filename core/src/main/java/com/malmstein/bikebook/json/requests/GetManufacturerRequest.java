package com.malmstein.bikebook.json.requests;

import com.malmstein.bikebook.json.ApiRequest;

public class GetManufacturerRequest extends ApiRequest {

    private final String manufacturer;

    public GetManufacturerRequest(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String getParams() {
        return "manufacturer=" + manufacturer;
    }
}
