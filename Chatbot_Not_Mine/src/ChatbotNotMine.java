import java.sql.*;
import java.util.*;
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class ChatbotNotMine{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        String Globaltell="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_projects","root","");
            System.out.println("Enter Your Choice-");
            System.out.println("Press 1 for Admin Login");
            System.out.println("Press 2 for Guest Login");
            System.out.println("Note:- You won't be able to revert your choices...");
            int ch = sc.nextInt();
            switch(ch){
                case 1:
                    String usr;
                    String pwd;
                    System.out.print("Enter your Username: ");
                    usr=sc.next();
                    System.out.print("Enter your Password: ");
                    pwd=sc.next();
                    if(usr.equals("x") && pwd.equals("x")){
                        System.out.println("Login success");
                        String commq;
                        while(true){
                            String str;
                            if(Globaltell.equals("")){
                                System.out.print("-----> : ");
                                str=reader.readLine();
                            }
                            else{
                                str=Globaltell;
                            }
                            String[] str1 = str.split(" ");
                            commq=str;
                            if(str.equals("hmm") || str.equals("hm") || str.equals("ok") || str.equals("yes") || str.equals("n say")){
                                Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_projects","root","");
                                String[] que = new String[] {"what is your name","how old are you","do you like videogames","which is your fav song","do you love me","your city","your college","your enrollment no"};
                                Random rand = new Random();
                                int rand1=rand.nextInt(que.length);
                                String ques=que[rand1];
                                System.out.println("Que = "+ques);
                                String[] q = ques.split(" ");
                                for(String my : q){
                                    if(q.equals("me") ||q.equals("fav") ||q.equals("your") ||q.equals("which") ||q.equals("you") ||q.equals("are") ||q.equals("when") || q.equals("what") || q.equals("how") || q.equals("where") || q.equals("will") || q.equals("you") || q.equals("why") || q.equals("is") || q.equals("your") || q.equals("do") || q.equals("u") || q.equals("you") || q.equals("like")){
                                        continue;
                                    }
                                    System.out.print("Answer : ");
                                    String askAns=reader.readLine();
                                    if(askAns.equals("why") || askAns.equals("what")){
                                        System.out.println("please tell acc to context");
                                        break;
                                    }
                                    PreparedStatement pst23 = conn1.prepareStatement("Insert INTO not_me(human,bot) Values(?,?)");
                                    pst23.setString(1, my);
                                    pst23.setString(2, askAns);
                                    int res = pst23.executeUpdate();
                                    String[] greet = new String[]{"ok","nice","awesome","whaah","ghazab","badiya"};
                                    int rand2=rand.nextInt(greet.length);
                                    String finalAns=greet[rand2];
                                    System.out.print(finalAns +" ");
                                    Globaltell="";
                                    break;
                                }
                            }
                            String Finale="";
                            for(String a:str1){
                                if(str1.length != 1){
                                    if(a.equals("when") || a.equals("what") || a.equals("how") || a.equals("where") || a.equals("will") || a.equals("why") || a.equals("is") || a.equals("did")|| a.equals("can")|| a.equals("the")|| a.equals("was")|| a.equals("in")|| a.equals("as")|| a.equals("a")|| a.equals("an")|| a.equals("of")|| a.equals("do")|| a.equals("use")|| a.equals("become")|| a.equals("and")){
                                        continue;
                                    }
                                }
                                Finale+=a;
                                Finale+=" ";
                            }
                            // System.out.println("Finale = "+Finale);
                            PreparedStatement pst=con.prepareStatement("select bot from not_me where human = ?");
                            pst.setString(1,Finale);
                            ResultSet rs=pst.executeQuery();
                            if(rs.isBeforeFirst()){
                                while(rs.next()){
                                    System.out.print("---->"+rs.getString(1)+" ");
                                    Connection Conag = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_projects", "root","");
                                    PreparedStatement pst13 = Conag.prepareStatement("insert Into not_me Values(?,?)");
                                    pst13.setString(1,rs.getString(1));
                                    System.out.println("");
                                    System.out.print("----> : ");
                                    String ip = reader.readLine();
                                    pst13.setString(2, ip);
                                    pst13.executeUpdate();
                                    Globaltell=ip;
                                    break;
                                }
                            }
                            else{
                                str=commq;
                                String resp;
                                System.out.println(Finale+" what ?");
                                str.toString().toLowerCase();
                                resp=reader.readLine();
                                PreparedStatement pst1=con.prepareStatement("INSERT INTO not_me(human,bot) VALUES(?,?)");
                                pst1.setString(1, Finale);
                                pst1.setString(2, resp);
                                int rs1=pst1.executeUpdate();
                                Connection Conag = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_projects", "root","");
                                PreparedStatement pst13 = Conag.prepareStatement("insert Into not_me Values(?,?)");
                                pst13.setString(1,resp);
                                System.out.println(" ok ");
                                System.out.print("----> ");
                                String ip = reader.readLine();
                                pst13.setString(2, ip);
                                pst13.executeUpdate();
                                Globaltell=ip;
                            }
                        }
                    }
                    break;
                case 2:
                    while(true){
                        System.out.print("Que : ");
                        String str=reader.readLine();
                        PreparedStatement pst=con.prepareStatement("select bot from not_me where human = ?");
                        pst.setString(1, str);
                        ResultSet rs=pst.executeQuery();
                        if(rs.isBeforeFirst()){
                            while(rs.next()){
                                System.out.println(rs.getString(1));
                            }
                        }
                        else{
                            System.out.println("sorry i dont know anything about that, please ask the creator beside you -_-!!!");
                        }
                        if(str.equals("bye")){
                            break;
                        }
                    }
            }
        }
        catch(IOException | ClassNotFoundException | SQLException ex){
            System.out.println(ex);
        }
    }
}
