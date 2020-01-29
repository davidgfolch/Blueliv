package com.dgf.blueliv;

import com.dgf.blueliv.search.FileSearch;

import java.io.IOException;

public class Main {

    private Main() {}

    public static void main(String[] args) throws IOException {
        String file=args[0];
        String type=args[1];
        String searchValue=args[2];
        FileSearch fileSearch = FileSearch.getInstance(type);
        System.out.println("Found:\n"+ fileSearch.find(file, searchValue));
    }
}
