package com.malmstein.bikebook.api;

import com.google.gson.reflect.TypeToken;
import com.malmstein.bikebook.http.HttpClient;
import com.malmstein.bikebook.json.ApiRequest;
import com.malmstein.bikebook.json.JsonReader;
import com.malmstein.bikebook.json.requests.GetBikeRequest;
import com.malmstein.bikebook.json.requests.GetIndexRequest;
import com.malmstein.bikebook.json.requests.GetManufacturerRequest;
import com.malmstein.bikebook.json.responses.BikeDetail;
import com.malmstein.bikebook.json.responses.Index;
import com.malmstein.bikebook.json.responses.Manufacturer;
import com.malmstein.bikebook.json.responses.Year;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class BikeBook {

    private final HttpClient httpClient;
    private final URL baseUrl;
    private final JsonReader jsonReader;

    public BikeBook(final URL baseUrl, final HttpClient httpClient, final JsonReader jsonReader) {
        this.httpClient = checkNotNull(httpClient);
        this.baseUrl = baseUrl;
        this.jsonReader = checkNotNull(jsonReader);
    }

    public Index getIndex(String requestURL) {
        final GetIndexRequest request = new GetIndexRequest();
        return queryApi(createUrl(requestURL + request.getParams()), request, Index.class);
    }

    public Manufacturer getManufacturer(String requestURL, String manufacturer) {
        final GetManufacturerRequest request = new GetManufacturerRequest(manufacturer);
        return queryApi(createUrl(requestURL + request.getParams()), request, TypeToken.get(new ArrayList<Year>().getClass()).getType());
    }

    public BikeDetail getBike(String requestURL, String manufacturer, String year, String frame) {
        final GetBikeRequest request = new GetBikeRequest(manufacturer, year, frame);
        return queryApi(createUrl(requestURL), request, BikeDetail.class);
    }

    private <T> T queryApi(final URL requestUrl, final ApiRequest request, final Class<T> responseClass) {
        if (request.toQuery().isEmpty()) {
            return jsonReader.fromJson((httpClient.get(requestUrl)), responseClass);
        } else {
            return jsonReader.fromJson(httpClient.get(createUrl(requestUrl + "?" + request.toQuery())), responseClass);
        }
    }

    private <T> T queryApi(final URL requestUrl, final ApiRequest request, final Type type) {
        if (request.toQuery().isEmpty()) {
            return jsonReader.fromJson((httpClient.get(requestUrl)), type);
        } else {
            return jsonReader.fromJson(httpClient.get(createUrl(requestUrl + "?" + request.toQuery())), type);
        }
    }

    private static String encode(final String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static URL createUrl(final String urlAsString) {
        try {
            return new URL(urlAsString);
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
