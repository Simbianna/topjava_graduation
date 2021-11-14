package ru.dto;

import lombok.Data;
import ru.model.HasEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Data
public class UserTo extends BaseTo implements HasEmail, Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 5, max = 32, message = "length must be between 5 and 32 characters")
    private String password;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
          //      ", vote='" + vote + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTo)) return false;
        UserTo userTo = (UserTo) o;
        return Objects.equals(getName(), userTo.getName()) &&
                Objects.equals(getEmail(), userTo.getEmail()) &&
                Objects.equals(getPassword(), userTo.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
