package source;

import enums.Label;
import enums.Rights;
import enums.SQLTable;
import static enums.SQLTable.*;
import enums.ApplicationState;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides communication with MySQL database. Constructor
 * establishes connection used in every method called after the constructor. SQL
 * command to create the database with proper structure and some data is
 * included in this project. String encoding is set to UTF-8, so it should
 * hanhle properly even localized characters. Everything regarding connection to
 * database is in constructor. For communication with database is used JDBC
 * framework. For communicating with database are used only parameterized
 * statements making database immune to SQL injection.
 *
 * @author Azathoth
 */
public class Mysql {

    private final Connection conn;
    private PreparedStatement ps;
    private final String url;
    private final String dbName;
    private final String uname;
    private final String pwd;

    /**
     * Defines variables and tries to establish connection with SQL database.
     *
     * @throws java.lang.ClassNotFoundException when establishing a connection
     * fails
     * @throws java.sql.SQLException when jdbs driver is not found
     */
    public Mysql() throws ClassNotFoundException, SQLException {                //vyhazuje výjimky, které mohou být chyceny servletem, který uživatele přesměruje na statickou stránku a oznámí mu problém
        url = "jdbc:mysql://localhost:3306/";                                   //pro localhost
        dbName = "mysql";                                                       //pro localhost
        uname = "root";
        pwd = "";
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url + dbName + "?useUnicode=true&characterEncoding=utf-8", uname, pwd);    //kvůli UTF-8 kódování při komunikaci s mysql databází
    }

    /**
     * Tries to login user and returns instance of LoggedUser with data from SQL
     * table.
     *
     * @param username username user put to login form
     * @param password hash of password user put to login form
     * @return Object LoggedUser containing all important data about user,
     * returns UserFailingInLogin, when login is not successful
     * @throws java.sql.SQLException when something goes wrong with database
     */
    public LoggedUser login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM " + SQLTable.LOGIN.getTable() + " where " + Label.USERNAME.getNameRaw() + "=? and " + Label.PASSWORD.getNameRaw() + "=?";
        ps = conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();                                   //pro parametrizovaný dotaz
        int size = 0;
        int rights = Integer.MAX_VALUE;                                       //číselná práva jsou od nuly výš, max value tedy nepřidělí platná práva
        String name = "";
        String lastname = "";
        while (rs.next()) {
            username = rs.getString(Label.USERNAME.getNameRaw());
            name = rs.getString(Label.NAME.getNameRaw());
            lastname = rs.getString(Label.LASTNAME.getNameRaw());
            rights = rs.getInt("rights");
            size++;
        }
        if (size == 1) {                                                        //podmínkou pro úspěšné přihlášení se je právě jedna shoda
            Rights rightsObject = Rights.getRightsFromInt(rights);
            return new LoggedUser(name, lastname, rightsObject, username);
        }
        return new UserFailingInLogin();
    }

    /**
     * Adds new ip address with number of registrations set to 1 to table of ip
     * addresses.
     *
     * @param IP IP address to be added
     * @return returns true when adding is successful and returns false, when
     * something goes wrong
     * @throws java.sql.SQLException when something goes wrong with database
     */
    public boolean insertNewIP(String IP) throws SQLException {
        String sql = "INSERT INTO ip_adresa(ip, count) VALUES(?,?)";
        ps = conn.prepareStatement(sql);                                          //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1, IP);
        ps.setInt(2, 1);
        int rs = ps.executeUpdate();                                            //pro parametrizovaný dotaz
        return rs == 1;
    }

    /**
     * Finds IP address and returns number of registrations from this address.
     *
     * @param IP IP address to be find
     * @return number of registrations from provided IP address, returns 0 when
     * address is not found in database
     * @throws java.sql.SQLException when something goes wrong with database
     */
    public int findIP(String IP) throws SQLException {
        String sql = "SELECT * FROM ip_adresa where ip=?";
        ps = conn.prepareStatement(sql);                                          //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1, IP);
        ResultSet rs = ps.executeQuery();                                       //pro parametrizovaný dotaz
        while (rs.next()) {
            return rs.getInt("count");
        }
        return 0;
    }

    /**
     * Increases number of registrations from provided IP address by one.
     *
     * @param IP IP address whose number of registrations shall be incremented
     * @return true when modyfiing row was succesful, otherwise return false;
     * @throws java.sql.SQLException when something goes wrong with database
     */
    public boolean increaseIPcount(String IP) throws SQLException {
        String sql = "UPDATE ip_adresa SET count = count+1 WHERE ip = ?";
        ps = conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1, IP);
        int rs = ps.executeUpdate();                                        //pro parametrizovaný dotaz
        return rs == 1;
    }

    /**
     * Adds new applicant to login table and to applicants table.
     *
     * @param table table, where new applicant should be stored: uchazeci,
     * uchazeci_spam or uchazeci_ipspam
     * @param input array with all info about new applicant
     * @return returns true when adding is successful and returns false, when
     * something goes wrong
     * @throws java.sql.SQLException when something goes wrong with database
     */
    public boolean insertNewApplicant(SQLTable table, Map<Label, String> input) throws SQLException {
        input.put(Label.APPLICATION_STATE, ApplicationState.getDefaultBoolean().getName());
        input.put(Label.TUITION, "nezaplaceno");
        boolean output1 = insertNewUserToLogin(input, Rights.APPLICANT);
        boolean output2 = insertApplicant(table, input);
        return output1 && output2;
    }

    /**
     * Returns all users from provided SQL table.
     *
     * @param table whose content you want to get
     * @return List of Map<Label, String> where is stored all info from provided
     * table
     * @throws java.sql.SQLException when something goes wrong with database
     */
    public List<Map<Label, String>> showPeople(SQLTable table) throws SQLException {                        //protože název tabulky nejde parametrizovat, je to ošetřeno přes výčtový typ
        List<Map<Label, String>> output = new ArrayList<>();
        String sql = "SELECT * FROM " + table.getTable() + " where 1";
        ps = conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Map<Label, String> temp = new LinkedHashMap<>();
            for (Label label : Label.values()) {
                if (label.isInTable(table)) {
                    temp.put(label, rs.getString(label.getNameRaw()));
                }
            }
            output.add(temp);
        }
        for (int i = 0; i < output.size(); i++) {
            sql = "SELECT * FROM " + SQLTable.LOGIN.getTable() + " where " + SQLTable.LOGIN.getPrimaryKey().getNameRaw() + " = ?";
            ps = conn.prepareStatement(sql);                                //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1, output.get(i).get(SQLTable.LOGIN.getPrimaryKey()));
            rs = ps.executeQuery();                          //pro parametrizovaný dotaz
            while (rs.next()) {
                for (Label label : Label.values()) {
                    if (label.isInTable(SQLTable.LOGIN)) {
                        String temp = rs.getString(label.getNameRaw());
                        output.get(i).put(label, temp);
                    }
                }
            }
        }
        return output;
    }

    /**
     * Returns all content from provided table which has same String in provided
     * criteriumColumn as is provided criterium and if negate is true, returns
     * all content from provided table which has not same String in provided
     * criteriumColumn as is provided criterium
     *
     * @param table whose content you want to get
     * @param criterium String which shall shown data has or not (depending on
     * provided negate)
     * @param criteriumColumn Name of column whose content shall be compared
     * with String provided in criterium
     * @param negate determines whether wanted data should or should not match
     * String provided in criterium
     * @return List of Map<Label, String> where is stored all info from provided
     * @throws SQLException when something goes wrong with database
     */
    public List<Map<Label, String>> showPeople(SQLTable table, String criterium, Label criteriumColumn, boolean negate) throws SQLException {
        List<Map<Label, String>> output = new ArrayList<>();
        String sql;
        if (negate) {
            sql = "SELECT * FROM " + table.getTable() + " where " + criteriumColumn.getNameRaw() + "<>?";
        } else {
            sql = "SELECT * FROM " + table.getTable() + " where " + criteriumColumn.getNameRaw() + "=?";
        }
        ps = conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1, criterium);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Map<Label, String> temp = new LinkedHashMap<>();
            for (Label label : Label.values()) {
                if (label.isInTable(table)) {
                    temp.put(label, rs.getString(label.getNameRaw()));
                }
            }
            output.add(temp);
        }
        for (Map<Label, String> map : output) {
            sql = "SELECT * FROM " + SQLTable.LOGIN.getTable() + " where " + SQLTable.LOGIN.getPrimaryKey().getNameRaw() + " = ?";
            ps = conn.prepareStatement(sql);                                //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1, map.get(SQLTable.LOGIN.getPrimaryKey()));
            rs = ps.executeQuery();                          //pro parametrizovaný dotaz
            while (rs.next()) {
                for (Label label : Label.values()) {
                    if (label.isInTable(SQLTable.LOGIN)) {
                        String temp = rs.getString(label.getNameRaw());
                        map.put(label, temp);
                    }
                }
            }
        }
        return output;
    }

    /**
     * Dynamicaly creates update statement for provided Map and SQLTable
     *
     * @param user Map containing Labels which will be used for creating update
     * statement
     * @param table SQLTable where shall be update executed
     * @return SQL prepared statement
     */
    private String createUpdateStatement(Map<Label, String> user, SQLTable table) {
        String sql = "UPDATE " + table.getTable() + " SET ";
        for (Label label : user.keySet()) {
            if (label.isInTable(table) && !label.isPrimaryKey()) {
                sql += label.getNameRaw() + " = ?, ";
            }
        }
        sql = sql.substring(0, sql.length() - 2);                                   //odtrhne poslední čárku a mezeru za ní
        sql += " WHERE " + table.getPrimaryKey().getNameRaw() + "= ?";
        return sql;
    }

    /**
     * Changes data in Map of all applicants provident in List of Map
     *
     * @param table whose data shall be updated
     * @param applicant List of Map which contains all data which shall be
     * updated
     * @return true when updating is successful and returns false, when
     * something goes wrong
     * @throws SQLException when something goes wrong with database
     */
    public boolean updateApplicants(SQLTable table, List<Map<Label, String>> applicant) throws SQLException {
        int[] rs = new int[applicant.size() * 2];
        for (int i = 0; i < applicant.size(); i++) {                            //update se provede pro každého uchazeče
            String sql = createUpdateStatement(applicant.get(i), table);
            ps = conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            int count = 0;
            for (Label label : applicant.get(i).keySet()) {
                if (!label.isPrimaryKey() && label.isInTable(table)) {
                    count++;                                                    //číslování v preparedStatementu začíná od jedné
                    ps.setString(count, applicant.get(i).get(label));
                }
            }
            count++;
            ps.setString(count, applicant.get(i).get(table.getPrimaryKey()));

            rs[2 * i] = ps.executeUpdate();
            sql = createUpdateStatement(applicant.get(i), SQLTable.LOGIN);
            ps = conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            count = 0;
            for (Label label : applicant.get(i).keySet()) {
                if (!label.isPrimaryKey() && label.isInTable(SQLTable.LOGIN)) {
                    count++;                                                        //číslování v preparedStatementu začíná od jedné
                    ps.setString(count, applicant.get(i).get(label));
                    System.out.println("ps.setString(" + count + "," + applicant.get(i).get(label) + ");");
                }
            }
            count++;
            ps.setString(count, applicant.get(i).get(table.getPrimaryKey()));
            rs[2 * i + 1] = ps.executeUpdate();
        }
        boolean output = true;
        for (int i = 0; i < rs.length; i++) {
            if (rs[i] != 1) {
                output = false;
            }
        }
        return output;
    }

    /**
     * Changes password of chosen user.
     *
     * @param username of user, whose password should be changed
     * @param password new password to be set
     * @return true when updating is successful and returns false, when
     * something goes wrong
     * @throws SQLException when something goes wrong with database
     */
    public boolean updatePassword(String username, String password) throws SQLException {
        String sql = "UPDATE " + SQLTable.LOGIN.getTable() + " SET " + Label.PASSWORD.getNameRaw() + "=? where " + SQLTable.LOGIN.getPrimaryKey().getNameRaw() + "=?";
        ps = conn.prepareStatement(sql);                                          //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1, password);
        ps.setString(2, username);
        int rs = ps.executeUpdate();                                            //pro parametrizovaný dotaz
        return rs == 1;
    }

    /**
     * Validates ID, checks if it is unique.
     *
     * @param id ID to be checked if is unique or not
     * @return returns true in case of unique id, otherwise return false
     * @throws java.sql.SQLException when something goes wrong with database
     */
    public boolean validateId(String id) throws SQLException {
        String sql = "SELECT * FROM " + SQLTable.LOGIN.getTable() + " where " + SQLTable.LOGIN.getPrimaryKey().getNameRaw() + "=?";
        ps = conn.prepareStatement(sql);                                          //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();                                       //pro parametrizovaný dotaz
        return !rs.next();                                                      //pokud má resultset next, pak id není jedinečné
    }

    /**
     * Returns all data about chosen applicant from chosen table.
     *
     * @param username of user whose data will be returned
     * @param table where is chosen user stored
     * @return returns all data in chosen table as Map<Label, String>
     * @throws java.sql.SQLException when something goes wrong with database
     */
    public Map<Label, String> showApplicant(String username, SQLTable table) throws SQLException {
        Map<Label, String> output = new LinkedHashMap<>();
        String sql = "SELECT * FROM " + table.getTable() + " where " + table.getPrimaryKey().getNameRaw() + " = ?";
        ps = conn.prepareStatement(sql);                                          //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            for (Label label : Label.values()) {
                if (label.isInTable(table)) {
                    output.put(label, rs.getString(label.getNameRaw()));
                }
            }
        }
        sql = "SELECT * FROM " + SQLTable.LOGIN.getTable() + " where " + SQLTable.LOGIN.getPrimaryKey().getNameRaw() + " = ?";
        ps = conn.prepareStatement(sql);                                        //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1, output.get(SQLTable.LOGIN.getPrimaryKey()));
        rs = ps.executeQuery();                                                 //pro parametrizovaný dotaz
        while (rs.next()) {
            for (Label label : Label.values()) {
                if (label.isInTable(SQLTable.LOGIN)) {
                    output.put(label, rs.getString(label.getNameRaw()));
                }
            }
        }
        return output;
    }

    /**
     * Changes data provided in Map in provided SQL table.
     *
     * @param applicant Map containing data to be set to table, must contain
     * primary key
     * @param table SQL table where shall be data changed
     * @return true when updating is successful and returns false, when
     * something goes wrong
     * @throws SQLException when something goes wrong with database
     */
    public boolean updateApplicant(Map<Label, String> applicant, SQLTable table) throws SQLException {
        int rs;
        String sql = createUpdateStatement(applicant, table);
        ps = conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        int count = 0;
        for (Label label : applicant.keySet()) {
            if (!label.isPrimaryKey() && label.isInTable(table)) {
                count++;
                ps.setString(count, applicant.get(label));
            }
        }
        count++;
        ps.setString(count, applicant.get(table.getPrimaryKey()));

        rs = ps.executeUpdate();
        return rs == 1;
    }

    /**
     * Returns SQL table where is applicant with provided username.
     *
     * @param username which shall be searched for in all tables
     * @return returns one of tables: APPLICANTS, APPLICANTS_SPAM,
     * APPLICANTS_IPSPAM
     * @throws java.sql.SQLException when something goes wrong with database
     * @throws java.lang.NoSuchFieldException when applicant is not in any of
     * these three tables
     */
    public SQLTable findTableWithApplicant(String username) throws SQLException, NoSuchFieldException {
        SQLTable[] tables = {APPLICANTS, APPLICANTS_SPAM, APPLICANTS_IPSPAM};
        for (SQLTable tables1 : tables) {
            String sql = "SELECT * FROM " + tables1.getTable() + " where " + tables1.getPrimaryKey().getNameRaw() + " = ?";
            ps = conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return tables1;
            }
        }
        throw new NoSuchFieldException("User " + username + " was not found.");
    }

    /**
     * Returns Map with data from login table about user with provided username.
     *
     * @param username of user whose data shall be returned
     * @return data of user with same username as provided
     * @throws SQLException when something goes wrong with database
     */
    public Map<Label, String> showLoginInfoOfUser(String username) throws SQLException {
        Map<Label, String> output = new LinkedHashMap<>();
        String sql = "SELECT * FROM " + SQLTable.LOGIN.getTable() + " where " + SQLTable.LOGIN.getPrimaryKey().getNameRaw() + " = ?";
        ps = conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            for (Label label : Label.values()) {
                if (label.isInTable(SQLTable.LOGIN)) {
                    output.put(label, rs.getString(label.getNameRaw()));
                }
            }
        }
        return output;
    }

    /**
     * Transfers applicant from one table to another.
     *
     * @param tableFrom table where is applicant stored
     * @param applicantUsername username of applicant who shall be transfered
     * @param tableTo table where shall applicant be trasferred
     * @return true if transfer is successful, otherwise return false
     * @throws SQLException when something goes wrong with database
     */
    public boolean transferApplicant(SQLTable tableFrom, String applicantUsername, SQLTable tableTo) throws SQLException {
        Map<Label, String> input = showApplicant(applicantUsername, tableFrom);
        boolean temp1 = insertApplicant(tableTo, input);
        boolean temp2 = deleteRow(applicantUsername, tableFrom);
        return temp1 && temp2;
    }

    /**
     * Inserts applicant to provided SQL table.
     *
     * @param table where shall be applicant added
     * @param input all data about applicant who shall be added
     * @return true if adding is successful, otherwise return false
     * @throws SQLException when something goes wrong with database
     */
    public boolean insertApplicant(SQLTable table, Map<Label, String> input) throws SQLException {
        String sql = createInsertStatement(table);
        return executeInsertStatement(sql, input, table);
    }

    /**
     * Deletes all data about provided user.
     *
     * @param username of user who shall be deleted
     * @param table where is user who shall be deleted
     * @return true if deleting is successful, otherwise return false
     * @throws SQLException when something goes wrong with database
     */
    public boolean deleteRow(String username, SQLTable table) throws SQLException {
        String sql = "DELETE FROM " + table.getTable() + " where " + table.getPrimaryKey().getNameRaw() + "=?";
        ps = conn.prepareStatement(sql);                                          //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1, username);
        int rs = ps.executeUpdate();                                                //pro parametrizovaný dotaz
        return rs == 1;
    }

    /**
     * Inserts user to login table.
     *
     * @param input data about new user who shall be added to login table
     * @param rights of user who shall be added
     * @return true if adding is successful, otherwise return false
     * @throws SQLException when something goes wrong with database
     */
    public boolean insertNewUserToLogin(Map<Label, String> input, Rights rights) throws SQLException {
        input.put(Label.RIGHTS, Integer.toString(rights.getRightsValue()));
        String sql = createInsertStatement(SQLTable.LOGIN);
        return executeInsertStatement(sql, input, SQLTable.LOGIN);
    }

    /**
     * Inserts user to pedagog table.
     *
     * @param input data about new user who shall be added to pedagog table
     * @return true if adding is successful, otherwise return false
     * @throws SQLException when something goes wrong with database
     */
    public boolean insertNewPedagog(Map<Label, String> input) throws SQLException {
        boolean output1 = insertNewUserToLogin(input, Rights.PEDAGOG);
        String sql = createInsertStatement(SQLTable.PEDAGOGOVE);
        boolean output2 = executeInsertStatement(sql, input, SQLTable.PEDAGOGOVE);
        return output1 && output2;
    }

    /**
     * Inserts user to administrativa table.
     *
     * @param input data about new user who shall be added to administrativa
     * table
     * @return true if adding is successful, otherwise return false
     * @throws SQLException when something goes wrong with database
     */
    public boolean insertNewAdministrativa(Map<Label, String> input) throws SQLException {
        boolean output1 = insertNewUserToLogin(input, Rights.ADMINISTRATIVA);
        String sql = createInsertStatement(SQLTable.ADMINISTRATIVA);
        boolean output2 = executeInsertStatement(sql, input, SQLTable.ADMINISTRATIVA);
        return output1 && output2;
    }

    /**
     * Inserts user to student table.
     *
     * @param input data about new user who shall be added to student table
     * @return true if adding is successful, otherwise return false
     * @throws SQLException when something goes wrong with database
     */
    public boolean insertNewStudent(Map<Label, String> input) throws SQLException {
        boolean output1 = updateRights(input.get(SQLTable.LOGIN.getPrimaryKey()), Rights.STUDENT);
        String sql = createInsertStatement(SQLTable.STUDENTS);
        boolean output2 = executeInsertStatement(sql, input, SQLTable.STUDENTS);
        return output1 && output2;
    }

    /**
     * Changes rights of user with provided username to provided rights.
     *
     * @param username of user whose rights shall be changed
     * @param rights new rights which shall be put to row where user with
     * provided username is
     * @return true if change is successful, otherwise return false
     * @throws SQLException when something goes wrong with database
     */
    public boolean updateRights(String username, Rights rights) throws SQLException {
        String sql = "UPDATE " + SQLTable.LOGIN.getTable() + " SET " + Label.RIGHTS.getNameRaw() + "=? where " + SQLTable.LOGIN.getPrimaryKey().getNameRaw() + "=?";
        ps = conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setInt(1, rights.getRightsValue());
        ps.setString(2, username);
        int rs = ps.executeUpdate();                                   //pro parametrizovaný dotaz
        return rs == 1;
    }

    /**
     * Creates prepared statement for inserting new user to provided SQL table.
     *
     * @param table for which shall be prepared statement for insert created
     * @return Sting containing SQL prepared statement
     */
    private String createInsertStatement(SQLTable table) {
        String sql = "INSERT INTO " + table.getTable() + "(";
        for (Label label : Label.values()) {
            if (label.isInTable(table)) {
                sql += label.getNameRaw();
                sql += ", ";
            }
        }
        sql = sql.substring(0, sql.length() - 2);                                   //odtrhne poslední čárku a mezeru za ní
        sql += ") VALUES(";
        for (Label label : Label.values()) {
            if (label.isInTable(table)) {
                sql += "?,";
            }
        }
        sql = sql.substring(0, sql.length() - 1);                                   //odtrhne poslední čárku
        sql += ")";
        System.out.println("Sestavené SQL pro tabulku " + table + " je: " + sql);
        return sql;
    }

    /**
     * Executed prepared insert statement.
     *
     * @param sql String with parameterized insert statement.
     * @param input Data which shall be inserted into table
     * @param table where should be new data inserted
     * @return true if adding is successful, otherwise return false
     * @throws SQLException when something goes wrong with database
     */
    private boolean executeInsertStatement(String sql, Map<Label, String> input, SQLTable table) throws SQLException {
        ps = conn.prepareStatement(sql);                                          //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        int count = 0;
        for (Label label : Label.values()) {
            if (label.isInTable(table)) {
                count++;
                if (input.get(label) == null) {
                    throw new NullPointerException("Chyba při vkládání do tabulky " + table + ", sql dotaz je " + sql + ", input má null pod labelem " + label);
                }
                ps.setString(count, input.get(label));
            }
        }
        int rs = ps.executeUpdate();
        return rs == 1;
    }
}
