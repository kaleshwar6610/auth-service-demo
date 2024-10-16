package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "UserDetails")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    //The * in a regex means 0 or an unlimited amount.
    // It will not allow other characters in your regex [a-zA-Z ]*,
    // but it will allow empty strings.
    @Pattern(message = "Bad format for userName",
            regexp = "[A-Za-z]*")
    @NotBlank(message = "Please add userName")
    @Length(min = 3)
    @JsonProperty(value="userName")
    private String userName;

    //^                 # start-of-string
    //(?=.*[0-9])       # a digit must occur at least once
    //(?=.*[a-z])       # a lower case letter must occur at least once
    //(?=.*[A-Z])       # an upper case letter must occur at least once
    //(?=.*[@#$%^&+=])  # a special character must occur at least once
    //(?=\S+$)          # no whitespace allowed in the entire string
    //.{8,}             # anything, at least eight places though
    //$                 # end-of-string
    @Pattern(message = "bad format for password",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    @NotBlank(message = "Please add password")
    @JsonProperty(value="password")
    private String password;

    @Email(message = "bad format for email",
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotBlank(message = "Please add email")
    @JsonProperty(value="email")
    private String email;

    @Pattern(message = "Bad format for role",
            regexp = "[A-Za-z0-9]*")
    @NotBlank(message = "Please add role")
    @Length(max = 12, min = 1)
    @JsonProperty(value="role")
    private String role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
