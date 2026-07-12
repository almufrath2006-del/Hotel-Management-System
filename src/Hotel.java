import java.util.*;
import java.sql.*;
public class Hotel {
    Set<Integer>Avrooms=new LinkedHashSet<>();
    void add(String name,int phno,String email,String rmtyp,String frm,String to,int days,String roomsno,int rooms,int amont ){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rooms","root","1234");
            Statement st=con.createStatement();
            ResultSet rs1=st.executeQuery("select * from customer;");
            int rs=st.executeUpdate("insert into customer value('"+name+"',"+phno+",'"+email+"','"+rmtyp+"','"+frm+"','"+to+"',"+days+",'"+roomsno+"',"+rooms+","+amont+");");
            String romno[]=roomsno.split(",");
            for (String i : romno) {
                int j=Integer.parseInt(i);
                Avrooms.add(j);
            }
            System.out.println("Customer Added.");
        }
        catch(Exception e){}
    }
    void Remove(String name,String name2){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rooms","root","1234");
            Statement st=con.createStatement();
            if (!name2.equals("muf")) {
                ResultSet rs=st.executeQuery("select room_no from customer where name='"+name2+"';");
                while (rs.next()) {
                    String roomspl[]=rs.getString(1).split(",");
                    for (String s : roomspl) {
                        int i=Integer.parseInt(s);
                        Avrooms.remove(i);
                    }
                }int r=st.executeUpdate("delete from customer where name='"+name2+"';");
                if (r!=0) {
                    System.err.println("Customer removed");
                }
                else{
                    System.out.println("No Customers found");
                }
            }   
            if (!name.equals("muf")) { 
                ResultSet rs2=st.executeQuery("select amount from customer where name='"+name+"';");
                rs2.next();
                int r=st.executeUpdate("update wallet set wallet=wallet+"+rs2.getInt(1)+";");
                ResultSet rs=st.executeQuery("select room_no from customer where name='"+name+"';");
                while (rs.next()) {
                    String roomspl[]=rs.getString(1).split(",");
                    for (String s : roomspl) {
                        int i=Integer.parseInt(s);
                        Avrooms.remove(i);
                    }
                }
                int r2=st.executeUpdate("delete from customer where name='"+name+"';");
                if (r2!=0) {
                    System.err.println("Customer removed");
                }
                else{
                    System.out.println("No Customers found");
                }
            }   
        }
        catch(Exception e){
            System.out.println("No Customers Found");
        }
    }
    void search(int rno,String name){
      try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rooms","root","1234");
            Statement st=con.createStatement();
            if(rno!=0){
            ResultSet rs=st.executeQuery("select * from customer where room_no like '%"+rno+"%';");
            rs.next();
            System.out.println("\nName:"+rs.getString(1)+"\nPh no:"+rs.getInt(2)+"\nE-mail:"+rs.getString(3)+"\nRoom type:"+rs.getString(4)+"\nFrom:"+rs.getString(5)+"\nTo:"+rs.getString(6)+"\nDays:"+rs.getInt(7)+"\nRooms No:"+rs.getString(8)+"\nRooms:"+rs.getInt(9)+"\nAmount:"+rs.getInt(10)); 
            }
            else if(rno==0){
                ResultSet rs=st.executeQuery("select * from customer where name='"+name+"';");
                rs.next();
                System.out.println("\nName:"+rs.getString(1)+"\nPh no:"+rs.getInt(2)+"\nE-mail:"+rs.getString(3)+"\nRoom type:"+rs.getString(4)+"\nFrom:"+rs.getString(5)+"\nTo:"+rs.getString(6)+"\nDays:"+rs.getInt(7)+"\nRooms No:"+rs.getString(8)+"\nRooms:"+rs.getInt(9)+"\nAmount:"+rs.getInt(10)); 
            
            }
        }
        catch(Exception e){
            System.out.println("No Customers Found");
        }   
    }
    void ViewAllCustomer(){
        try{
            int i=0;
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rooms","root","1234");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from customer;");
            while(rs.next()){
                System.out.println("\nName:"+rs.getString(1)+"\nPh no:"+rs.getInt(2)+"\nE-mail:"+rs.getString(3)+"\nRoom type:"+rs.getString(4)+"\nFrom:"+rs.getString(5)+"\nTo:"+rs.getString(6)+"\nDays:"+rs.getInt(7)+"\nRooms No:"+rs.getString(8)+"\nRooms:"+rs.getInt(9)+"\nAmount:"+rs.getInt(10));
                i++;
            }
            System.out.println("Total Customers:"+i);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    void Wallet(){
      try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rooms","root","1234");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select wallet from wallet;");
            rs.next();
                System.out.println("Amount:"+rs.getString(1));
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        Hotel ht=new Hotel();
        Set<Integer>roomnoset=new HashSet<>();
        Scanner sc=new Scanner(System.in);
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rooms","root","1234");
            Statement st=con.createStatement();
            ResultSet rs1=st.executeQuery("select room_no from customer;");
            while (rs1.next()) {
                String i[]=rs1.getString(1).split(",");
                for (String string : i) {
                int j=Integer.parseInt(string);
                ht.Avrooms.add(j);
                }
            }
        }
        catch(Exception e){}
        while (true) {
            System.out.println("\n1.Add Customer\n2.Remove Customer\n3.Search\n4.Occupied Rooms\n5.View All Customer\n6.Wallet\n0 for stop");
            int choice=sc.nextInt();
            if(choice==1) {
                sc.nextLine();
                while (true) {
                    System.out.println("Enter Name:");
                    String name=sc.nextLine();
                    if (name.equals("0"))break;
                    System.out.println("Enter Ph.No:");
                    int phno=sc.nextInt();
                    if(phno==0)break;
                    sc.nextLine();
                    System.out.println("Enter E-Mail:");
                    String email=sc.nextLine();
                    if(email.equals("0"))break;
                    String rmtyp=null;int rmamt=0;String roomsno=null;int rooms=0;
                    System.out.println("\nSelect Room Type\n1.Simple 2000 1-5/\n2.A/c 3000 6-10/\n3.Dulex 4000 11-15/\n4.Ultra Dulex 5000 16-20/");
                    int choice2=sc.nextInt();      
                    if (choice2==1) {
                      sc.nextLine();
                        int w=0;
                        while (true) {
                            System.out.println("Rooms no");
                            roomsno=sc.nextLine();
                            if(roomsno.equals("0"))break;
                            String roomplt[]=roomsno.split(",");int m=0;
                            for (String s : roomplt) {
                                int o=Integer.parseInt(s);
                                roomnoset.add(o);
                            }
                            if(roomplt.length==roomnoset.size()){
                                for (int o : roomnoset) {
                                    if(o>0&&o<6){
                                        rooms=roomplt.length;
                                        for (int i : ht.Avrooms) {
                                            if (o==i) {
                                                System.out.println("Room no "+o+" Occupied");
                                            }
                                            else{
                                                m++;
                                            } 
                                        }
                                        if (m==ht.Avrooms.size()*rooms){
                                            rmtyp="Simple 2000/";rmamt=2000;w=1;break;
                                        }
                                    }
                                    else{
                                        System.out.println("Simple Rooms are 1 to 5");
                                    }
                                }
                                roomnoset.clear();
                            }
                            else{
                                System.out.println("Entered same room number multiple times");
                            }if (w==1)break;
                        }
                    }
                    else if (choice2==2) {
                       sc.nextLine();
                        int w=0;
                        while (true) {
                            System.out.println("Rooms no");
                            roomsno=sc.nextLine();
                            if(roomsno.equals("0"))break;
                            String roomplt[]=roomsno.split(",");int m=0;
                            for (String s : roomplt) {
                                int o=Integer.parseInt(s);
                                roomnoset.add(o);
                            }
                            if(roomplt.length==roomnoset.size()){
                                for (int o : roomnoset) {
                                    if(o>5&&o<11){
                                        rooms=roomplt.length;
                                        for (int i : ht.Avrooms) {
                                            if (o==i) {
                                                System.out.println("Room no "+o+" Occupied");
                                            }
                                            else{
                                                m++;
                                            } 
                                        }
                                        if (m==ht.Avrooms.size()*rooms){
                                            rmtyp="A/C 3000/";rmamt=3000;w=1;break;
                                        }
                                    }
                                    else{
                                        System.out.println("A/C Rooms are 6 to 10");
                                    }
                                }
                                roomnoset.clear();
                            }
                            else{
                                System.out.println("Entered same room number multiple times");
                            }if (w==1)break;
                        }
                    }
                    else if(choice2==3){
                        sc.nextLine();
                        int w=0;
                        while (true) {
                            System.out.println("Rooms no");
                            roomsno=sc.nextLine();
                            if(roomsno.equals("0"))break;
                            String roomplt[]=roomsno.split(",");int m=0;
                            for (String s : roomplt) {
                                int o=Integer.parseInt(s);
                                roomnoset.add(o);
                            }
                            if(roomplt.length==roomnoset.size()){
                                for (int o : roomnoset) {
                                    if(o>10&&o<16){
                                        rooms=roomplt.length;
                                        for (int i : ht.Avrooms) {
                                            if (o==i) {
                                                System.out.println("Room no "+o+" Occupied");
                                            }
                                            else{
                                                m++;
                                            } 
                                        }
                                        if (m==ht.Avrooms.size()*rooms){
                                            rmtyp="Dulex 4000/";rmamt=4000;w=1;break;
                                        }
                                    }
                                    else{
                                        System.out.println("Dulex Rooms are 11 to 15");
                                    }
                                }
                                roomnoset.clear();
                            }
                            else{
                                System.out.println("Entered same room number multiple times");
                            }if (w==1)break;
                        }
                    }
                    else if(choice2==4){
                        sc.nextLine();
                        int w=0;
                        while (true) {
                            System.out.println("Rooms no");
                            roomsno=sc.nextLine();
                            if(roomsno.equals("0"))break;
                            String roomplt[]=roomsno.split(",");int m=0;
                            for (String s : roomplt) {
                                int o=Integer.parseInt(s);
                                roomnoset.add(o);
                            }
                            if(roomplt.length==roomnoset.size()){
                                for (int o : roomnoset) {
                                    if(o>15&&o<21){
                                        rooms=roomplt.length;
                                        for (int i : ht.Avrooms) {
                                            if (o==i) {
                                                System.out.println("Room no "+o+" Occupied");
                                            }
                                            else{
                                                m++;
                                            } 
                                        }
                                        if (m==ht.Avrooms.size()*rooms){
                                            rmtyp="Super Dulex 5000/";rmamt=5000;w=1;break;
                                        }
                                    }
                                    else{
                                        System.out.println("Super Dulex Rooms are 16 to 20");
                                    }
                                }
                                roomnoset.clear();
                            }
                            else{
                                System.out.println("Entered same room number multiple times");
                            }if (w==1)break;
                        }
                    }
                    else if (choice2==0){
                        break;
                    }
                    else{
                        System.out.println("Invalid option");
                    }                      
                    System.out.println("from Date(01 apr)");
                    String frm=sc.nextLine();
                    if (frm.equals("0"))break;
                    System.out.println("To Date(02 apr)");
                    String to=sc.nextLine();
                    if (to.equals("0"))break;
                    String frmsp[]=frm.split(" ");
                    String tosp[]=to.split(" ");
                    int a=Integer.parseInt(tosp[0]);
                    int b=Integer.parseInt(frmsp[0]);
                    int days=a-b;
                    int amount=(rmamt*days)*rooms;
                    ht.add(name,phno,email,rmtyp,frm,to,days,roomsno,rooms,amount);
                }
            }
            else if (choice==2) {
                sc.nextLine();
                System.out.println("\n1.Paid\n2.Remove");
                int choi=sc.nextInt();
                sc.nextLine();
                String name="muf";String name2="muf";
                if(choi==1){
                    System.out.println("Enter Customer Name");
                    name=sc.nextLine();
                }
                else if (choi==2) {
                    System.out.println("Enter Customer Name");
                    name2=sc.nextLine();
                }
                else if(choi==0)break;
                ht.Remove(name,name2);
            }
            else if (choice==3) {
                sc.nextLine();
                System.out.println("1.Name\n2.Room No");
                String name="muf";int rno=0;
                int choi=sc.nextInt();
                sc.nextLine();
                if (choi==1) {
                    System.out.println("Enter customer name:");
                    name=sc.nextLine();
                }
                else if(choi==2){
                    System.out.println("Enter room no:");
                    rno=sc.nextInt();
                }
                ht.search(rno,name);
            }
            else if(choice==4){
                for (int i : ht.Avrooms) {
                    if (i>0&&i<6) {
                        System.out.println("S "+i);
                    }
                    else if(i>5&&i<11){
                        System.out.println("A/c "+i);
                    }
                    else if(i>10&&i<16){
                        System.out.println("D "+i);
                    }
                    else if(i>15&&i<21){
                        System.out.println("SD "+i);
                    }
                }
            }
            else if (choice==5) {
                ht.ViewAllCustomer();
            }
            else if (choice==6) {
                ht.Wallet();
            }
            else if (choice==0)break;
            else{
                System.out.println("Invalid choice");
            }
        }
    }
}