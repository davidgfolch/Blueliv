package com.dgf.blueliv.search;

import com.dgf.blueliv.model.Record;

public class IdSearch extends FileSearch {

    @Override
    public void parseLineForType(String line, String value, String splitter) {
        Record data=parseLine(line,splitter);
        if (value.equals(data.getId()) && !results.containsKey(data.getCity())) results.put(data.getCity(), EMPTY);
    }

    @Override
    public String formatResult() {
        StringBuilder sb = new StringBuilder();
        results.forEach((k, v) -> sb.append(k).append(NEW_LINE));
        return sb.toString();
    }

}
