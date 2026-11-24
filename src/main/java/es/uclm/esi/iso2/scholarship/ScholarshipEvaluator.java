package es.uclm.esi.iso2.scholarship;

import java.math.BigDecimal;

/**
 * Evaluates student profiles against scholarship eligibility rules.
 *
 * <p>Rules are evaluated in this priority order: EXCELLENCE, INCLUSION,
 * RESIDENCE, LARGE_FAMILY, then NONE. The Residence GPA range is interpreted
 *  * as {@code 6.5 <= GPA < 8.5} so that it ends where the Excellence threshold starts
 * and no GPA value falls outside both ranges.</p>
 */
public class ScholarshipEvaluator {

    private static final double EXCELLENCE_MINIMUM_GPA = 8.5;
    private static final BigDecimal EXCELLENCE_MAXIMUM_FAMILY_INCOME = new BigDecimal("30000");

    private static final double INCLUSION_MINIMUM_DISABILITY_PERCENTAGE = 33.0;
    private static final double INCLUSION_MINIMUM_GPA = 5.0;

    private static final double RESIDENCE_MINIMUM_GPA = 6.5;
    private static final double RESIDENCE_MAXIMUM_GPA_EXCLUSIVE = 8.5;
    private static final BigDecimal RESIDENCE_MAXIMUM_FAMILY_INCOME = new BigDecimal("20000");
    private static final double RESIDENCE_MINIMUM_CAMPUS_DISTANCE_KM_EXCLUSIVE = 50.0;

    private static final double LARGE_FAMILY_MINIMUM_GPA = 5.0;
    private static final BigDecimal LARGE_FAMILY_MAXIMUM_FAMILY_INCOME = new BigDecimal("15000");
    private static final int LARGE_FAMILY_MINIMUM_NUMBER_OF_SIBLINGS = 3;

    /**
     * Evaluates a student profile and returns the first matching scholarship category.
     *
     * @param student student profile to evaluate
     * @return the highest-priority matching scholarship category, or NONE
     * @throws InvalidStudentProfileException if the student is null
     */
    public ScholarshipCategory evaluate(StudentProfile student) {
        if (student == null) {
            throw new InvalidStudentProfileException("student must not be null");
        }

        if (student.getGpa() >= EXCELLENCE_MINIMUM_GPA
                && student.getFamilyIncome().compareTo(EXCELLENCE_MAXIMUM_FAMILY_INCOME) < 0) {
            return ScholarshipCategory.EXCELLENCE;
        }
        if (student.getDisabilityPercentage() >= INCLUSION_MINIMUM_DISABILITY_PERCENTAGE
                && student.getGpa() >= INCLUSION_MINIMUM_GPA) {
            return ScholarshipCategory.INCLUSION;
        }
        if (student.getGpa() >= RESIDENCE_MINIMUM_GPA
                && student.getGpa() < RESIDENCE_MAXIMUM_GPA_EXCLUSIVE
                && student.getFamilyIncome().compareTo(RESIDENCE_MAXIMUM_FAMILY_INCOME) < 0
                && student.getCampusDistanceKm() > RESIDENCE_MINIMUM_CAMPUS_DISTANCE_KM_EXCLUSIVE) {
            return ScholarshipCategory.RESIDENCE;
        }
        if (student.getGpa() >= LARGE_FAMILY_MINIMUM_GPA
                && student.getFamilyIncome().compareTo(LARGE_FAMILY_MAXIMUM_FAMILY_INCOME) < 0
                && student.getNumberOfSiblings() >= LARGE_FAMILY_MINIMUM_NUMBER_OF_SIBLINGS) {
            return ScholarshipCategory.LARGE_FAMILY;
        }
        return ScholarshipCategory.NONE;
    }
}
