package com.malmstein.bikebook.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.malmstein.bikebook.json.responses.BikeDetail;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.logging.Logger;

public final class GsonJsonReader implements JsonReader {

    private static class ImageResizedAdapter implements JsonDeserializer<BikeDetail> {

        private Gson gson = new Gson();

        @Override
        public BikeDetail deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (!json.isJsonObject()) {
                return null;
            }
            return gson.fromJson(json, BikeDetail.class);
        }
    }

    private static final Logger LOGGER = Logger.getLogger("gson");

    private final Gson gson;

    public GsonJsonReader() {
        this.gson = createGsonBuilder().create();
    }

    @Override
    public <T> T fromJson(InputStream input, Class<T> clazz) {
        try {
            LOGGER.info("start decoding stream");
            return gson.fromJson(new InputStreamReader(input), clazz);
        } catch (JsonParseException e) {
            //should we catch that here at all? not if the stacktrace has enough info already!
            throw new RuntimeException("Failed reading class " + clazz.getName(), e);
        } finally {
            LOGGER.info("done decoding stream");
        }
    }

    @Override
    public <T> T fromJson(InputStream input, Type type) {
        try {
            LOGGER.info("start decoding stream");
            return gson.fromJson(new InputStreamReader(input), type);
        } catch (JsonParseException e) {
            //should we catch that here at all? not if the stacktrace has enough info already!
            throw new RuntimeException("Failed reading class " + type.getClass(), e);
        } finally {
            LOGGER.info("done decoding stream");
        }
    }

    private static GsonBuilder createGsonBuilder() {
        return new GsonBuilder().registerTypeAdapter(BikeDetail.class, new ImageResizedAdapter());
    }

}
