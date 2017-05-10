package mco364;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void groupBy() {
        //3 apple, 2 banana, others 1
        List<String> items
                = Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        Map<String, Long> result
                = items.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        System.out.println(result);

    }

    public static void map(String[] args) {
        String[] sArray = {"apples", "peaches", "bananas", "apples", "kale", "fig"};
        List<String> sList = Arrays.asList(sArray);

        sList.stream()
                .filter(s -> s.contains("a"))
                .map(s -> s.length()) // same as String::length
                .map(Math::sqrt) // method group equiv to i -> Math.sqrt(i)
                .distinct()
                .forEach(i -> System.out.println(i)); // System.out::println

    }

    public static void main(String[] args) throws FileNotFoundException {
        // scan genesis and output letter and word histogram 
        Scanner sc = new Scanner(new File("genesis.txt"));
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
            sb.append(newLine);
        }
        String text = sb.toString();

        //System.out.println(text);
        List<Character> cList = new ArrayList<Character>();
        for (char c : text.toUpperCase().toCharArray()) {
            cList.add(c);
        }

        Map<Character, Long> map = cList.stream()
                .filter(Character::isAlphabetic)
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
                );
        System.out.println();

        String[] split = text.toUpperCase().split("[^a-zA-Z']+");

        List<String> cList2 = Arrays.asList(split);

        Map<String,Long> map2 = cList2.stream()
                .collect( Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
        System.out.println(map2);
    }
}
