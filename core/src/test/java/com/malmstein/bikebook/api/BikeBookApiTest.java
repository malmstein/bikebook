package com.malmstein.bikebook.api;

import com.malmstein.bikebook.json.GsonJsonReader;
import com.malmstein.bikebook.json.responses.BikeDetail;
import com.malmstein.bikebook.json.responses.Manufacturer;
import com.malmstein.bikebook.util.HttpClientUtil;
import com.malmstein.bikebook.util.JsonHelper;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BikeBookApiTest {

    @Test
    public void shouldReadBikeDetail() throws IOException {
        final URL serverUrl = prepareMockServer(JsonHelper.GET_BIKE_DETAIL);
        final BikeBook api = createJsonApi(serverUrl);

        final BikeDetail bikeDetail = api.getBike(serverUrl.toString(), "Fuji", "2014", "outland_29_1_3");
        assertThat(bikeDetail).isNotNull();
        assertThat(bikeDetail.getBike().getManufacturer()).isEqualTo("Fuji");
        assertThat(bikeDetail.getBike().getYear()).isEqualTo(2014);
    }

    @Test
    public void shouldReadManufacturer() throws IOException {
        final URL serverUrl = prepareMockServer(JsonHelper.GET_MANUFACTURER);
        final BikeBook api = createJsonApi(serverUrl);

        final Manufacturer manufacturer = api.getManufacturer(serverUrl.toString(), "Cinelli");
        assertThat(manufacturer).isNotNull();
    }

    private static URL prepareMockServer(final String response) throws IOException {
        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(response));
        server.play();
        return server.getUrl("/");
    }

    private static BikeBook createJsonApi(final URL serverUrl) throws MalformedURLException {
            return new BikeBook(serverUrl, HttpClientUtil.createSimpleHttpClient(), new GsonJsonReader());
    }
}
