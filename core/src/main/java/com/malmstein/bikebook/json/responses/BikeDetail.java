package com.malmstein.bikebook.json.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BikeDetail {

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

    public static class Bike {
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

        @SerializedName("rear_wheel_bsd")
        private int rearWheelBsd;

        @SerializedName("rear_tire_narrow")
        private String rearTireNarrow;

        @SerializedName("stock_photo_url")
        private String stockPhoto;

        @SerializedName("stock_photo_small")
        private String stockSmallPhoto;

        public String getFrameModel() {
            return frameModel;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public int getYear() {
            return year;
        }

        public String getDescription() {
            return description;
        }

        public String getPaintDescription() {
            return paintDescription;
        }

        public String getManufacturersUrl() {
            return manufacturersUrl;
        }

        public int getRearWheelBsd() {
            return rearWheelBsd;
        }

        public String getRearTireNarrow() {
            return rearTireNarrow;
        }

        public String getStockPhotoUrl() {
            return stockPhoto;
        }

        public String getStockSmallPhotoUrl() {
            return stockSmallPhoto;
        }

        @Override
        public int hashCode() {
            return com.google.common.base.Objects.hashCode(frameModel, manufacturer, year, description, paintDescription, manufacturersUrl, rearWheelBsd, rearTireNarrow, stockPhoto, stockSmallPhoto);
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

            return com.google.common.base.Objects.equal(this.frameModel, other.getFrameModel())
                    && com.google.common.base.Objects.equal(this.manufacturer, other.getManufacturer())
                    && com.google.common.base.Objects.equal(this.year, other.getYear())
                    && com.google.common.base.Objects.equal(this.description, other.getDescription())
                    && com.google.common.base.Objects.equal(this.paintDescription, other.getPaintDescription())
                    && com.google.common.base.Objects.equal(this.manufacturersUrl, other.getManufacturersUrl())
                    && com.google.common.base.Objects.equal(this.rearWheelBsd, other.getRearWheelBsd())
                    && com.google.common.base.Objects.equal(this.rearTireNarrow, other.getRearTireNarrow())
                    && com.google.common.base.Objects.equal(this.stockPhoto, other.getStockPhotoUrl())
                    && com.google.common.base.Objects.equal(this.stockSmallPhoto, other.getStockSmallPhotoUrl());
        }
    }

    @SerializedName("bike")
    private Bike bike;

    public ArrayList<Component> getComponents(){
        return this.components;
    }

    public Bike getBike(){
        return this.bike;
    }

}
