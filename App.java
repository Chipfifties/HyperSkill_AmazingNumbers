package numbers;

import java.util.*;
import java.util.stream.*;

public class App {
    private static final List<String> PROPERTIES = List.of("buzz", "duck", "palindromic",
            "gapful", "spy", "even", "odd", "square", "sunny", "jumping", "happy",
            "sad", "-buzz", "-duck", "-palindromic", "-gapful", "-spy", "-even", "-odd",
            "-square", "-sunny", "-jumping", "-happy", "-sad");
    private static final List<String> EXL_PAIR_ONE = List.of("even", "odd");
    private static final List<String> EXL_PAIR_TWO = List.of("duck", "spy");
    private static final List<String> EXL_PAIR_THREE = List.of("sunny", "square");
    private static final List<String> EXL_PAIR_FOUR = List.of("happy", "sad");
    private static final List<String> NEG_EXL_PAIR_ONE = List.of("-even", "-odd");
    private static final List<String> NEG_EXL_PAIR_TWO = List.of("-duck", "-spy");
    private static final List<String> NEG_EXL_PAIR_THREE = List.of("-sunny", "-square");
    private static final List<String> NEG_EXL_PAIR_FOUR = List.of("-happy", "-sad");

    public static void runApp() {
        System.out.print("\nEnter a request: ");
        String[] input = new Scanner(System.in).nextLine().split(" ");

        if (input[0].equals("")) {
            Output.printInstructions(0);
        } else if (input[0].equals("0")) {
            System.out.println("\nGoodbye!");
            System.exit(0);
        } else if (input.length <= 2 && isNatural(input)) {
            getProperties(input);
        } else if (isNatural(input) && isGoodProperty(Arrays.asList(input)) && isNotExclusive(input)) {
            getProperties(input);
        }

        runApp();
    }

    private static boolean isNatural(String[] input) {
        boolean isNatural = true;

        if (!input[0].matches("^\\d*\\b") || Long.parseLong(input[0]) < 1) {
            Output.printError(1, "");
            isNatural = false;
        }
        if (input.length > 1 && (!input[1].matches("^\\d*\\b") || Long.parseLong(input[1]) < 1)) {
            Output.printError(2, "");
            isNatural = false;
        }

        return isNatural;
    }

    private static boolean isGoodProperty(List<String> input) {
        List<String> props = input.stream()
                .filter(x -> input.indexOf(x) > 1 && !PROPERTIES.contains(x.toLowerCase()))
                .collect(Collectors.toList());

        if (!props.isEmpty()) {
            Output.printError(3, String.join(", ", props));
            return false;
        }
        return true;
    }

    private static boolean isNotExclusive(String[] input) {
        List<String> lst = Stream.of(input).map(String::toLowerCase).collect(Collectors.toList());

        for (String value : lst) {
            if (value.matches("^-\\w+") && lst.contains(value.substring(1))) {
                Output.printError(4, String.join(", ", value, value.substring(1)));
                return false;
            }
            if (value.matches("^\\w+") && lst.contains("-" + value)) {
                Output.printError(4, String.join(", ", value, "-" + value));
                return false;
            }
        }
        return checkPairs(lst, EXL_PAIR_ONE) && checkPairs(lst, NEG_EXL_PAIR_ONE)
                && checkPairs(lst, EXL_PAIR_TWO) && checkPairs(lst, NEG_EXL_PAIR_TWO)
                && checkPairs(lst, EXL_PAIR_THREE) && checkPairs(lst, NEG_EXL_PAIR_THREE)
                && checkPairs(lst, EXL_PAIR_FOUR) && checkPairs(lst, NEG_EXL_PAIR_FOUR);
    }

    private static boolean checkPairs(List<String> lst, List<String> condition) {
        if (lst.containsAll(condition)) {
            Output.printError(4, String.join(", ", condition));
            return false;
        }
        return true;
    }


    private static void getProperties(String[] input) {
        System.out.println();
        switch (input.length) {
            case 1:
                System.out.println(new Number(Long.parseLong(input[0])).getPropertiesString(2));
                break;
            case 2:
                for (int i = 0; i < Long.parseLong(input[1]); i++) {
                    System.out.println(new Number(Long.parseLong(input[0]) + i).getPropertiesString(1));
                }
                break;
            default:
                ArrayList<String> props = Arrays.stream(input)
                        .map(String::toLowerCase)
                        .collect(Collectors.toCollection(ArrayList::new));
                ArrayList<String> exlProps = props.stream()
                        .filter(x -> x.matches("^-\\w+"))
                        .map(x -> x.substring(1))
                        .collect(Collectors.toCollection(ArrayList::new));
                props.removeIf(x -> x.matches("^-\\w+") || props.indexOf(x) < 2);

                for (int i = 0, lineCounter = 0; lineCounter < Long.parseLong(input[1]); i++) {
                    String result = new Number(Long.parseLong(input[0]) + i).getPropertiesString(1);

                    if (props.stream().allMatch(result::contains) && exlProps.stream().noneMatch(result::contains)) {
                        System.out.println(result);
                        lineCounter++;
                    }
                }
        }
    }
}
