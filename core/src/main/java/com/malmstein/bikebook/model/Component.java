package com.malmstein.bikebook.model;

import com.malmstein.bikebook.json.responses.BikeDetailJson;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Component {

    public abstract String getComponentType();

    public abstract String getDescription();

    public abstract String getCgroup();

    //NOTE: Parameters need to be in the order of the access methods above!
    public static Component create(final String name, final String description, final String cgroup) {
        return new AutoValue_Component(name, description, cgroup);
    }

    public static Component from(BikeDetailJson.Component component) {
        return Component.create(component.getComponentType(), component.getDescription(), component.getCgroup());
    }

}
