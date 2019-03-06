package community_program;

import community_program.enums.Action;
import community_program.enums.Donation;
import community_program.enums.Gender;
import community_program.enums.MemberStatus;
import community_program.exceptions.NotFullyBusyException;
import community_program.classes.Community;
import community_program.classes.CommunityMember;
import community_program.classes.Married;
import community_program.classes.Single;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * This program manages a community information system
 * Created by Israel Yakovson ID: 300190055 and
 * Yizchak Weiss ID: 204017776
 * 05/03/2019
 */
public class Main {
    public static Scanner reader = new Scanner(System.in);
    public static Community community;

    public static void main(String[] args) {
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
                    } catch (NotFullyBusyException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println(community);
                    break;
                case 3:
                    printAction(community.getCommunityMembers(), Action.ENTITLEMENT);
                    break;
                case 4:
                    printAction(community.getCommunityMembers(), Action.VOLUNTEER_HOURS);
                    break;
                case 5:
                    printAction(community.getCommunityMembers(), Action.COMMUNITY_TAX);
                    break;
                case 6:
                    System.out.println(community.totalTax());
                    break;
                case 7:
                    System.out.println(findApprovedAmount(community));
                    break;
                case 8:
                    printTotalVolunteerHours(community.totalVolunteerHours());
                    break;
                default:
                    System.out.println("Wrong choice - Please choose again");
            }
            choice = menu();
        }
        System.out.println("\nThank you for using Community Program, we hope you found it useful.");
    }

    private static void addMember() throws NotFullyBusyException, IllegalArgumentException {
        CommunityMember member = null;
        int choice;
        //       reader = new Scanner(System.in);
        System.out.println("For married member press 1. For single member press 2:");
        while (member == null) {
            // Reading from System.in
            choice = reader.nextInt();
            switch (choice) {
                case 1:
                    member = memberInit(MemberStatus.MARRIED);
                    break;
                case 2:
                    member = memberInit(MemberStatus.SINGLE);
                default:
                    System.out.println("Wrong choice - Please choose again");
            }
        }

        community.addMembers(member);
    }

    private static double findApprovedAmount(Community community) {
        int id;
        double amount = -1;
        System.out.println("Please insert the ID of the member:");
        while (amount == -1) {
            //read user input
//            reader = new Scanner(System.in);  // Reading from System.in
            id = reader.nextInt();

            amount = community.approvedAmountFromGemach(id);
            if (amount == -1) {
                System.out.println("Wrong ID, Please insert again:");
            }
        }
        return amount;
    }

    private static void printAction(List<CommunityMember> communityMembers, Action action) {
        int id = -1;
        Boolean found = false;
        System.out.println("Please insert the ID of the member:");
        while (!found && id != 0) {
            //read the user input
//            reader = new Scanner(System.in);  // Reading from System.in
            id = reader.nextInt();

            for (CommunityMember member : communityMembers) {
                if (Objects.equals(member.getId(), id)) {
                    switch (action) {
                        case ENTITLEMENT:
                            System.out.println(member.entitlement());
                            found = true;
                            break;
                        case VOLUNTEER_HOURS:
                            System.out.println(member.recommendedVolunteerHours());
                            found = true;
                            break;
                        case COMMUNITY_TAX:
                            System.out.println(member.communityTax());
                            found = true;
                            break;
                    }
                }
            }
            if (!found) {
                System.out.println("Wrong ID, Please insert again or press 0 to go back to the menu:");
                id = reader.nextInt();
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
        String menu = "\nPlease select your choice:\n" +
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
        //Reading from System.in
//        reader = new Scanner(System.in);
        choice = reader.nextInt();
        return choice;
    }

    private static Community init() throws NotFullyBusyException, IllegalArgumentException{
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


    private static CommunityMember memberInit(MemberStatus status) throws NotFullyBusyException {
//        reader = new Scanner(System.in);
        CommunityMember communityMember = null;
        System.out.println("Enter ID number:");
        int id = reader.nextInt();
        System.out.println("Enter 0 for male, 1 for female:");
        int choice = -1;
        while ((choice = Integer.parseInt(reader.next())) != 0 && choice != 1) ;
        Gender gender = Gender.values()[choice];
        reader.nextLine();
        System.out.println("Enter name:");
        String name = reader.nextLine();
        System.out.println("Enter address:");
        String address = reader.nextLine();
        String[] date = null;
        while (date == null || date.length < 3) {
            System.out.println("Enter birthday Date in three numbers with spaces:");
            String userDate = reader.nextLine();
            date = userDate.split(" ");
        }
        Date birthday = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        System.out.println("Enter Torah weekly hours:");
        double torahStudy = reader.nextDouble();
        System.out.println("Enter work weekly hours:");
        double workHours = reader.nextDouble();
        System.out.println("Enter month income:");
        double income = reader.nextDouble();
        System.out.println("Enter percent of Gemch usage:");
        double gemachUsage = reader.nextDouble();
        choice = -1;
        System.out.println("Enter 0 for SPIRITUALLY donation, 1 for PHYSICALLY donation, and 2 for MUSICAL donation:");
        while ((choice = reader.nextInt()) != 0 && choice != 1 && choice != 2) ;
        Donation donation = Donation.values()[choice];
        switch (status) {
            case MARRIED:
                System.out.println("Enter spouse id:");
                int spouseId = reader.nextInt();
                System.out.println("Enter sum of children:");
                int numChildren = reader.nextInt();
                communityMember = new Married(id, gender, name, address, birthday, torahStudy, workHours, income, gemachUsage, donation, spouseId, numChildren);
            case SINGLE:
                System.out.println("Enter number of study years:");
                int studyYears = reader.nextInt();
                communityMember = new Single(id, gender, name, address, birthday, torahStudy, workHours, income, gemachUsage, donation, studyYears);
        }
        return communityMember;
    }
}
