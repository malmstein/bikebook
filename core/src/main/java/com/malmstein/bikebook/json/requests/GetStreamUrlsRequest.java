package com.malmstein.bikebook.json.requests;

import com.malmstein.bikebook.json.ApiRequest;

public class GetStreamUrlsRequest extends ApiRequest {

    private String cmd = "get_stream_urls";
    private String token;
    private String language;
    private String id;

    public GetStreamUrlsRequest(String token, String language, String id) {
        this.token = token;
        this.language = language;
        this.id = id;
    }
}
