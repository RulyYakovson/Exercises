package community_program.implementation;

import community_program.enums.Donation;
import community_program.enums.Gender;
import community_program.exceptions.NotFullyBusyException;

import java.util.Date;

public class Married extends  CommunityMember {

    int spouseId;

    int numOfChildren;

    /**
     *  Constructor
     *
     * @param id
     * @param gender
     * @param name
     * @param address
     * @param birthDay
     * @param weeklyTorahHours - Tha amount of hours the member learning torah in a week
     * @param weeklyWorkHours - The amount of hours the member works in a week
     * @param income - The monthly income of the member
     * @param percentOfGemachUsed - The percentage of use of the "gmach"
     * @param donation - The type of voluntary action
     * @param spouseId
     * @param numOfChildren - The number of children under 18
     * @throws NotFullyBusyException - In case the member is not busy (Torah + work) exactly two-thirds of the week
     */
    public Married(int id, Gender gender, String name, String address, Date birthDay, double weeklyTorahHours,
                   double weeklyWorkHours, double income, double percentOfGemachUsed, Donation donation, int spouseId, int numOfChildren) throws NotFullyBusyException {
        super(id, gender, name, address, birthDay, weeklyTorahHours, weeklyWorkHours, income, percentOfGemachUsed, donation);
        setSpouseId(spouseId);
        setNumOfChildren(numOfChildren);
    }

    @Override
    public double communityTax() {
        // If the member only studies and does not work ("Torhato umanuto")
        if(getWeeklyWorkHours() == 0) {
            return 0;
        }
        //TODO
        return 0;
    }

    @Override
    public double entitlement() {
        //TODO
        return 0;
    }

    @Override
    public int recommendedVolunteerHours() {
        //TODO
        return 0;
    }

    public int getSpouseId() {
        return spouseId;
    }

    public void setSpouseId(int spouseId) {
        this.spouseId = spouseId;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                " spouseId=" + spouseId +
                ", numOfChildren=" + numOfChildren +
                '}');
    }
}
