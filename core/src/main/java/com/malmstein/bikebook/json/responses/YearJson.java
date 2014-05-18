package com.malmstein.bikebook.json.responses;

import com.google.gson.annotations.SerializedName;

public class YearJson {

    @SerializedName("id")
    private String id;

    @SerializedName("text")
    private String text;

    @SerializedName("name")
    private String name;

    public String getId() { return id; }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(id, text, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final YearJson other = (YearJson) obj;

        return com.google.common.base.Objects.equal(this.id, other.id)
                && com.google.common.base.Objects.equal(this.text, other.text)
                && com.google.common.base.Objects.equal(this.name, other.name);
    }
}