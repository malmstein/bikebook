package com.malmstein.bikebook.api;

import com.malmstein.bikebook.json.responses.Manufacturer;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(Theories.class)
public abstract class BackendTestBase {

    protected abstract Backend createApi();

    @Theory
    public void shouldParseManufacturers() throws IOException {
        Backend backend = createApi();
        final List<Manufacturer> index = backend.readManifest();
        assertThat(index).isNotEmpty();
//        for (final Year concertManifest : index) {
//            final Concert concert = backend.readConcertDetails(manifest, concertManifest);
//            assertConcert(concert);
//        }
    }

}
