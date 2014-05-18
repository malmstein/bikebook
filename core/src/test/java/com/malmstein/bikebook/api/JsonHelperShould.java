package com.malmstein.bikebook.api;

import com.malmstein.bikebook.util.JsonHelper;

import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JsonHelperShould {

    @Test
    public void return_the_index_json() throws IOException {
        InputStreamReader in = new InputStreamReader(JsonHelper.index(), "UTF-8");
        assertThat(in, is(in));
    }
}
