package community_program.implementation;

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
}
