package community_program.classes;

import community_program.enums.Donation;
import community_program.enums.Gender;
import community_program.exceptions.NotFullyBusyException;

import java.util.Date;

public abstract class CommunityMember implements ObligationsAndRights {
    private static final int ESSENTIAL_BUSY_HOURS = 112;

    private int id;

    private Gender gender;

    private String name;

    private String address;

    private Date birthDay;

    private double weeklyTorahHours;

    private double weeklyWorkHours;

    private double income;

    private double percentOfGemachUsed;

    private Donation donation;

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
     * @throws NotFullyBusyException - In case the member is not busy (Torah + work) exactly two-thirds of the week
     */
    public CommunityMember(int id, Gender gender, String name, String address, Date birthDay, double weeklyTorahHours,
                           double weeklyWorkHours, double income, double percentOfGemachUsed, Donation donation) throws NotFullyBusyException {
        if(weeklyTorahHours + weeklyWorkHours != ESSENTIAL_BUSY_HOURS) {
            throw new NotFullyBusyException("ERROR: This human cannot be a community member because he not fully busy");
        }
        setId(id);
        setGender(gender);
        setName(name);
        setAddress(address);
        setBirthDay(birthDay);
        setWeeklyTorahHours(weeklyTorahHours);
        setWeeklyWorkHours(weeklyWorkHours);
        setIncome(income);
        setPercentOfGemachUsed(percentOfGemachUsed);
        setDonation(donation);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public double getWeeklyTorahHours() {
        return weeklyTorahHours;
    }

    public void setWeeklyTorahHours(double weeklyTorahHours) {
        this.weeklyTorahHours = weeklyTorahHours;
    }

    public double getWeeklyWorkHours() {
        return weeklyWorkHours;
    }

    public void setWeeklyWorkHours(double weeklyWorkHours) {
        this.weeklyWorkHours = weeklyWorkHours;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getPercentOfGemachUsed() {
        return percentOfGemachUsed;
    }

    public void setPercentOfGemachUsed(double percentOfGemachUsed) {
        this.percentOfGemachUsed = percentOfGemachUsed;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    @Override
    public String toString() {
        return "CommunityMember{" +
                "id=" + id +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthDay=" + birthDay +
                ", weeklyTorahHours=" + weeklyTorahHours +
                ", weeklyWorkHours=" + weeklyWorkHours +
                ", income=" + income +
                ", percentOfGemachUsed=" + percentOfGemachUsed +
                ", donation=" + donation +
                '}';
    }

    /**
     * Calculate Progressive taxation -
     * The higher the income of a member, the higher the tax rate will be imposed on him.
     * @param income
     * @param points
     * @return
     */
    double calculateTax(double income, double points){

        double taxPercent = 1.5;
        double tax = 0;
        double levelOfIncome = 10000;

        //Calculates the tax according to the salary level.
        //0: 0 - 10,000. 1: 10,000 - 17,500. 2: 17,500 - 23,125. 3: 23,125 - infinity.
        int i = 0;
        while (income > 0 && i < 3){
            tax += Math.min(income, levelOfIncome)*(Math.max(taxPercent - points*0.2, 0));
            income -= levelOfIncome;
            levelOfIncome *= 3/4;
            taxPercent *= 2;
        }
        return income > 0 ? tax + income*(Math.max(taxPercent - points*0.2, 0)) : tax;
    }
}
