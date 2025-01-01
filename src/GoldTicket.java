import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class GoldTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Įveskite šešiaženklį bilieto numerį:");
        String ticketNumber = scanner.nextLine();

        while (!isValidTicket(ticketNumber)) {
            System.out.println("Netinkamas bilieto numeris. Įveskite šešiaženklį skaičių:");
            ticketNumber = scanner.nextLine();
        }

        if (isLucky(ticketNumber)) {
            System.out.println("Bilietas " + ticketNumber + " yra laimingas!");
        } else {
            System.out.println("Bilietas " + ticketNumber + " nėra laimingas.");
        }

        System.out.println("Ieškoma pirmo laimingo bilieto...");
        int attempts = 0;
        String luckyTicket;
        do {
            luckyTicket = generateRandomTicket();
            attempts++;
        } while (!isLucky(luckyTicket));

        System.out.println("Pirmas laimingas bilietas: " + luckyTicket);
        System.out.println("Bandymų skaičius: " + attempts);
    }

    private static boolean isValidTicket(String ticket) {
        return ticket.length() == 6 && ticket.matches("\\d+");
    }

    private static boolean isLucky(String ticket) {
        int firstSum = Character.getNumericValue(ticket.charAt(0)) +
                Character.getNumericValue(ticket.charAt(1)) +
                Character.getNumericValue(ticket.charAt(2));
        int secondSum = Character.getNumericValue(ticket.charAt(3)) +
                Character.getNumericValue(ticket.charAt(4)) +
                Character.getNumericValue(ticket.charAt(5));

        return firstSum == secondSum && allDigitsUnique(ticket);
    }

    private static boolean allDigitsUnique(String ticket) {
        HashSet<Character> uniqueDigits = new HashSet<>();
        for (char c : ticket.toCharArray()) {
            if (!uniqueDigits.add(c)) {
                return false;
            }
        }
        return true;
    }

    private static String generateRandomTicket() {
        Random random = new Random();
        StringBuilder ticket = new StringBuilder(6);
        while (ticket.length() < 6) {
            char digit = (char) ('0' + random.nextInt(10));
            ticket.append(digit);
        }
        return ticket.toString();
    }
}