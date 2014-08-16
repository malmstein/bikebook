package com.malmstein.bikebook.json.responses;

import java.util.ArrayList;

public class BikeDetailJson {

    public static class Component {
        private String componentType;

        private String description;

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

    private java.util.ArrayList<Component> components;

    public static class Bike {
        private String frameModel;

        private String manufacturer;

        private int year;

        private String description;

        private String paintDescription;

        private String manufacturersUrl;

        private int rearWheelBsd;

        private String rearTireNarrow;

        private String stockPhoto;

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

    private Bike bike;

    public ArrayList<Component> getComponents(){
        return this.components;
    }

    public Bike getBike(){
        return this.bike;
    }

}
