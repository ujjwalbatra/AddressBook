import java.sql.ResultSet;
import java.sql.SQLException;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String country;

    public Person(ResultSet resultSet) throws SQLException{
        this.id = resultSet.getInt("id");
        this.firstName = resultSet.getString("firstname");
        this.lastName = resultSet.getString("lastname");
        this.phone = resultSet.getString("phone");
        this.addressLine1 = resultSet.getString("addressline1");
        this.addressLine2 = resultSet.getString("addressline2");
        this.city = resultSet.getString("city");
        this.state = resultSet.getString("state");
        this.zip = resultSet.getString("zip");
        this.country = resultSet.getString("country");
    }

    public Person(String firstName, String lastName, String phone, String addressLine1, String addressLine2, String city, String state, String zip, String country) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }
}