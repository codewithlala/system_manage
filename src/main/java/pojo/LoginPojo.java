package pojo;

public class LoginPojo {
    private String username;
    private String password;

    // Default constructor
    public LoginPojo() {
    }

    // Parameterized constructor
    public LoginPojo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Override toString method for debugging purposes
    @Override
    public String toString() {
        return "LoginPojo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
