package com.malmstein.bikebook.api;

import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Serves the API snapshots for offline testing.
 */
public class SnapshotServer {

    private final String snapshotDir;
    private MockWebServer mockWebServer;

    public SnapshotServer() {
        this("/com/malmstein/bikebook/api");
    }

    private SnapshotServer(final String snapshotDir) {
        this.snapshotDir = snapshotDir;
    }

    public URL start() throws IOException {
        if (mockWebServer != null) {
            throw new IllegalStateException("server already running!");
        }
        mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(final RecordedRequest request) {
                try {
                    String path = request.getPath();
                    String fileName = path.substring(path.indexOf("/static"));
                    return tweakResponse(new MockResponse()
                            .setResponseCode(HttpURLConnection.HTTP_OK)
                            .setBody(readFile(fileName)));
                } catch (Exception e) {
                    e.printStackTrace();
                    return new MockResponse().setBody("error " + e.getMessage());
                }
            }
        });
        mockWebServer.play();
        return mockWebServer.getUrl("/");
    }

    protected MockResponse tweakResponse(final MockResponse response) {
        return response;
    }

    public URL baseUrl() {
        if (mockWebServer == null) {
            throw new IllegalStateException("server is not running!");
        }
        return mockWebServer.getUrl("/");
    }

    public void stop() throws IOException {
        mockWebServer.shutdown();
        mockWebServer = null;
    }

    private String readFile(final String path) throws IOException {
        InputStream resourceAsStream = BikeBookApiTest.class.getResourceAsStream(snapshotDir + path);
        InputStreamReader reader = new InputStreamReader(resourceAsStream);
        BufferedReader buffered = new BufferedReader(reader);
        String line;
        StringWriter writer = new StringWriter();
        while ((line = buffered.readLine()) != null) {
            writer.write(line);
        }
        resourceAsStream.close();
        return writer.toString();
    }

}
