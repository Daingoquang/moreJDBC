import java.sql.*;

public class moreJdbc {
    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true","root", ""
                );
        ) {
            Statement statement= connection.createStatement();
//
            connection.setAutoCommit(false);
            ResultSet resultSet = statement.executeQuery("select id, qty from books where id in(1001, 1002)");
            System.out.println("--  Before UPDATE --");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id")+","+ resultSet.getInt("qty"));
            }
//
            connection.commit();
            statement.executeUpdate("update books set qty = qty + 1 where id=1001");
            statement.executeUpdate("update books set qty = qty + 1 where id=1002");
//
            connection.commit();
            resultSet = statement.executeQuery("select id, qty from books where id in(1001, 1002)");
            System.out.println("-- After UPDATE and Commit --");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id")+","+ resultSet.getInt("qty"));
            }
//
            connection.commit();
            statement.executeUpdate("update books set qty = qty - 99 where id=1001");
            statement.executeUpdate("update books set qty = qty - 99 where id=1002");
//
            connection.rollback();
            resultSet = statement.executeQuery("select id, qty from books where id in(1001, 1002)");
            System.out.println("-- After UPDATE and Rollback --");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id")+","+ resultSet.getInt("qty"));
            }
//
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
