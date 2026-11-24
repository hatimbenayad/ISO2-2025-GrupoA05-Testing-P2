package es.uclm.esi.iso2.scholarship;

import java.math.BigDecimal;

/**
 * Immutable profile containing the data used to determine scholarship eligibility.
 */
public final class StudentProfile {

    private final double gpa;
    private final BigDecimal familyIncome;
    private final double campusDistanceKm;
    private final double disabilityPercentage;
    private final int numberOfSiblings;

    /**
     * Creates a validated student profile.
     *
     * @param gpa grade-point average
     * @param familyIncome annual family income
     * @param campusDistanceKm distance from the student's home to campus
     * @param disabilityPercentage recognised disability percentage
     * @param numberOfSiblings number of siblings
     * @throws InvalidStudentProfileException if any profile value is invalid
     */
    public StudentProfile(double gpa, BigDecimal familyIncome, double campusDistanceKm,
                          double disabilityPercentage, int numberOfSiblings) {
        if (!Double.isFinite(gpa) || gpa < 0.0 || gpa > 10.0) {
            throw new InvalidStudentProfileException("gpa must be finite and in [0.0, 10.0]: " + gpa);
        }
        if (familyIncome == null) {
            throw new InvalidStudentProfileException("familyIncome must not be null: " + familyIncome);
        }
        if (familyIncome.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidStudentProfileException(
                    "familyIncome must be greater than or equal to 0: " + familyIncome);
        }
        if (!Double.isFinite(campusDistanceKm) || campusDistanceKm < 0.0) {
            throw new InvalidStudentProfileException(
                    "campusDistanceKm must be finite and greater than or equal to 0.0: "
                            + campusDistanceKm);
        }
        if (!Double.isFinite(disabilityPercentage)
                || disabilityPercentage < 0.0 || disabilityPercentage > 100.0) {
            throw new InvalidStudentProfileException(
                    "disabilityPercentage must be finite and in [0.0, 100.0]: "
                            + disabilityPercentage);
        }
        if (numberOfSiblings < 0) {
            throw new InvalidStudentProfileException(
                    "numberOfSiblings must be greater than or equal to 0: " + numberOfSiblings);
        }

        this.gpa = gpa;
        this.familyIncome = familyIncome;
        this.campusDistanceKm = campusDistanceKm;
        this.disabilityPercentage = disabilityPercentage;
        this.numberOfSiblings = numberOfSiblings;
    }

    /**
     * Returns the grade-point average.
     *
     * @return the GPA
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Returns the annual family income.
     *
     * @return the family income
     */
    public BigDecimal getFamilyIncome() {
        return familyIncome;
    }

    /**
     * Returns the distance from the student's home to campus.
     *
     * @return the campus distance in kilometres
     */
    public double getCampusDistanceKm() {
        return campusDistanceKm;
    }

    /**
     * Returns the recognised disability percentage.
     *
     * @return the disability percentage
     */
    public double getDisabilityPercentage() {
        return disabilityPercentage;
    }

    /**
     * Returns the number of siblings.
     *
     * @return the number of siblings
     */
    public int getNumberOfSiblings() {
        return numberOfSiblings;
    }

    /**
     * Returns a text representation containing all five profile values.
     *
     * @return the profile values
     */
    @Override
    public String toString() {
        return "StudentProfile{"
                + "gpa=" + gpa
                + ", familyIncome=" + familyIncome
                + ", campusDistanceKm=" + campusDistanceKm
                + ", disabilityPercentage=" + disabilityPercentage
                + ", numberOfSiblings=" + numberOfSiblings
                + '}';
    }
}
