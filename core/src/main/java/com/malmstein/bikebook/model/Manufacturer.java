package com.malmstein.bikebook.model;

import com.google.auto.value.AutoValue;
import com.malmstein.bikebook.json.responses.ModelJson;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@AutoValue
public abstract class Manufacturer {

    public abstract String getName();
    public abstract List<Year> getModels();

    //NOTE: Parameters need to be in the order of the access methods above!
    public static Manufacturer create(final String name, final List<Year> modelList) {
        return new AutoValue_Manufacturer(name, modelList);
    }

    public static Manufacturer from (Map.Entry<String, Map<String, List<ModelJson>>> manufacturerEntry){
        return Manufacturer.create(manufacturerEntry.getKey(), createYears(manufacturerEntry.getValue()));
    }

    private static List<Year> createYears(final Map<String, List<ModelJson>> yearsMap) {
        if (yearsMap == null) {
            return Collections.emptyList();
        }

        List<Year> years = Collections.emptyList();

        for (Map.Entry<String, List<ModelJson>> entry : yearsMap.entrySet()) {
            years.add(Year.from(entry));
        }

        return Collections.unmodifiableList(years);
    }


}
