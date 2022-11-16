package search;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[1]);
        SimpleSearch simpleSearch = new SimpleSearch();
        simpleSearch.constructor(file);
    }
}
