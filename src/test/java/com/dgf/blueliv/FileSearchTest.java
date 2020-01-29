package com.dgf.blueliv;

import com.dgf.blueliv.search.FileSearch;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.dgf.blueliv.search.FileSearch.CITY;
import static com.dgf.blueliv.search.FileSearch.ID;
import static org.testng.Assert.assertEquals;

public class FileSearchTest {

    public static final String FILE = "file.test.txt";

    @Test
    public void idSearch() throws IOException {
        assertEquals("LAS VEGAS\n" +
                "NEW YORK\n", FileSearch.getInstance(ID).find(FILE,"09877359D"));
    }
    @Test
    public void citySearch() throws IOException {
        assertEquals("54808168L,Shelley Payne\n" +
                "23803975X,Emilio Warner\n" +
                "44340637H,Renee Anderson\n" +
                "04217040J,Lowell Miles\n" +
                "93654902Y,Erica Burns\n" +
                "54315871Z,Rhonda Hopkins\n" +
                "69429384C,Russell Pope\n" +
                "58204706D,Peter Daniel\n" +
                "84604786E,Ruben Daniels\n" +
                "10863096N,Johnathan Cooper\n", FileSearch.getInstance(CITY).find(FILE,"BARCELONA"));
    }
}
