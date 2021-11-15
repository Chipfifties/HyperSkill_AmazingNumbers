package numbers;

public class Output {
    public static void printInstructions(int welcomeMessageCode) {
        System.out.print(welcomeMessageCode == 1 ? "Welcome to Amazing Numbers!\n" : "");
        System.out.println("\nSupported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    public static void printError(int errorCode, String property) {
        switch (errorCode) {
            case 1:
                System.out.println("\nThe first parameter should be a natural number or zero.");
                break;
            case 2:
                System.out.println("\nThe second parameter should be a natural number.");
                break;
            case 3:
                System.out.printf("\nThe " + (property.contains(",") ? "properties [%s] are" : "property [%s] is") +
                        " wrong.\nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, " +
                        "SUNNY, JUMPING, HAPPY, SAD]", property.toUpperCase());
                break;
            case 4:
                System.out.printf("\nThe request contains mutually exclusive properties: [%s]\n" +
                        "There are no numbers with these properties.", property.toUpperCase());
                break;
        }
    }
}
