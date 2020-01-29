package com.dgf.blueliv;

import org.testng.annotations.Test;

import java.io.IOException;

import static com.dgf.blueliv.search.FileSearch.CITY;

public class MainTest {

    public static final String FILE = "file.test.txt";

    @Test(expectedExceptions = { IllegalStateException.class }, expectedExceptionsMessageRegExp = "Type of search is not supported: type=invalid")
    public void mainException() throws IOException {
        Main.main(new String[] {FILE,"invalid","BARCELONA"});
    }
    @Test(expectedExceptions = { NullPointerException.class })
    public void mainException2() throws IOException {
        Main.main(new String[] {FILE,null,"BARCELONA"});
    }

    @Test()
    public void main() throws IOException {
        Main.main(new String[] {FILE,CITY,"BARCELONA"});
    }
}
