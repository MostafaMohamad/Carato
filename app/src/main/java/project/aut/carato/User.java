package project.aut.carato;

public class User {
    String name;
    String uname;
    String email;
    String gender;
    String birth;
    String pass;
    String type;

    public User(String name, String uname, String email, String gender, String birth, String pass, String type) {
        this.name = name;
        this.uname = uname;
        this.email = email;
        this.gender = gender;
        this.birth = birth;
        this.pass = pass;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
