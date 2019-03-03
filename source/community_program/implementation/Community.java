package community_program.implementation;

import community_program.enums.Donation;

import java.util.*;

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
     * Add members to the community
     * @param members - The members to add
     */
    public void addMembers(CommunityMember... members) {
        for (CommunityMember member : members) {
            if (member != null) {
                if (isExist(member.getId())) {
                    System.out.println(String.format("ID: '%s' already exist", member.getId()));
                } else {
                    communityMembers.add(member);
                }
            }
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
     * @param id
     * @return - The approved amount or -1 if the member cannot be found
     */
    public double approvedAmountFromGemach(int id) {
        CommunityMember member = getMemberById(id);
        if(member != null) {

        //TODO
            return 999;
        }
        return -1;
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

    private CommunityMember getMemberById(int id) {
        for (CommunityMember member : communityMembers) {
            if (Objects.equals(member.getId(), id)) {
                return member;
            }
        }
        return null;
    }

    private Boolean isExist(int id) {
        for (CommunityMember member : communityMembers) {
            if(member.getId() == id) {
                return true;
            }
        }
        return false;
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
