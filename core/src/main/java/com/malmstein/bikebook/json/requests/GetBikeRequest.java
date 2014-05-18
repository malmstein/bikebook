package com.malmstein.bikebook.json.requests;

import com.malmstein.bikebook.json.ApiRequest;

public class GetBikeRequest extends ApiRequest {

    private final String manufacturer;
    private final String year;
    private final String frame;

    public GetBikeRequest(String manufacturer, String year, String frame) {
        this.manufacturer = manufacturer;
        this.year = year;
        this.frame = frame;
    }

    @Override
    public String getParams() {
        return "manufacturer=" + manufacturer +
                "&year=" + year +
                "&frame_model=" + frame;

    }
}
