import  java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Driver {
    public static void main(String[] args) {
        mysqlconnect connect = new mysqlconnect();
        connect.queryXml();
        connect.SqlConnection();


    }
}
