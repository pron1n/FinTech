package file_creator;

import file_creator.user.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOperator {

    public Connection getConnectionToDB() throws SQLException, ClassNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/DBConnection_parameters.dat"));
        String value;
        List<String> values = new ArrayList<>();

        while ((value = br.readLine()) != null) {
            values.add(value);
        }

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://@" + values.get(0) + ":" + values.get(1) + "/" + values.get(2);
        String user = values.get(3);
        String pass = values.get(4);
        Connection conn = DriverManager.getConnection(url, user, pass);

        return conn;
    }

    private ResultSet getResultSet(Connection conn, String query) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return rs;
    }

    public int getUserIdInDB(Connection conn, User user) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet id = stmt.executeQuery("select id from persons where surname = '" + user.name.getLast() + "'" + " and " +
        "name ='" + user.name.getFirst() + "'" + " and " + "middlename = '" + user.name.getPatronymic() + "'");
        id.next();
        try {
            return id.getInt(1);
        } catch (Exception e) {
            return 0;
        }
    }

    public void updateUserInfo(Connection conn, User user) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate("update persons set birthday = " +
                "str_to_date('" + user.dob.getDate() + "', '%d-%m-%Y')" + ", "
                + "inn = '" + user.getInn() + "' where id = " + String.valueOf(getUserIdInDB(conn, user)));
        stmt.executeUpdate("update address set postcode = '" + String.valueOf(user.getPostcode()) + "', "
                + "country = '" + user.getNat() + "', "
                + "region = '" + user.location.getState() + "', "
                + "city = '" + user.location.getCity() + "', "
                + "street = '" + user.location.getStreet() + "', "
                + "house = " + String.valueOf(user.location.getBldNumber()) + ", "
                + "flat = " + String.valueOf(user.location.getAptNumber()) +
                " where id = " + String.valueOf(getForeignKeyByUser(conn, user)));
    }

    private int getForeignKeyByUser(Connection conn, User user) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet fk = getResultSet(conn, "select address_id from persons where surname = '" + user.name.getLast() + "'" + " and " +
                "name ='" + user.name.getFirst() + "'" + " and " + "middlename = '" + user.name.getPatronymic() + "'");
        fk.next();

        return fk.getInt(1);
    }

    private int getForeignKeyByUserAddress(Connection conn, User user) throws SQLException, ClassNotFoundException {
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
                + String.valueOf(getForeignKeyByUserAddress(conn, user)) + ")");
    }

    public int getNumberOfUsersInDB(Connection conn) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet count = stmt.executeQuery("select count(*) from persons");
        count.next();

        return count.getInt(1);
    }

    public User getRandomUserFromDB(Connection conn) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet userPersonData = stmt.executeQuery("select * from persons order by rand() limit 1");

        User user = new User();
        while (userPersonData.next()) {
            user.name.setFirst(userPersonData.getString("name"));
            user.name.setLast(userPersonData.getString("surname"));
            user.name.setPatronymic(userPersonData.getString("middlename"));
            user.setGender(userPersonData.getString("gender"));
            user.dob.setDate(userPersonData.getDate("birthday").toString());
            user.setInn(userPersonData.getString("inn"));
        }

        ResultSet userAddressData = stmt.executeQuery("select * from address order by rand() limit 1");
        while (userAddressData.next()) {
            user.setPostcode(Integer.valueOf(userAddressData.getString("postcode")));
            user.setNat(userAddressData.getString("country"));
            user.location.setState(userAddressData.getString("region"));
            user.location.setCity(userAddressData.getString("city"));
            user.location.setStreet(userAddressData.getString("street"));
            user.location.setBldNumber(Integer.valueOf(userAddressData.getString("house")));
            user.location.setAptNumber(Integer.valueOf(userAddressData.getString("flat")));
        }

        return user;
    }
}
