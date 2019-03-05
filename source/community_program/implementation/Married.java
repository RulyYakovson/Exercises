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

    /**
     * Calculates Progressive taxation in terms of gender and number of children.
     * @return
     */
    @Override
    public double communityTax() {
        // If the member only studies and does not work ("Torhato umanuto")
        if(getWeeklyWorkHours() == 0) {
            return 0;
        }
        //calculate right points of tax.
        double points = 0.5; //for marriage
        if(getGender() == Gender.FEMALE) points += 0.5;
        points += getNumOfChildren();
        return calculateTax(getIncome(),points);
    }

    /**
     * gets the max amount of entitlement to get from the "Gmach"
     * consider by the income and children.
     *
     * Note:
     * dose not check how much he use the entitlement.
     * @return
     */
    @Override
    public double entitlement() {
        return (getIncome()/10000+1)*5000 + (getNumOfChildren()*500);
    }

    /**
     * get recommended volunteer hours consider whit num of children.
     * by default is 5 hours a week.
     * @return int value of
     */
    @Override
    public double recommendedVolunteerHours() {
        return Math.max((int)(5 - getNumOfChildren()*0.5),0);
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
