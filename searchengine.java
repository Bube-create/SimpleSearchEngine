package search;

import java.io.*;
import java.util.*;

public class Main {
   
    static Scanner scan = new Scanner(System.in);
  

    public static void main(String[] args) {

        String[] array = fileReader(args[1]);

        boolean exit = false;
        while (!exit) {
            int res = menu();
            if (res == 1) {
                find(array);
            } else if (res == 2) {
                print(array);
            } else if (res == 0) {
                System.out.println("");
                System.out.println("Bye!");
                exit = true;
            } else {
                System.out.println("Incorrect option! Try again.");
            }
        }

    }


    public static String[] fileReader(String file) {
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (strLine != null) {
                strLine = br.readLine();
                sb.append(strLine);
                sb.append(System.lineSeparator());
                if (strLine == null)
                    break;
                list.add(strLine);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Unable to read the file.");
        }
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static int menu() {
        System.out.println("");
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
        System.out.println("");

        int response = Integer.parseInt(scan.nextLine());
        return response;
    }

    public static void find(String[] array) {

        System.out.println("");
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String selection = scan.nextLine();

        switch (selection) {
            case "ALL" :
                allImproved(array);
                break;
            case "ANY" :
                anyImproved(array);
                break;
            case "NONE" :
                noneImproved(array);
                break;
            default:
                break;
        }
    }


    public static void anyImproved(String[] array) {
        System.out.println("Enter a name or email to search all suitable people. ");
        String pple = scan.nextLine().toLowerCase();
        String[] split = pple.split(" ");
        HashMap<String, ArrayList<Integer>> data = findMod(array);
        ArrayList<String> ss = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (data.containsKey(split[i])) {
                ArrayList<Integer> arr = data.get(split[i]);
                for (int j = 0; j < arr.size(); j++) {
                    ss.add(array[arr.get(j)]);
                }
            }
        }
        for (String i :
                ss) {
            System.out.println(i);
        }
    }

    public static void noneImproved(String[] array) {
        System.out.println("Enter a name or email to search all suitable people. ");
        String pple = scan.nextLine().toLowerCase();
        String[] split = pple.split(" ");
        boolean found = false;
        HashMap<String, ArrayList<Integer>> data = findMod(array);
        HashSet<String> ss = new HashSet<>();
        for (String i : array) {
            ss.add(i);
        }
        HashSet<String> ss2 = new HashSet<>();

        for (int i = 0; i < split.length; i++) {
            if (data.containsKey(split[i])) {
                ArrayList<Integer> arr = data.get(split[i]);
                for (int j = 0; j < arr.size(); j++) {
                    ss2.add(array[arr.get(j)]);
                }
            }
        }


        ss.removeAll(ss2);
        for (String i :
                ss) {
            System.out.println(i);
        }

        if (ss.size() == 0) {
            System.out.println("No matching people found.");
        }
    }

    public static void allImproved(String[] array) {
        System.out.println("Enter a name or email to search all suitable people. ");
        String pple = scan.nextLine().toLowerCase();
        String[] split = pple.split(" ");
        HashMap<String, ArrayList<Integer>> data = findMod(array);
        ArrayList<String> ss = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (data.containsKey(split[i])) {
                ArrayList<Integer> arr = data.get(split[i]);
                for (int j = 0; j < arr.size(); j++) {
                    ss.add(array[arr.get(j)]);
                }
            }
        }
        boolean contains = false;
        HashSet<String> ff = new HashSet<>();
        int truecount = 0;
        for (int i = 0; i < ss.size(); i++) {
            for (int j = 0; j < split.length; j++) {
                if (ss.get(i).toLowerCase().contains(split[j])) {
                    contains = true;
                    truecount++;
                } else {
                    contains = false;
                    truecount = 0;
                }
            }
            if (contains == true && truecount == split.length) {
                ff.add(ss.get(i));
            }
            truecount = 0;

        }
        for (String i: ff) {
            System.out.println(i);
        }
    }





    public static HashMap<String, ArrayList<Integer>> findMod(String[] array) {
        HashMap<String, ArrayList<Integer>> result = new HashMap<>();
        ArrayList<Integer> temp = new ArrayList<>();

        int index = 0;
        int i = 0;
        while (i < array.length) {
            for (String j : array[i].split(" ")) {
                temp.add(index);
                if (result.containsKey(j.toLowerCase())) {
                    result.get(j.toLowerCase()).add(index);
                } else {
                    result.put(j.toLowerCase(), temp);
                }
                temp = new ArrayList<>();
            }
            index++;
            i++;
        }
        return result;

    }

    public static void print(String[] array) {
        System.out.println("");
        System.out.println("=== List of people ===");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

}
