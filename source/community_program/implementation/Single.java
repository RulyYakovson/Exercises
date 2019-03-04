package community_program.implementation;

import community_program.enums.Donation;
import community_program.enums.Gender;
import community_program.exceptions.NotFullyBusyException;

import java.util.Date;

public class Single extends CommunityMember {

    double studyYears;

    /**
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
     * @param studyYears
     * @throws NotFullyBusyException - In case the member is not busy (Torah + work) exactly two-thirds of the week
     */
    public Single(int id, Gender gender, String name, String address, Date birthDay, double weeklyTorahHours,
                  double weeklyWorkHours, double income, double percentOfGemachUsed, Donation donation, double studyYears) throws NotFullyBusyException {
        super(id, gender, name, address, birthDay, weeklyTorahHours, weeklyWorkHours, income, percentOfGemachUsed, donation);
        setStudyYears(studyYears);
    }

    @Override
    public double communityTax() {
        // If the member only studies and does not work ("Torhato umanuto")
        if(getWeeklyWorkHours() == 0) {
            return 0;
        }//TODO
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

    public double getStudyYears() {
        return studyYears;
    }

    public void setStudyYears(double studyYears) {
        this.studyYears = studyYears;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                " studyYears=" + studyYears +
                        '}');
    }
}
