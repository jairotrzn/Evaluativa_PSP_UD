import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {

   private String name;
    private String password;
    private String registro;

    public User() {sc
        this.name ="";
        this.password ="";
        this.registro = LocalDate.now().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", registro='" + registro + '\'' +
                '}';
    }
}
