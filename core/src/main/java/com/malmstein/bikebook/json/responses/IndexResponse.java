package com.malmstein.bikebook.json.responses;

import java.util.List;
import java.util.Map;

public class IndexResponse {

    public Map<String, Map<String, List<ModelJson>>> index;

    public void setIndex(Map<String, Map<String, List<ModelJson>>> index) {
        this.index = index;
    }

    public Map<String, Map<String, List<ModelJson>>> getIndex() {
        return index;
    }
}
