package com.malmstein.bikebook.api;

import com.malmstein.bikebook.json.GsonJsonReader;
import com.malmstein.bikebook.json.responses.BikeDetailJson;
import com.malmstein.bikebook.json.responses.ModelJson;
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

        final BikeDetailJson bikeDetailJson = api.getBike(serverUrl.toString(), "Fuji", "2014", "outland_29_1_3");
        assertThat(bikeDetailJson).isNotNull();
        assertThat(bikeDetailJson.getBike().getManufacturer()).isEqualTo("Fuji");
        assertThat(bikeDetailJson.getBike().getYear()).isEqualTo(2014);
    }

    @Test
    public void shouldReadManufacturer() throws IOException {
        final URL serverUrl = prepareMockServer(JsonHelper.GET_MANUFACTURER);
        final BikeBook api = createJsonApi(serverUrl);

        final Map<String, List<ModelJson>> manufacturer = api.getManufacturer(serverUrl.toString(), "Cinelli");
        assertThat(manufacturer).isNotNull();
        assertThat(manufacturer.size()).isEqualTo(2);
    }

    @Test
    public void shouldReadYear() throws IOException {
        final URL serverUrl = prepareMockServer(JsonHelper.GET_YEAR);
        final BikeBook api = createJsonApi(serverUrl);

        final List<ModelJson> modelJson = api.getManufacturerByYear(serverUrl.toString(), "Cinelli", "2014");
        assertThat(modelJson).isNotNull();
        assertThat(modelJson.size()).isEqualTo(22);
    }

    @Test
    public void shouldReadIndex() throws IOException {
        final URL serverUrl = prepareMockServer(JsonHelper.GET_INDEX);
        final BikeBook api = createJsonApi(serverUrl);

        final Map<String, Map<String, List<ModelJson>>> index = api.getIndex(serverUrl.toString());
        assertThat(index).isNotNull();
        assertThat(index.size()).isEqualTo(2);

        for (Map.Entry<String, Map<String, List<ModelJson>>> entry : index.entrySet()) {
            String key = entry.getKey();
            Map<String, List<ModelJson>> tab = entry.getValue();
            assertThat(tab.size()).isGreaterThan(0);
        }

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
