package numbers;

import java.util.Arrays;
import java.util.stream.Stream;

class Number {
    long number;
    boolean isEven, isBuzz, isDuck, isPalindromic, isGapful, isSpy, isSquare, isSunny, isJumping, isHappy;

    Number(long number) {
        this.number = number;
        String numberAsString = Long.toString(number);
        this.isEven = number % 2 == 0;
        this.isBuzz = number % 7 == 0 || numberAsString.charAt(numberAsString.length() - 1) == '7';
        this.isDuck = numberAsString.matches("(?!0)[0-9]*0[0-9]*");
        this.isPalindromic = numberAsString.equals(new StringBuilder(numberAsString).reverse().toString());
        this.isGapful = isGapful(numberAsString);
        this.isSpy = isSpy(numberAsString);
        this.isSquare = Math.sqrt((double) number) - Math.floor(Math.sqrt((double) number)) == 0;
        this.isSunny = Math.sqrt((double) number + 1) - Math.floor(Math.sqrt((double) number + 1)) == 0;
        this.isJumping = isJumping(numberAsString);
        this.isHappy = isHappy(numberAsString);
    }

    String getPropertiesString(int printFormat) {
        switch (printFormat) {
            case 1:
                return String.format("%12d is ", number) +
                        (isEven ? "even" : "odd") +
                        (isBuzz ? ", buzz" : "") +
                        (isDuck ? ", duck" : "") +
                        (isPalindromic ? ", palindromic" : "") +
                        (isSpy ? ", spy" : "") +
                        (isGapful ? ", gapful" : "") +
                        (isSquare ? ", square" : "") +
                        (isSunny ? ", sunny" : "") +
                        (isJumping ? ", jumping" : "") +
                        (isHappy ? ", happy" : ", sad");
            case 2:
                return String.format("\nProperties of %d\n", number) +
                        String.format("%12s: %s\n", "buzz", isBuzz) +
                        String.format("%12s: %s\n", "duck", isDuck) +
                        String.format("%12s: %s\n", "palindromic", isPalindromic) +
                        String.format("%12s: %s\n", "gapful", isGapful) +
                        String.format("%12s: %s\n", "spy", isSpy) +
                        String.format("%12s: %s\n", "square", isSquare) +
                        String.format("%12s: %s\n", "sunny", isSunny) +
                        String.format("%12s: %s\n", "jumping", isJumping) +
                        String.format("%12s: %s\n", "happy", isHappy) +
                        String.format("%12s: %s\n", "sad", !isHappy) +
                        String.format("%12s: %s\n", "even", isEven) +
                        String.format("%12s: %s", "odd", !isEven);
            default:
                return "";
        }
    }

    private static boolean isHappy(String number) {
        long sumOfSquares = 0;
        while (sumOfSquares != 1 && sumOfSquares != 4) {
            sumOfSquares = Arrays.stream(number.split("")).mapToLong(Long::parseLong).map(x -> x * x).sum();
            number = Long.toString(sumOfSquares);
        }
        return sumOfSquares == 1;
    }

    private static boolean isJumping(String numAsString) {
        for (int i = 0; i < numAsString.length() - 1; i++) {
            if (Math.abs(numAsString.charAt(i) - numAsString.charAt(i + 1)) != 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isGapful(String numAsString) {
        return numAsString.length() >= 3
                && Long.parseLong(numAsString) % Long.parseLong(numAsString.charAt(0)
                        + numAsString.substring(numAsString.length() - 1)) == 0;
    }

    private static boolean isSpy(String number) {
        return Stream.of(number.split("")).mapToLong(Long::parseLong).sum()
                == Stream.of(number.split("")).mapToLong(Long::parseLong).reduce(1, Math::multiplyExact);
    }
}
