package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class SimpleSearch {
    public void constructor (File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(file);
        Map<Integer, String> map = new HashMap<>();
        int i = 0;
        while(scan.hasNextLine()){
            map.put(i, scan.nextLine().trim());
            i++;
        }
        scan.close();
        boolean stop = true;
        while (stop) {
            message();
            int action = scanner.nextInt();
            System.out.println();
            switch (action) {
                case 0:
                    exit();
                    break;
                case 1:
                    simpleSearch(map);
                    break;
                case 2:
                    printAll(map);
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }
        }
    }
    public static boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }

    public void simpleSearch (Map<Integer, String> map) {
        Scanner scanner = new Scanner(System.in);
         System.out.println("Select a matching strategy: ALL, ANY, NONE");
         String action = scanner.nextLine();
         System.out.println("Enter a name or email to search all suitable people.");
         String value = scanner.nextLine();
         String[] values = value.split(" ");
         ArrayList<String> list = new ArrayList<>();
         int n = 0;
         switch (action) {
             case "ALL":
                 for (int i = 0; i < map.size(); i++) {
                     if (map.containsValue(value.toLowerCase())) {
                         list.add(map.get(i));
                         n++;
                     }
                 }
                 System.out.println(n + " people founded:");
                 list.forEach(System.out::println);
                 break;
             case "ANY":
                 for (int j = 0; j < values.length; j++) {
                     for (int i = 0; i < map.size(); i++) {
                         String[] words = map.get(i).split("\\s+");
                         for (String word: words) {
                             if (values[j].equalsIgnoreCase(word)) {
                                 list.add(map.get(i));
                                 n++;
                             }
                         }
                     }
                 }
                 System.out.println(n + " people founded:");
                 list.forEach(System.out::println);
                 break;
             case "NONE":
                 for (int i = 0; i < map.size(); i++) {
                     list.add(map.get(i));
                 }
                 for (int j = 0; j < values.length; j++) {
                     String m = values[j].toLowerCase();
                     list.removeIf(s -> s.toLowerCase().contains(m));

                 }
                 System.out.println(list.size() + " people founded:");
                 list.forEach(System.out::println);
                 break;
             default:
                 break;
         }
    }
    public String searchIn (String[] text) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.next();
        for (int i = 0; i < text.length; i++) {
            if (text[i].contains(word)) {
                return String.valueOf(i + 1);
            }
        }
        return "Not found";
    }
    public void exit () {
        System.exit(0);
    }
    public void message () {
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }
    public void printAll (Map<Integer, String> map) {
        System.out.println("=== List of people ===" + map.size());
        for (int i = 0; i < map.size(); i++) {
            System.out.println(map.get(i));
        }
        System.out.println();
    }
}
