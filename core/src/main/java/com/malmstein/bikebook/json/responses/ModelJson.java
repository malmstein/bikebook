package com.malmstein.bikebook.json.responses;

public class ModelJson {

    private String id;
    private String text;
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
        final ModelJson other = (ModelJson) obj;

        return com.google.common.base.Objects.equal(this.id, other.id)
                && com.google.common.base.Objects.equal(this.text, other.text)
                && com.google.common.base.Objects.equal(this.name, other.name);
    }
}