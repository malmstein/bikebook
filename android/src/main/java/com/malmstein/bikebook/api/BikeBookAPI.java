package com.malmstein.bikebook.api;

import com.malmstein.bikebook.json.responses.IndexResponse;

import ly.apps.android.rest.client.Callback;
import ly.apps.android.rest.client.annotations.GET;
import ly.apps.android.rest.client.annotations.RestService;

@RestService
public interface BikeBookAPI {

    @GET("/assets/index.json")
    void getIndex(Callback<IndexResponse> callback);

}