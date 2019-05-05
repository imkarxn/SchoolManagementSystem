package sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class mysql
{
    static Connection con;
    static Statement stm;
    private static void connect() {
        try {

            String url = "jdbc:mysql://localhost:3306/db_project";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, "MP1", "mysqlpw");
            stm = con.createStatement();
        }
        catch (Exception e) {System.out.println(e);}
    }
    private static void disconnect(){
        try{
            stm.close();
            con.close();
        }
        catch (Exception e) {System.out.println(e);}
    }
    public static boolean Authenticate(String username,String password,String Authtype)
    {
        connect();
        String query="SELECT * FROM "+Authtype+"pw WHERE userid='"+username+"' and passsword='"+password+"'";
        try{ResultSet rs=stm.executeQuery(query);
            if(rs.next())
            {
                disconnect();
                return true;
            }}
        catch (Exception e) {System.out.println(e);}
        disconnect();
        return false;
    }
    public static void addAttandance(String rollnumber,boolean present,int day,String abcd)
    {
        int i=0;
        if(present) i=1;
        String query="UPDATE `student"+abcd+"` SET `Day"+day+"`="+i+" WHERE `RollNo`=\""+rollnumber+"\"";
        firequery(query);
        return;
    }
    public static void getTotal(String rollnumber,String abcd)
    {
        String query="SELECT * FROM `student"+abcd+"` WHERE `RollNo`=\""+rollnumber+"\"";
        int total=0;
        try
        {
            ResultSet rs=stm.executeQuery(query);
            rs.next();
            for(int i=2;i<=31;i++) total+=rs.getInt(i);
            query="UPDATE `student"+abcd+"` SET `Total`="+total+" WHERE `RollNo`=\""+rollnumber+"\"";
            firequery(query);
        }
        catch (Exception e)
        {
            System.out.println(query+" "+e);
        }


    }
    public static void firequery(String query)
    {
        connect();
        try
        {
            stm.executeUpdate(query);
        }
        catch (Exception e)
        {
            System.out.println(query+" "+e);
        }

        return;
    }

    public static ResultSet query2result(String query)
    {
        connect();
        System.out.println(query);
        try
        {
            ResultSet rs=stm.executeQuery(query);
            return rs;
        } catch (Exception e) {System.out.println(query+"Invalid Query");}
        return null;
    }
}
