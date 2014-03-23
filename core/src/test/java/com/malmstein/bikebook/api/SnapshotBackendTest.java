package com.malmstein.bikebook.api;

import com.malmstein.bikebook.http.HttpClient;
import com.malmstein.bikebook.http.OkConnectionFactory;
import com.malmstein.bikebook.http.SawickiHttpClient;
import com.malmstein.bikebook.json.GsonJsonReader;
import com.squareup.okhttp.OkHttpClient;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Theories.class)
public final class SnapshotBackendTest extends BackendTestBase {

    private static final String SNAPSHOT_BASEDIR = "/bikebook";

    private static HttpClient httpClient;
    private static GsonJsonReader jsonReader;
    private static SnapshotServer server;

    @BeforeClass
    public static void setupServer() throws IOException {
        server = new SnapshotServer(SNAPSHOT_BASEDIR);
        server.start();

        httpClient = new SawickiHttpClient(new OkConnectionFactory(new OkHttpClient()));
        jsonReader = new GsonJsonReader();
    }

    @AfterClass
    public static void tearServer() throws IOException {
        System.out.println("tearing down server!");
        server.stop();
    }

    @Override
    @Theory
    public void shouldParseManufacturers() throws IOException {
        super.shouldParseManufacturers();
    }

    @Override
    protected Backend createApi() {
        return new Backend(server.baseUrl(), httpClient, jsonReader);
    }

}
