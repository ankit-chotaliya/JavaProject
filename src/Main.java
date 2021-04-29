
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int choice;
        while (true) {


            welcome();
            System.out.println("***************************************************************\n");
            System.out.println("  1 -> Sign up for new user \n");
            System.out.println("  2 -> Log in to your account \n");
            System.out.println("  3 -> Add new Room (For Admin) \n");

            System.out.println("  4 -> Exit \n");
            System.out.println("\n***************************************************************\n");
            System.out.println("Enter your choice from above :   ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            // method for performs action according to choice
            performAction(choice);
        }



    }

    private static void performAction(int choice) throws IOException {

        auth authobj = new auth();
        switch (choice) {
            case 1:
                authobj.signup();
                break;
            case 2:
                authobj.login();
                break;
            case 3:
                addRoom();
            case 4:
                System.exit(0);
            default:
                System.out.println("Invalid choice :(\n");
        }
    }

    private static void addRoom() {
        String ID,price,bookby="null",isac,isfood,days="0";
        Scanner sc = new Scanner(System.in);
        String pass;
        System.out.println("Enter Admin key : ");
        pass = sc.nextLine();
        if(!pass.equals("thisadmin0011")) {
            System.out.println("Invalid Access !\n Try again");
            System.exit(0);
        }
        else {
            String itrID,temp;
            File file1 = new File("rooms.txt");
            String[] arr = new String[50];
            boolean exist = false;
            int idx = 0;

            try {
                BufferedReader br = new BufferedReader(new FileReader(file1));
                while (true) {
                    itrID = br.readLine();
                    if(itrID == null)
                        break;
                    arr[idx++] = itrID;
                    temp = br.readLine();
                    temp = br.readLine();
                    temp = br.readLine();
                    temp = br.readLine();
                    temp = br.readLine();
                }
                br.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                exist = false;
                System.out.println("Enter Room ID : ");
                ID = sc.nextLine();
                for(int k=0;k<idx;k++) {
                    if(arr[k].equals(ID)) {
                        exist = true;
                        break;
                    }
                }
                if(exist) {
                    System.out.println("Room with this Room ID is already exist !");
                }
                else
                    break;
            }
            System.out.println("Enter price : ");
            price = sc.nextLine();

            while (true) {
                System.out.println("AC Available? (y/n) : ");
                isac = sc.nextLine();
                if(!isac.equals("y") && !isac.equals("n") && !isac.equals("Y") && !isac.equals("N")) {
                    System.out.println("Please type valid option :(");
                }
                else if(isac.equals("y") || isac.equals("Y")) {
                    isac = "true";
                    break;
                }
                else{
                    isac = "false";
                    break;
                }
            }

            while (true) {
                System.out.println("Food Available? (y/n) : ");
                isfood = sc.nextLine();
                if(!isfood.equals("y") && !isfood.equals("n") && !isfood.equals("Y") && !isfood.equals("N")) {
                    System.out.println("Please type valid option :(");
                }
                else if(isfood.equals("y") || isfood.equals("Y")) {
                    isfood = "true";
                    break;
                }
                else{
                    isfood = "false";
                    break;
                }
            }


            File file = new File("rooms.txt");
            String data = ID + "\n" + bookby + "\n" + price + "\n" + isac + "\n" + isfood + "\n" + days + "\n";
            try {
                if(file.exists()) {
                    BufferedWriter br = new BufferedWriter(new FileWriter(file,true));
                    br.write(data);
                    br.close();
                }
                else {
                    if(file.createNewFile()) {
                        BufferedWriter br = new BufferedWriter(new FileWriter(file));
                        br.write(data);
                        br.close();
                    }
                }
                System.out.println("Room added successfully !");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // method for heading design
    private static void welcome() {
        System.out.println("***************************************************************\n");
        System.out.println("                    HOTEL MANAGEMENT                           \n");
        System.out.println("***************************************************************");
    }

}
