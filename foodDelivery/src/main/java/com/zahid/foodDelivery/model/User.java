package com.zahid.foodDelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;


   @NotEmpty(message = "UserName is Empty!!!")
   @Size(min=3,max=20, message = "User name cannot be less than 3 letters or greater than 20 letters")
    private String name;

   @NotNull
////    @Pattern(regexp = "^[a-zA-Z0-9_+&*-] + (?:\\\\.[a-zA-Z0-9_+&*-]\n" +
////            "+ )*@(?:[a-zA-Z0-9-]+\\\\.) + [a-zA-Z]{2, 7}$ ")
  @NotEmpty(message = "Email is Empty!!!")
    private String email;

      @NotNull
////    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8, 20}$")
    @NotEmpty(message = "password is Empty!!!")
    private String password;
    //@NotNull
    private String role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<AuthenticationToken> authenticationTokens;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<FoodHistory> foodHistories;

}
