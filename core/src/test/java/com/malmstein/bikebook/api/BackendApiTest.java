package com.malmstein.bikebook.api;

import com.malmstein.bikebook.http.EndPoint;
import com.malmstein.bikebook.http.HttpClient;
import com.malmstein.bikebook.http.OkConnectionFactory;
import com.malmstein.bikebook.http.SawickiHttpClient;
import com.malmstein.bikebook.json.GsonJsonReader;
import com.malmstein.bikebook.json.JsonReader;
import com.malmstein.bikebook.json.responses.Index;
import com.malmstein.bikebook.json.responses.Manufacturer;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BackendApiTest {

    private static final EndPoint END_POINT = EndPoint.PRODUCTION;

    private static SnapshotServer server;
    private static HttpClient httpClient;
    private static JsonReader jsonReader;

    @BeforeClass
    public static void setupMockServer() throws IOException {
        server = new SnapshotServer();
        server.start();
        httpClient = new MockHttpClient(new SawickiHttpClient(new OkConnectionFactory(new OkHttpClient())), END_POINT.getUrl(), server.baseUrl());
        jsonReader = new GsonJsonReader();
    }

    @AfterClass
    public static void tearDownMockServer() throws IOException {
        server.stop();
    }

    @Test
    public void shouldReadIndex() throws IOException {
        final Backend api = createJsonApi(server.baseUrl());
        final Index index = api.getIndex(server.baseUrl().toString());
        assertThat(index).isNotNull();
    }

    @Test
    public void shouldReadManufacturer() throws IOException {
        final Backend api = createJsonApi(server.baseUrl());
        final Manufacturer manufacturer = api.getManufacturer(server.baseUrl().toString(), "Cinelli");
        assertThat(manufacturer).isNotNull();
    }

    private static Backend createJsonApi(final URL serverUrl) throws MalformedURLException {
        return new Backend(serverUrl, httpClient, jsonReader);
    }

}
