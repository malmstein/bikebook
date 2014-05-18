package com.malmstein.bikebook.json.requests;

import com.malmstein.bikebook.json.ApiRequest;

public class GetManufacturerbyYearRequest extends ApiRequest {

    private final String manufacturer;
    private final String year;

    public GetManufacturerbyYearRequest(String manufacturer, String year) {
        this.manufacturer = manufacturer;
        this.year = year;
    }

    @Override
    public String getParams() {
        return "manufacturer=" + manufacturer +
                "&year=" + year;
    }
}
