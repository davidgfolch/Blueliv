package com.dgf.blueliv.search;

import com.dgf.blueliv.model.Record;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

public abstract class FileSearch {

    public static final String CITY = "CITY";
    public static final String ID = "ID";

    protected static final String EMPTY = "";
    protected static final String COMMA = ",";
    protected static final String NEW_LINE = "\n";

    public static final String F1 = "F1";
    public static final String F2 = "F2";
    public static final String SPLITTER_1=",";
    public static final String SPLITTER_2=" ; ";
    public static final String DASH = "-";
    protected Map<String,String> results = new HashMap<>();

    public static FileSearch getInstance(String type) {
        switch (type) {
            case CITY: return new CitySearch();
            case ID: return new IdSearch();
            default: throw new IllegalStateException("Type of search is not supported: type="+type);
        }
    }

    public String find(String file, String value) throws IOException {
        results = new HashMap<>();
        String splitter=SPLITTER_1;
        try (LineNumberReader in = new LineNumberReader(new InputStreamReader(new FileInputStream(file)))) {
            do {
                final String line = in.readLine();
                if (line == null) {
                    break;
                }
                switch (line) {
                    case F1: splitter = SPLITTER_1; break;
                    case F2: splitter = SPLITTER_2; break;
                    default: parseLineForType(line,value, splitter);
                }
            } while (true);
        }
        return formatResult();
    }

    public abstract String formatResult();
    public abstract void parseLineForType(String line, String value, String splitter);

    public Record parseLine(String line, String splitter) {
        String[] data = line.split(splitter);
        return new Record(data[0].substring(2) //Remove "D "
                ,data[1]
                ,data[2].replaceAll(DASH,EMPTY) //Remove dash
        );
    }

}
