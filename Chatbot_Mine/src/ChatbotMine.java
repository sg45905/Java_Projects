import java.util.Scanner;
import java.sql.*;

public class ChatbotMine{
    public static void main(String[] args){
        String h,b,usr,pwd;
        int ch;
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your choice: ");
        ch = sc.nextInt();
        switch(ch){
            case 1:
                System.out.println("Hello Admin...");
                System.out.println("Please provide your user name and password");
                usr = sc.next();
                pwd = sc.next();
                if(usr.equals("hello") && pwd.equals("hii")){
                    train();
                }
            default:
                System.out.println("Please provide a valid choice");
        }
    }
    public static void train(){
        String usr = "",bot = "";
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_projects","root","");
            Statement st = conn.createStatement();
            while(true){
                System.out.print("---> ");
                usr = sc.nextLine();
                if(usr.equals("quit") || usr.equals("exit")){
                    break;
                }else{
                    System.out.print(": ");
                    bot = sc.nextLine();
                    int m = st.executeUpdate("insert into me values ('"+usr+"','"+bot+"')");
                }
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
