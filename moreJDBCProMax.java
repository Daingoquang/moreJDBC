import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class moreJDBCProMax {
    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true", "root", ""
                );
        ) {
//            Turn-off auto commit
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
//
            statement.addBatch("insert into books values(8001, 'Java ABC', 'Kevin John', 99.99, 9)");
            statement.addBatch("insert into books values(8002, 'Java XYZ', 'Kevin John 2', 99.99, 9)");
            statement.addBatch("update books set price = 11.11 where id = 8001 or id = 8002");
//
            int[] returnCodes = statement.executeBatch();
            System.out.println("Return code are: ");
            for (int code : returnCodes){
                System.out.println(code+" ,");
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}