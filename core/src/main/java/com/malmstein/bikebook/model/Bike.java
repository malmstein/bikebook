package com.malmstein.bikebook.model;

import com.malmstein.bikebook.json.responses.BikeDetailJson;

import java.util.Collections;
import java.util.List;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Bike {

    public abstract Details getDetails();
    public abstract List<Component> getComponents();

    //NOTE: Parameters need to be in the order of the access methods above!
    public static Bike create(final Details details, final List<Component> components) {
        return new AutoValue_Bike(details, components);
    }

    public static Bike from(BikeDetailJson.Bike details, List<BikeDetailJson.Component> components){
         return create(Details.from(details), createComponents(components));
    }

    private static List<Component> createComponents(List<BikeDetailJson.Component> componentList){
        if (componentList == null) {
            return Collections.emptyList();
        }

        List<Component> components = Collections.emptyList();

        for (BikeDetailJson.Component component : componentList) {
            components.add(Component.from(component));
        }

        return Collections.unmodifiableList(components);
    }
}
