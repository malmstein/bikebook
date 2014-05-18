package com.malmstein.bikebook.api;

import com.malmstein.bikebook.json.GsonJsonReader;
import com.malmstein.bikebook.json.responses.BikeDetail;
import com.malmstein.bikebook.json.responses.Year;
import com.malmstein.bikebook.util.HttpClientUtil;
import com.malmstein.bikebook.util.JsonHelper;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

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

        final Map<String, List<Year>> manufacturer = api.getManufacturer(serverUrl.toString(), "Cinelli");
        assertThat(manufacturer).isNotNull();
        assertThat(manufacturer.size()).isEqualTo(2);
    }

    @Test
    public void shouldReadYear() throws IOException {
        final URL serverUrl = prepareMockServer(JsonHelper.GET_YEAR);
        final BikeBook api = createJsonApi(serverUrl);

        final List<Year> year = api.getManufacturerByYear(serverUrl.toString(), "Cinelli", "2014");
        assertThat(year).isNotNull();
        assertThat(year.size()).isEqualTo(22);
    }

    @Test
    public void shouldReadIndex() throws IOException {
        final URL serverUrl = prepareMockServer(JsonHelper.GET_INDEX);
        final BikeBook api = createJsonApi(serverUrl);

        final Map<String, Map<String, List<Year>>> index = api.getIndex(serverUrl.toString());
        assertThat(index).isNotNull();
        assertThat(index.size()).isEqualTo(2);

//        for (Map.Entry<String, Map<String, List<Year>>> entry : index.entrySet()) {
//            String key = entry.getKey();
//            Map<String, List<Year>> tab = entry.getValue();
//            // do something with key and/or tab
//        }

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
