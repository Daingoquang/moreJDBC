import java.sql.*;

public class moreJDBCSuper {
    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true", "root", ""
                );
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into books values(?, ?, ?, ?, ?) "
            );
//            Turn-off auto commit
            connection.setAutoCommit(false);
//
            preparedStatement.setInt(1,8003);
            preparedStatement.setString(2,"Java 123");
            preparedStatement.setString(3,"kevin John");
            preparedStatement.setDouble(4,12.21);
            preparedStatement.setInt(5,8);
            preparedStatement.addBatch();
//
            preparedStatement.setInt(1,8004);
            preparedStatement.setString(2,"Java 456");
            preparedStatement.addBatch();
//
            int[] returnCodes = preparedStatement.executeBatch();
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
