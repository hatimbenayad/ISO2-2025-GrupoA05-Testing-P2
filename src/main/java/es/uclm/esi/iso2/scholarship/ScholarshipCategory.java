package es.uclm.esi.iso2.scholarship;

/**
 * Categories of university scholarships and their associated benefits.
 */
public enum ScholarshipCategory {
    /** Scholarship for students with outstanding academic performance. */
    EXCELLENCE(
            "Excellence Scholarship",
            "Awarded for outstanding academic performance with a limited family income.",
            "Full tuition waiver and an academic excellence grant."),

    /** Scholarship for students who live far from the campus. */
    RESIDENCE(
            "Residence Scholarship",
            "Awarded to students who live far from the campus and have a low family income.",
            "Partial tuition waiver and an accommodation grant."),

    /** Scholarship for students from large families. */
    LARGE_FAMILY(
            "Large Family Scholarship",
            "Awarded to students from large families with a low family income.",
            "Partial tuition waiver for large families."),

    /** Scholarship for students with a recognised disability. */
    INCLUSION(
            "Inclusion Scholarship",
            "Awarded to students with a recognised disability of at least 33%.",
            "Full tuition waiver and an inclusion support grant."),

    /** Category used when no scholarship criteria are met. */
    NONE(
            "No Scholarship",
            "The student does not meet the criteria of any scholarship.",
            "No financial or tuition benefits.");

    private final String displayName;
    private final String description;
    private final String benefits;

    /**
     * Creates a scholarship category with its display information.
     *
     * @param displayName name shown to users
     * @param description description of the category
     * @param benefits benefits associated with the category
     */
    ScholarshipCategory(String displayName, String description, String benefits) {
        this.displayName = displayName;
        this.description = description;
        this.benefits = benefits;
    }

    /**
     * Returns the category's display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the category's eligibility description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the benefits granted by this category.
     *
     * @return the benefits
     */
    public String getBenefits() {
        return benefits;
    }
}
