package file_creator;

import file_creator.user.User;

import java.sql.*;

public class DBOperator {

    public Connection getConnectionToDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://@localhost:3306/Users";
        String user = "root";
        String pass = "Fin_tech19";
        Connection conn = DriverManager.getConnection(url, user, pass);

        return conn;
    }

    public ResultSet getResultSet(Connection conn, String query) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return rs;
    }

    public void isUserPresentsInDB(Connection conn) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from persons");
    }

    public void insertUserIntoDB(Connection conn, User user) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate("insert into address (postcode, country, region, city, street, house, flat)" +
                " values " + "(" + "'" + String.valueOf(user.getPostcode()) + "'" + ", "
                + "'" + user.getNat() + "'" + ", "
                + "'" + user.location.getState() + "'" + ", "
                + "'" + user.location.getCity() + "'" + ", "
                + "'" + user.location.getStreet() + "'" + ", "
                + String.valueOf(user.location.getBldNumber()) + ", "
                + String.valueOf(user.location.getAptNumber()) + ")");
        ResultSet id = getResultSet(conn, "select id from address where postcode = '" + String.valueOf(user.getPostcode()) + "'" + " and "
                + "country = '" + user.getNat() + "'" + " and "
                + "region = '" + user.location.getState() + "'" + " and "
                + "city = '" + user.location.getCity() + "'" + " and "
                + "street = '" + user.location.getStreet() + "'" + " and "
                + "house = " + String.valueOf(user.location.getBldNumber()) + " and "
                + "flat = " + String.valueOf(user.location.getAptNumber()));
        id.next();
        stmt.executeUpdate("insert into persons (surname, name, middlename, birthday, gender, inn, address_id)" +
                " values " + "(" + "'" + user.name.getLast() + "'" + ", "
                + "'" + user.name.getFirst() + "'" + ", "
                + "'" + user.name.getPatronymic() + "'" + ", "
                + "str_to_date('" + user.dob.getDate() + "', '%d-%m-%Y')" + ", "
                + "'" + user.getGender() + "'" + ", "
                + "'" + user.getInn() + "'" + ", "
                + String.valueOf(id.getInt(1)) + ")");

    }


}
