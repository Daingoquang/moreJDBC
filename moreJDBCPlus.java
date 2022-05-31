import java.sql.*;
public class moreJDBCPlus {
    public static void main(String[] args) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true","root", ""
                );
                Statement statement= connection.createStatement();
        ) {
            try {
                connection.setAutoCommit(false);
//
                statement.executeUpdate("insert into books values (4001, 'Paul chan', 'Mahjong 101', 4.4, 4)");
                statement.executeUpdate("insert into books values (4002, 'Paul chan', 'Mahjong 102', 4.4, 4)");
//
                connection.commit();

            } catch (SQLException ex) {
                System.out.println("-- Rolling back changes --");
                connection.rollback();
                ex.printStackTrace();
            }
//
//            ResultSet resultSet = statement.executeQuery("select * from books");
//            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//
//            int numColumns = resultSetMetaData.getColumnCount();
//
//            for (int i = 1; i <= numColumns; ++i){
//                System.out.println("%-30s"+","+ resultSetMetaData.getColumnName(i));
//            }
//            System.out.println();
//
//            for (int i = 1; i <= numColumns; ++i){
//                System.out.println("%-30s"+","+"(" +resultSetMetaData.getColumnClassName(i)+")");
//            }
//            System.out.println();
//
//            while (resultSet.next()){
//                for (int i = 1; i <= numColumns; ++i){
//                    System.out.println("%-30s"+","+resultSet.getString(i));
//                }
//                System.out.println();
//            }
        }
    }
}
