package com.malmstein.bikebook.model;

import com.google.auto.value.AutoValue;
import com.malmstein.bikebook.json.responses.YearJson;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@AutoValue
public abstract class Year {

    public abstract String getYearName();
    public abstract List<Model> getModels();

    //NOTE: Parameters need to be in the order of the access methods above!
    public static Year create(final String yearName, List<Model> modelList) {
        return new AutoValue_Year(yearName, modelList);
    }

    public static Year from (Map.Entry<String, List<YearJson>> yearEntry){
        String year = yearEntry.getKey();
        List<Model> modelList = Collections.emptyList();
        for (YearJson yearJsons : yearEntry.getValue()) {
            modelList.add(Model.from(yearJsons));
        }
        return create(year, modelList);
    }

}
