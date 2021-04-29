import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class auth {
    Scanner sc = new Scanner(System.in);
    String name,dob,pass,cpass,email,number;
    String usermail = "";
    public void signup(){
        takeName();
        takeEmail();
        takeDob();
        takeNumber();
        takePass();
        storeData();

    }


    private void storeData() {
        String data = email + "\n" + pass + "\n" + name + "\n" + dob + "\n" + number + "\n";
        File file = new File("user.txt");
        try {
            if(file.exists()){
                BufferedWriter bf = new BufferedWriter(new FileWriter(file,true));
                bf.write(data);
                bf.close();
                System.out.println("Account created successfully !");

            }
            else {
                if(file.createNewFile()){
                    BufferedWriter bf = new BufferedWriter(new FileWriter(file));
                    bf.write(data);
                    bf.close();
                    System.out.println("Account created successfully !");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void takePass() {
        System.out.println("Enter your Password (minimum 6 digits) : ");
        pass = sc.nextLine();
        System.out.println("Enter confirm password : ");
        cpass = sc.nextLine();
        if(!pass.equals(cpass) || pass.length() < 6) {
            System.out.println("invalid password :(");
            takePass();
        }

    }

    private void takeDob() {
        System.out.println("Enter your Birth date (DD/MM/YYYY) : ");
        dob = sc.nextLine();
    }

    private void takeNumber() {
        System.out.println("Enter your mobile Number : ");
        number = sc.nextLine();
    }

    private void takeEmail() {
        System.out.println("Enter your Email : ");
        email = sc.nextLine();
        if(!email.contains("@")){
            System.out.println("Invalid EMail :( ");
            takeEmail();
        }
        else if(!email.contains(".")){
            System.out.println("Invalid EMail :( ");
            takeEmail();
        }
        else {
            File file = new File("user.txt");
            String check;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((check = br.readLine()) != null) {
                    if(check.equals(email)) {
                        System.out.println("Email is already taken!");
                        takeEmail();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void takeName() {
        System.out.println("Enter your Name : ");
        name = sc.nextLine();
    }
public void login() {
       String Email,Pass,str;
boolean flag = false;
       System.out.println("Enter your Email \n");
       Email = sc.nextLine();
    System.out.println("Enter your password \n");
       Pass = sc.nextLine();
       File file = new File("user.txt");
       try {
           if(file.exists()) {
               BufferedReader br = new BufferedReader(new FileReader(file));
               while ((str = br.readLine()) != null) {
                   if(str.equals(Email)) {
                       if(br.readLine().equals(Pass)) {
                           System.out.println("You are logged in successfully\n");
                           usermail = Email;
                           flag = true;
                           break;
                       }
                       else
                           System.out.println("incorrect password");
                   }

               }
               if(!flag)
                   System.out.println("User does not Exist :(");
               else
                   services();

br.close();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }

}




    private void services() {
        int choice;
        slabel:
       while (true) {

           System.out.println("*********************** Services *****************************\n");
           System.out.println("  1 -> Book New Room \n");
           System.out.println("  2 -> See Your Bookings \n");
           System.out.println("  3 -> Cancel Room Booking \n");
           System.out.println("  4 -> Exit \n");
           choice = sc.nextInt();
           switch (choice) {
               case 1:
                   newRoom();
                   break;
               case 2:
                   seeRoom();
                   break;
               case 3:
                   cancelRoom();
                   break;
               case 4:
                   System.exit(0);
           }
       }




    }

    private void cancelRoom() {
        String id,day,data,test;
        int choice;
        File file = new File("rooms.txt");
        try {
            Room room = new Room();
            ArrayList<Room> arrayList = new ArrayList<>();
            String[] IDs = new String[50];
            int idx = 0;
            BufferedReader br = new BufferedReader(new FileReader(file));
            System.out.println("Your Bookings : \n\n");

            while (true) {
                id = br.readLine();
                if(id == null)
                    break;
                else
                    room.setId(id);
                room.setBookby(br.readLine());
                room.setPrice(br.readLine());
                room.setIsac(br.readLine());
                room.setIsfood(br.readLine());
                room.setDays(br.readLine());

                if(room.getBookby().equals(usermail)) {
                    System.out.println("Room ID : "+room.getId());
                    System.out.println("Room price : "+room.getPrice());
                    System.out.println("Days : "+room.getDays());
                    System.out.println("\n");
                }
                IDs[idx++] = room.getId();

                arrayList.add(new Room(room.getId(),room.getBookby(),room.getPrice(),room.getIsac(),room.getIsfood(),room.getDays()));


            }
            br.close();

            System.out.println("\n Enter Room ID for cancel booking  :");
            choice = sc.nextInt();
            boolean existID = false;
            String checkID = Integer.toString(choice);
            for(int i = 0; i < IDs.length;i++) {
                if(IDs[i]!=null && IDs[i].equals(checkID)) {
                    existID = true;
                    break;
                }
            }
            if(!existID) {
                System.out.println("Invalid Room ID entered :(");
                Label slabel;
              /*  System.exit(0);*/
            }


            if(arrayList.size() < choice) {
                System.out.println("Invalid room ID: ");
            }
            else{
                Room temp = new Room();
                temp = arrayList.get(choice-1);
                temp.setBookby("null");
                temp.setDays("0");

                arrayList.set(choice-1,temp);

                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                temp = arrayList.get(0);
                data = temp.getId() + "\n" + temp.getBookby() + "\n" + temp.getPrice() + "\n" + temp.getIsac() + "\n" +
                        temp.getIsfood() + "\n" + temp.getDays() + "\n";
                bw.write(data);
                bw.close();
                bw = new BufferedWriter(new FileWriter(file,true));
                Room itr = null;
                for(int i = 1; i < arrayList.size(); i++) {

                    itr = arrayList.get(i);
                    data = itr.getId() + "\n" + itr.getBookby() + "\n" + itr.getPrice() + "\n" + itr.getIsac() + "\n" +
                            itr.getIsfood() + "\n" + itr.getDays() + "\n";
                    bw.write(data);
                    itr = null;

                }
                bw.close();
                System.out.println("Your Room is cancelled successfully !\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void seeRoom() {
        String id,price,bookby,days,test;
        System.out.println("*********************** All your Rooms *****************************\n");
        File file = new File("rooms.txt");
        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            while (true) {
                id = br.readLine();
                if(id == null)
                    break;
                bookby = br.readLine();
                price = br.readLine();
                test = br.readLine();
                test = br.readLine();
                days = br.readLine();
                if(bookby.equals(usermail)) {
                    System.out.println("Room ID : "+id);
                    System.out.println("Room price : "+price);
                    System.out.println("Days : "+days);
                    System.out.println("\n");
                }

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newRoom() {
        String id,day,data,test;
        int choice;
        File file = new File("rooms.txt");
        try {
            Room room = new Room();
            ArrayList<Room> arrayList = new ArrayList<>();
            String[] IDs = new String[50];
            int idx = 0;
            BufferedReader br = new BufferedReader(new FileReader(file));
            System.out.println("Available Rooms : \n\n");
            while (true) {
                id = br.readLine();
                if(id == null)
                    break;
                else
                    room.setId(id);
                room.setBookby(br.readLine());
                room.setPrice(br.readLine());
                room.setIsac(br.readLine());
                room.setIsfood(br.readLine());
                room.setDays(br.readLine());

                if(room.getBookby().equals("null")) {
                    System.out.println("Room ID : "+room.getId());
                    System.out.println("Room price : "+room.getPrice());
                    System.out.println("AC Availability : "+room.getIsac());
                    System.out.println("Food availability : "+room.getIsfood());
                    System.out.println("\n");
                }
                IDs[idx++] = room.getId();
                arrayList.add(new Room(room.getId(),room.getBookby(),room.getPrice(),room.getIsac(),room.getIsfood(),room.getDays()));


            }
            br.close();

            System.out.println("\n Enter Room ID for booking Room :");
            boolean existID = false;
            choice = sc.nextInt();
            test = sc.nextLine();
            String checkID = Integer.toString(choice);
            for(int i=0;i<IDs.length;i++) {
                if(IDs[i]!= null && IDs[i].equals(checkID)) {
                    existID = true;
                    break;
                }
            }
            if(!existID && choice <= 0) {
                System.out.println("Invalid Room ID entered :(");
                Label slabel;
               /* System.exit(0);*/
            }
            if(arrayList.size() < choice) {
                System.out.println("Room not Exist");
            }
            else{
                System.out.println("How many days you want to stay? :");
                day = sc.nextLine();
                Room temp = new Room();
                temp = arrayList.get(choice-1);
                temp.setBookby(usermail);
                temp.setDays(day);

                arrayList.set(choice-1,temp);

                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                temp = arrayList.get(0);
                data = temp.getId() + "\n" + temp.getBookby() + "\n" + temp.getPrice() + "\n" + temp.getIsac() + "\n" +
                        temp.getIsfood() + "\n" + temp.getDays() + "\n";
                bw.write(data);
                bw.close();
                bw = new BufferedWriter(new FileWriter(file,true));
             Room itr = null;
                for(int i = 1; i < arrayList.size(); i++) {

                    itr = arrayList.get(i);
                    data = itr.getId() + "\n" + itr.getBookby() + "\n" + itr.getPrice() + "\n" + itr.getIsac() + "\n" +
                            itr.getIsfood() + "\n" + itr.getDays() + "\n";
                    bw.write(data);
                    itr = null;

                }
                bw.close();
                System.out.println("Your Room is booked successfully !\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
