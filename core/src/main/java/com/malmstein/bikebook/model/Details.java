package com.malmstein.bikebook.model;

import com.google.auto.value.AutoValue;
import com.malmstein.bikebook.json.responses.BikeDetailJson;
import com.malmstein.bikebook.json.responses.ModelJson;

import java.util.List;
import java.util.Map;

@AutoValue
public abstract class Details {

    public abstract String getFrameModel();

    public abstract String getManufacturer();

    public abstract int getYear();

    public abstract String getDescription();

    public abstract String getPaintDescription();

    public abstract String getManufacturersUrl();

    public abstract int getRearWheelBsd();

    public abstract String getRearTireNarrow();

    public abstract String getStockPhoto();

    public abstract String getStockSmallPhoto();

    //NOTE: Parameters need to be in the order of the access methods above!
    private static Details create(String frameModel, String manufacturer, int year, String description, String paintDescription, String manufacturersUrl, int rearWheelBsd, String rearTireNarrow, String stockPhotoUrl, String stockSmallPhotoUrl) {
        return new AutoValue_Details(frameModel, manufacturer, year, description, paintDescription, manufacturersUrl, rearWheelBsd, rearTireNarrow, stockPhotoUrl, stockSmallPhotoUrl);
    }

    public static Details from(BikeDetailJson.Bike bikeDetails) {
        return Details.create(bikeDetails.getFrameModel(),
                bikeDetails.getManufacturer(),
                bikeDetails.getYear(),
                bikeDetails.getDescription(),
                bikeDetails.getPaintDescription(),
                bikeDetails.getManufacturersUrl(),
                bikeDetails.getRearWheelBsd(),
                bikeDetails.getRearTireNarrow(),
                bikeDetails.getStockPhotoUrl(),
                bikeDetails.getStockSmallPhotoUrl());
    }



}
