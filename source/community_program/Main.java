package community_program;

import community_program.enums.Donation;
import community_program.enums.Gender;
import community_program.exceptions.NotFullyBusyException;
import community_program.implementation.Community;
import community_program.implementation.CommunityMember;
import community_program.implementation.Married;
import community_program.implementation.Single;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        Community community = null;
        try {
            // Init the community with some default members
            community = init();
        } catch (NotFullyBusyException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("             ------------- COMMUNITY PROGRAM----------------");
        int choice = menu();
        while (choice != 0) {
            switch (choice) {
                case 1:
                    try {
                        addMember();
                    } catch (NotFullyBusyException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println(community);
                    break;
                case 3:
                    memberEntitlement(community.getCommunityMembers(), "entitlement");
                    break;
                case 4:
                    memberEntitlement(community.getCommunityMembers(), "volunteer hours");
                    break;
                case 5:
                    memberEntitlement(community.getCommunityMembers(), "community tax ");
                    break;
                case 6:
                    System.out.println(community.totalTax());
                    break;
                case 7:
                    System.out.println(findApprovedAmount(community));
                case 8:
                    printTotalVolunteerHours(community.totalVolunteerHours());
                default:
                    System.out.println("Wrong choice - Please choose again");
            }
            choice = menu();
        }
        System.out.println("\nThank you for using Community Program, we hope you found it useful.");
    }

    private static CommunityMember addMember() throws NotFullyBusyException {
        CommunityMember member = null;
        int choice;
        System.out.println("For married member press 1. For single member press 2:");
        while (member == null) {
            choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1:
                    member = addMarriedMember();
                    break;
                case 2:
                    member = addSingleMember();
                default:
                    System.out.println("Wrong choice - Please choose again");
            }
        }
        return member;
    }

    private static Married addMarriedMember() throws NotFullyBusyException {
        //TODO
        Married married = null;
        return married;
    }

    private static Single addSingleMember() throws NotFullyBusyException {
        //TODO
        Single single = null;
        return single;
    }

    private static double findApprovedAmount(Community community) {
        int id;
        double amount = -1;
        System.out.println("Please insert the ID of the member:");
        while (amount == -1) {
            id = Integer.parseInt(System.console().readLine());
            amount = community.approvedAmountFromGemach(id);
            if (amount == -1) {
                System.out.println("Wrong ID, Please insert again:");
            }
        }
        return amount;
    }

    private static void memberEntitlement(List<CommunityMember> communityMembers, String action) {
        int id;
        Boolean found = false;
        System.out.println("Please insert the ID of the member:");
        while (!found) {
            id = Integer.parseInt(System.console().readLine());
            for (CommunityMember member : communityMembers) {
                if (Objects.equals(member.getId(), id)) {
                    switch (action) {
                        case "entitlement":
                            System.out.println(member.entitlement());
                            found = true;
                            break;
                        case "volunteer hours":
                            System.out.println(member.recommendedVolunteerHours());
                            found = true;
                            break;
                        case "community tax":
                            System.out.println(member.communityTax());
                            found = true;
                            break;
                    }
                }
            }
            if (!found) {
                System.out.println("Wrong ID, Please insert again:");
            }
        }
    }

    private static void printTotalVolunteerHours(Map<Donation, Double> volunteerHoursMap) {
        double sHours = volunteerHoursMap.get(Donation.SPIRITUALLY);
        double pHours = volunteerHoursMap.get(Donation.PHYSICALLY);
        double mHours = volunteerHoursMap.get(Donation.MUSICAL);

        String base = "Total hours of %s donation: %s";
        System.out.println(String.format(base, Donation.SPIRITUALLY, sHours));
        System.out.println(String.format(base, Donation.PHYSICALLY, pHours));
        System.out.println(String.format(base, Donation.MUSICAL, mHours));
    }

    private static int menu() {
        int choice = 0;
        String menu = "Please select your choice:\n" +
                "To add a member press 1.\n" +
                "To see a list of all members press 2.\n" +
                "To see a member's entitlement from the Gemach press 3.\n" +
                "To see a member's recommended volunteer hours press 4.\n" +
                "To see a member's community tax press 5.\n" +
                "To see the total monthly tax that can be collected from all members press 6.\n" +
                "To see the approved amount that a member can get from the community Gemach press 7.\n" +
                "To see the total volunteer hours of all members press 8.\n" +
                "To exit press 0.";
        System.out.println(menu);
        choice = Integer.parseInt(System.console().readLine());
        return choice;
    }

    private static Community init() throws NotFullyBusyException {
        Single single1 = new Single(333, Gender.MALE, "Yitzhak Avinu", "Baohel", new Date(1099, 10, 2),
                111, 1, 10, 10, Donation.SPIRITUALLY, 25.5);
        Single single2 = new Single(444, Gender.MALE, "Yishmael", "Bmidbar", new Date(1080, 10, 2),
                0, 112, 9999, 10, Donation.PHYSICALLY, 0.5);
        Married married1 = new Married(111, Gender.FEMALE, "Sara Imeinu", "Baohel", new Date(1005, 10, 2),
                90, 22, 1000, 10, Donation.SPIRITUALLY, 111, 1);
        Married married2 = new Married(222, Gender.MALE, "Avraham Avinu", "Baohel", new Date(1000, 10, 2),
                110, 2, 1000, 10, Donation.SPIRITUALLY, 111, 1);
        Community community = new Community();
        community.addMembers(single1, single2, married1, married2);
        return community;
    }
}
