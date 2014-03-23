package com.malmstein.bikebook.model;

import com.google.gson.annotations.SerializedName;

public class Bike {

    public static class Component {
        @SerializedName("component_type")
        private String componentType;

        @SerializedName("description")
        private String description;

        @SerializedName("cgroup")
        private String cgroup;

        public String getComponentType() { return componentType; }

        public String getDescription() {
            return description;
        }

        public String getCgroup() {
            return cgroup;
        }

        @Override
        public int hashCode() {
            return com.google.common.base.Objects.hashCode(componentType, description, cgroup);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Component other = (Component) obj;

            return com.google.common.base.Objects.equal(this.componentType, other.componentType)
                    && com.google.common.base.Objects.equal(this.description, other.description)
                    && com.google.common.base.Objects.equal(this.cgroup, other.cgroup);
        }
    }

    @SerializedName("components")
    private java.util.ArrayList<Component> components;

    @SerializedName("frame_model")
    private String frameModel;

    @SerializedName("manufacturer")
    private String manufacturer;

    @SerializedName("year")
    private int year;

    @SerializedName("description")
    private String description;

    @SerializedName("paint_description")
    private String paintDescription;

    @SerializedName("manufacturers_url")
    private String manufacturersUrl;

    public java.util.ArrayList<Component> getComponents() { return components; }

    public String getFrameModel() { return frameModel; }

    public String getManufacturer() { return manufacturer; }

    public int getYear() { return year; }

    public String getDescription() { return description; }

    public String getPaintDescription() { return paintDescription; }

    public String getManufacturersUrl() { return manufacturersUrl; }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(frameModel, manufacturer, year, description, paintDescription, manufacturersUrl);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bike other = (Bike) obj;

        return com.google.common.base.Objects.equal(this.frameModel, other.frameModel)
                && com.google.common.base.Objects.equal(this.manufacturer, other.manufacturer)
                && com.google.common.base.Objects.equal(this.year, other.year)
                && com.google.common.base.Objects.equal(this.description, other.description)
                && com.google.common.base.Objects.equal(this.paintDescription, other.paintDescription)
                && com.google.common.base.Objects.equal(this.manufacturersUrl, other.manufacturersUrl);
    }


}
