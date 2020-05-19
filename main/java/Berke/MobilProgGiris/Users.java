package Berke.MobilProgGiris;

public class Users {
    private String username ;
    private String password;

    private char sex;
    private int weight;
    private int height;
    private int age;
    private char appMode;


    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
