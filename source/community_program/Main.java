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
import java.util.Scanner;



public class Main {
    public static  Scanner reader;
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
                        CommunityMember member = addMember();
                        community.addMembers(member);
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
                    memberEntitlement(community.getCommunityMembers(), "community tax");
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

    private static CommunityMember addMember() throws NotFullyBusyException {
        CommunityMember member = null;
        int choice;
        reader = new Scanner(System.in);
        System.out.println("For married member press 1. For single member press 2:");
        while (member == null) {
              // Reading from System.in
            choice = reader.nextInt();
            switch (choice) {
                case 1:
                    member = memberInit("married");
                    break;
                case 2:
                    member = memberInit("single");
                default:
                    System.out.println("Wrong choice - Please choose again");
            }
        }

        return member;
    }

    private static double findApprovedAmount(Community community) {
        int id;
        double amount = -1;
        System.out.println("Please insert the ID of the member:");
        while (amount == -1) {
            //read user input
            reader = new Scanner(System.in);  // Reading from System.in
            id = reader.nextInt();

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
            //read the user input
            reader = new Scanner(System.in);  // Reading from System.in
            id = reader.nextInt();

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
        //Reading from System.in
        reader = new Scanner(System.in);
        choice = reader.nextInt();
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


    private static CommunityMember memberInit(String type)throws NotFullyBusyException{
        reader = new Scanner(System.in);
        System.out.println("Enter ID number: ");
        int id = reader.nextInt();
        System.out.println("Enter 0 for male and 1 for female: ");
        int choice = -1;
        while ((choice = Integer.parseInt(reader.next())) != 0 && choice != 1);
        Gender gender = Gender.values()[choice];
        reader.nextLine();
        System.out.println("Enter name: ");
        String name = reader.nextLine();
        System.out.println("Enter address: ");
        String address = reader.nextLine();
        String[] date = null;
        while(date == null || date.length < 3) {
            System.out.println("Enter birthday Date in tree numbers whit space's: ");
            String userDate = reader.nextLine();
            date = userDate.split(" ");
        }
        Date birthday = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        System.out.println("Enter hoers of Tora shady: ");
        double toraStady = reader.nextDouble();
        System.out.println("Enter hoers of work: ");
        double workHours = reader.nextDouble();
        System.out.println("Enter income of month: ");
        double income = reader.nextDouble();
        System.out.println("Enter percent of gmch usage: ");
        double gmachUsage = reader.nextDouble();
        choice = -1;
        System.out.println("Enter 0 for SPIRITUALLY donation, 1 for PHYSICALLY donation, and 2 for MUSICAL donation: ");
        while ((choice = reader.nextInt()) != 0 && choice != 1 && choice != 2);
        Donation donation = Donation.values()[choice];
        switch (type){
            case "married":
                System.out.println("Enter spouse id: ");
                int spouseId = reader.nextInt();
                System.out.println("Enter sum children: ");
                int numChildren = reader.nextInt();
                return new Married(id, gender, name, address, birthday, toraStady, workHours, income, gmachUsage, donation, spouseId, numChildren);
            case "single":
                System.out.println("Enter the number of steady years: ");
                int stadyYears = reader.nextInt();
                return new Single(id, gender, name, address, birthday, toraStady, workHours, income, gmachUsage, donation, stadyYears);
        }
        return null;

    }
}
