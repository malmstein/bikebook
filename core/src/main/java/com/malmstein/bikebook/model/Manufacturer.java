package com.malmstein.bikebook.model;

import com.google.auto.value.AutoValue;
import com.malmstein.bikebook.json.responses.YearJson;

import java.util.Collection;
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

    public static Manufacturer from (Map.Entry<String, Map<String, List<YearJson>>> manufacturerEntry){
        return Manufacturer.create(manufacturerEntry.getKey(), createYears(manufacturerEntry.getValue());
    }

    private static List<Year> createYears(final Map<String, List<YearJson>> years) {
        if (years == null) {
            return Collections.emptyList();
        }

        List<Year> results = Collections.emptyList();

        return Collections.unmodifiableList(results);
    }


}
