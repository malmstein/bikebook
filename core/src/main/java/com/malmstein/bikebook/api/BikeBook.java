
package com.malmstein.bikebook.api;

import com.google.gson.reflect.TypeToken;
import com.malmstein.bikebook.http.HttpClient;
import com.malmstein.bikebook.json.ApiRequest;
import com.malmstein.bikebook.json.JsonReader;
import com.malmstein.bikebook.json.requests.GetBikeRequest;
import com.malmstein.bikebook.json.requests.GetIndexRequest;
import com.malmstein.bikebook.json.requests.GetManufacturerRequest;
import com.malmstein.bikebook.json.requests.GetManufacturerbyYearRequest;
import com.malmstein.bikebook.json.responses.BikeDetailJson;
import com.malmstein.bikebook.json.responses.YearJson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

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

    public Map<String, Map<String, List<YearJson>>> getIndex(String requestURL) {
        final GetIndexRequest request = new GetIndexRequest();
        return queryApi(createUrl(requestURL + request.getParams()), request, new TypeToken<Map<String, Map<String, List<YearJson>>>>() {}.getType());
    }

    public List<YearJson> getManufacturerByYear(String requestURL, String manufacturer, String year) {
        final GetManufacturerbyYearRequest request = new GetManufacturerbyYearRequest(manufacturer, year);
        return queryApi(createUrl(requestURL + request.getParams()), request, new TypeToken<List<YearJson>>() {}.getType());
    }

    public Map<String, List<YearJson>> getManufacturer(String requestURL, String manufacturer) {
        final GetManufacturerRequest request = new GetManufacturerRequest(manufacturer);
        return queryApi(createUrl(requestURL + request.getParams()), request, new TypeToken<Map<String, List<YearJson>>>() {}.getType());
    }

    public BikeDetailJson getBike(String requestURL, String manufacturer, String year, String frame) {
        final GetBikeRequest request = new GetBikeRequest(manufacturer, year, frame);
        return queryApi(createUrl(requestURL), request, BikeDetailJson.class);
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

