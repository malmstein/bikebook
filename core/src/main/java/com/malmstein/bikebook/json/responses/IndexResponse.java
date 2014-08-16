package com.malmstein.bikebook.json.responses;

import java.util.List;
import java.util.Map;

public class IndexResponse {

    public Map<String, Map<String, List<YearJson>>> index;

    public void setIndex(Map<String, Map<String, List<YearJson>>> index) {
        this.index = index;
    }

    public Map<String, Map<String, List<YearJson>>> getIndex() {
        return index;
    }
}
