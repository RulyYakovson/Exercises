package community_program.implementation;

public interface ObligationsAndRights {

    /**
     * @return - Returns the community tax for a community member
     */
    double communityTax();

    /**
     *
     * @return - Returns the maximum amount that a community member
     * is entitled to receive from the community "gmach"
     */
    double Entitlement();

    /**
     *
     * @return - Returns the recommended volunteer hours for a community member
     */
    int recommendedVolunteerHours();
}
