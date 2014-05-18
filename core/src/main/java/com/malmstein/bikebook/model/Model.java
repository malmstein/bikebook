package com.malmstein.bikebook.model;

import com.google.auto.value.AutoValue;
import com.malmstein.bikebook.json.responses.YearJson;

@AutoValue
public abstract class Model {

    public abstract String getId();
    public abstract String getName();

    //NOTE: Parameters need to be in the order of the access methods above!
    public static Model create(final String id, final String name) {
        return new AutoValue_Model(id, name);
    }

    public static Model from(YearJson yearJson){
        return create(yearJson.getId(), yearJson.getName());
    }

}
