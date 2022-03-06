package codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "Must isn't empty or null")
//    @Pattern(regexp = "[a-zA-Z]+", message = "Name is not number")
    private String name;

//    @Pattern(regexp = "^0[0-9]{9,10}", message = "PhoneNumber must have 10 or 11 digits")
    private String phone;

    private String address;

//    @Pattern(regexp = "[0-9]{9}", message = "idCard must have 9 digits")
    private String idCard;

    public Human() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
