package com.malmstein.bikebook.json.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StreamUrlsResponse {

    public static class Urls {

        public static class Details {

            @SerializedName("type")
            private String type;

            @SerializedName("transport")
            private String transport;

            @SerializedName("stream_proto")
            private String streamProto;

            public String getType() {
                return type;
            }

            public String getTransport() {
                return transport;
            }

            public String getStreamProto() {
                return streamProto;
            }
        }

        @SerializedName("url")
        private String url;

        @SerializedName("details")
        private Details details;

        @SerializedName("channel")
        private String channel;

        public String getUrl() {
            return url;
        }

        public Details getDetails() {
            return details;
        }

        public String getChannel() {
            return channel;
        }
    }

    @SerializedName("cmd")
    private String command;

    @SerializedName("language")
    private String language;

    @SerializedName("success")
    private boolean success;

    @SerializedName("token")
    private String token;

    @SerializedName("urls")
    private List<Urls> urls;

    public String getLanguage() {
        return language;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public List<Urls> getUrls() {
        return urls;
    }

    public String getCommand() {
        return command;
    }
}
