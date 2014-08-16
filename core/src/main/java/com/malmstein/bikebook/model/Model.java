package com.malmstein.bikebook.model;

import com.google.auto.value.AutoValue;
import com.malmstein.bikebook.json.responses.ModelJson;

@AutoValue
public abstract class Model {

    public abstract String getId();
    public abstract String getText();
    public abstract String getName();

    //NOTE: Parameters need to be in the order of the access methods above!
    public static Model create(final String id, final String text, final String name) {
        return new AutoValue_Model(id, text, name);
    }

    public static Model from(ModelJson modelJson){
        return create(modelJson.getId(), modelJson.getText(), modelJson.getName());
    }

}
