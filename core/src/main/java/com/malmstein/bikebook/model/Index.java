package com.malmstein.bikebook.model;

import com.google.auto.value.AutoValue;
import com.malmstein.bikebook.json.responses.YearJson;

import java.util.*;

@AutoValue
public abstract class Index {

    public abstract List<Manufacturer> getManufacturerList();

    //NOTE: Parameters need to be in the order of the access methods above!
    public static Index create(final List<Manufacturer> manufacturerList) {
        return new AutoValue_Index(manufacturerList);
    }

    public static Index from(Map<String, Map<String, List<YearJson>>> index) {

        List<Manufacturer> manufacturers = Collections.emptyList();

        for (Map.Entry<String, Map<String, List<YearJson>>> entry : index.entrySet()) {
            manufacturers.add(Manufacturer.from(entry));
        }

        return create(manufacturers);
    }


}
