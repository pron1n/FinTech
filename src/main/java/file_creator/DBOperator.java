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

    public int getUserIdByName(Connection conn, User user) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet id = stmt.executeQuery("select id from persons where surname = '" + user.name.getLast() + "'" + " and " +
        "name ='" + user.name.getFirst() + "'" + " and " + "middlename = '" + user.name.getPatronymic() + "'");
        id.next();

        return id.getInt(1);
    }

    public void updateUserInfoById(Connection conn, User user) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate("update persons set birthday = " +
                "str_to_date('" + user.dob.getDate() + "', '%d-%m-%Y')" + ", "
                + "inn = '" + user.getInn() + "' where id = " + String.valueOf(getUserIdByName(conn, user)));
        stmt.executeUpdate("upadate address set postcode = '" + String.valueOf(user.getPostcode()) + "', "
                + "country = '" + user.getNat() + "', "
                + "region = '" + user.location.getState() + "', "
                + "city = '" + user.location.getCity() + "', "
                + "street = '" + user.location.getStreet() + "', "
                + "house = " + String.valueOf(user.location.getBldNumber()) + ", "
                + "flat = " + String.valueOf(user.location.getAptNumber()) +
                "' where id = " + String.valueOf(getForeignKey(conn, user)));
    }

    public int getForeignKey(Connection conn, User user) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet id = getResultSet(conn, "select id from address where postcode = '" + String.valueOf(user.getPostcode()) + "'" + " and "
                + "country = '" + user.getNat() + "'" + " and "
                + "region = '" + user.location.getState() + "'" + " and "
                + "city = '" + user.location.getCity() + "'" + " and "
                + "street = '" + user.location.getStreet() + "'" + " and "
                + "house = " + String.valueOf(user.location.getBldNumber()) + " and "
                + "flat = " + String.valueOf(user.location.getAptNumber()));
        id.next();

        return id.getInt(1);
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
        stmt.executeUpdate("insert into persons (surname, name, middlename, birthday, gender, inn, address_id)" +
                " values " + "(" + "'" + user.name.getLast() + "'" + ", "
                + "'" + user.name.getFirst() + "'" + ", "
                + "'" + user.name.getPatronymic() + "'" + ", "
                + "str_to_date('" + user.dob.getDate() + "', '%d-%m-%Y')" + ", "
                + "'" + user.getGender() + "'" + ", "
                + "'" + user.getInn() + "'" + ", "
                + String.valueOf(getForeignKey(conn, user)) + ")");
    }
}
