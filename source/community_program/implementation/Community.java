package community_program.implementation;

import community_program.enums.Donation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Community {

    private List<CommunityMember> communityMembers;

    /**
     * Constructor
     */
    public Community() {
        communityMembers = new LinkedList<>();
    }

    public List<CommunityMember> getCommunityMembers() {
        return communityMembers;
    }

    /**
     * Add member to the community
     * @param member - The member to add
     */
    public void addMember(CommunityMember member) {
        if (member != null) {
            communityMembers.add(member);
        }
    }

    /**
     * Calculates the total monthly tax that can be collected from all members of the community.
     *
     * @return
     */
    public double totalTax() {
        double totalTax = 0;
        for (CommunityMember member : communityMembers) {
            totalTax += member.communityTax();
        }
        return totalTax;
    }

    /**
     *  Calculates the approved amount that tha given member can get from the community gemach
     * @param member
     * @return - The approved amount
     */
    public double approvedAmountFromGemach(CommunityMember member) {
        //TODO
        return 0;
    }

    /**
     * The total volunteer hours of all members of the community sorted according to the type of volunteering
     * @return - Map of pairs <type of volunteering, total volunteer hours>
     */
    public Map<Donation, Double> totalVolunteerHours() {
        Map<Donation, Double> totalVolunteerHours = new HashMap<>();
        double sHours = 0, pHours = 0, mHours = 0;
        for (CommunityMember member : communityMembers) {
            switch (member.getDonation()){
                case SPIRITUALLY:
                    sHours += member.recommendedVolunteerHours();
                    break;
                case PHYSICALLY:
                    pHours += member.recommendedVolunteerHours();
                    break;
                case MUSICAL:
                    mHours += member.recommendedVolunteerHours();
                    break;
            }
        }
        totalVolunteerHours.put(Donation.SPIRITUALLY, sHours);
        totalVolunteerHours.put(Donation.PHYSICALLY, pHours);
        totalVolunteerHours.put(Donation.MUSICAL, mHours);

        return totalVolunteerHours;
    }

    @Override
    public String toString() {
        String result = "";
        for (CommunityMember member : communityMembers) {
            result += member + "\n";
        }
        return result;
    }
}
