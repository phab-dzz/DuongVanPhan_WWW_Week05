package iuh.fit.phandev.backend.models;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "country")
    private CountryCode country;

    @Column(name = "zipcode", length = 7)
    private String zipcode;

    @Column(name = "number", length = 20)
    private String number;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "street", length = 150)
    private String street;

    @OneToOne(mappedBy = "address")
    private Candidate candidate;

    @OneToOne(mappedBy = "address")
    private Company company;

    public Address( String number, String street,String city, String zipcode, CountryCode country) {
        this.country = country;
        this.zipcode = zipcode;
        this.number = number;
        this.city = city;
        this.street = street;
    }

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", zipcode='" + zipcode + '\'' +
                ", country=" + country +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}