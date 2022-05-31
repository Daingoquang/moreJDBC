import java.sql.*;

public class jbdcPrepareStatementTest {
    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true", "root", ""
                );
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into books values(?, ?, ?, ?, ?)"
                );
                PreparedStatement preparedStatementSelect = connection.prepareStatement("select * from books");
        ) {
//            Set values
            preparedStatement.setInt(1, 7001);
            preparedStatement.setString(2,"Mahjong 102");
            preparedStatement.setString(3,"Kumar");
            preparedStatement.setDouble(4, 88.88);
            preparedStatement.setInt(5,6);
            int rowInserted = preparedStatement.executeUpdate();
            System.out.println(rowInserted + " row affected");
//            Change values
            preparedStatement.setInt(1, 7002);
            preparedStatement.setString(2,"Mahjong 120");
            rowInserted = preparedStatement.executeUpdate();
            System.out.println(rowInserted + " row affected");
//              Issue a Select
            ResultSet resultSet = preparedStatementSelect.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id")+","
                        +resultSet.getString("author")+","
                        +resultSet.getString("title")+","
                        +resultSet.getDouble("price")+","
                        +resultSet.getInt("qty")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
