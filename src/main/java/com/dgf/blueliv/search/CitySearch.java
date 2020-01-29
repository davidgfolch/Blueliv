package com.dgf.blueliv.search;

import com.dgf.blueliv.model.Record;

public class CitySearch extends FileSearch {

    @Override
    public void parseLineForType(String line, String value, String splitter) {
        Record data=parseLine(line, splitter);
        if (value.equals(data.getCity()) && !results.containsKey(data.getId())) results.put(data.getId(), data.getName());
    }

    @Override
    public String formatResult() {
        StringBuilder sb = new StringBuilder();
        results.forEach((k, v) -> sb.append(k).append(COMMA).append(v).append(NEW_LINE));
        return sb.toString();
    }

}
