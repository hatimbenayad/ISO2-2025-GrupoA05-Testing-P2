package es.uclm.esi.iso2.scholarship.cli;

import es.uclm.esi.iso2.scholarship.InvalidStudentProfileException;
import es.uclm.esi.iso2.scholarship.ScholarshipCategory;
import es.uclm.esi.iso2.scholarship.ScholarshipEvaluator;
import es.uclm.esi.iso2.scholarship.StudentProfile;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Command-line interface for evaluating a student's scholarship eligibility.
 */
public class ScholarshipCLI {

    /**
     * Reads a student profile, evaluates it, and displays the scholarship result.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Double gpa = readDecimal(scanner, "Enter GPA (0-10): ");
            if (gpa == null) {
                return;
            }

            BigDecimal familyIncome = readIncome(scanner, "Enter annual family income in EUR (no thousands separators, e.g. 18000): ");
            if (familyIncome == null) {
                return;
            }

            Double campusDistanceKm = readDecimal(scanner, "Enter distance to campus (km): ");
            if (campusDistanceKm == null) {
                return;
            }

            Double disabilityPercentage = readDecimal(scanner, "Enter disability percentage (0-100): ");
            if (disabilityPercentage == null) {
                return;
            }

            Integer numberOfSiblings = readSiblings(scanner, "Enter number of siblings: ");
            if (numberOfSiblings == null) {
                return;
            }

            try {
                StudentProfile student = new StudentProfile(
                        gpa, familyIncome, campusDistanceKm, disabilityPercentage, numberOfSiblings);
                ScholarshipCategory category = new ScholarshipEvaluator().evaluate(student);
                System.out.println("Category: " + category.getDisplayName());
                System.out.println("Description: " + category.getDescription());
                System.out.println("Benefits: " + category.getBenefits());
                return;
            } catch (InvalidStudentProfileException exception) {
                System.out.println("Invalid data: " + exception.getMessage());
            }
        }
    }

    private static Double readDecimal(Scanner scanner, String prompt) {
        while (true) {
            String value = readLine(scanner, prompt);
            if (value == null) {
                return null;
            }

            try {
                return Double.parseDouble(value.replace(',', '.'));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter a valid decimal value.");
            }
        }
    }

    private static BigDecimal readIncome(Scanner scanner, String prompt) {
        while (true) {
            String value = readLine(scanner, prompt);
            if (value == null) {
                return null;
            }

            try {
                return new BigDecimal(value.replace(',', '.'));
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter a valid family income.");
            }
        }
    }

    private static Integer readSiblings(Scanner scanner, String prompt) {
        while (true) {
            String value = readLine(scanner, prompt);
            if (value == null) {
                return null;
            }

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter a whole number of siblings.");
            }
        }
    }

    private static String readLine(Scanner scanner, String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            System.out.println("\nInput ended. Exiting.");
            return null;
        }
        return scanner.nextLine().trim();
    }
}
