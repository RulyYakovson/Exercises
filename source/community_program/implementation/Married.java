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
        double points = 0;
        if(getGender() == Gender.FEMALE) points = 0.5;
        points += getNumOfChildren();
        return calculateTax(getIncome(),points);
    }

    /**
     * Calculate Progressive taxation -
     * The higher the income of a member, the higher the tax rate will be imposed on him.
     * @param income
     * @param points
     * @return
     */
    double calculateTax(double income, double points){

        double taxProcent = 1.5 - points * 0.2;
        double tax = 0;
        double levlOfIncome = 10000;

        //Calculates the tax according to the salary level.
        int i = 0;
        while (income > 0 && i < 4){
            tax += Math.min(income, levlOfIncome)*taxProcent;
            income -= levlOfIncome;
            levlOfIncome *= 3/4;
            taxProcent *= 2;
        }
        return income > 0 ? tax += income*taxProcent  : tax;
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
