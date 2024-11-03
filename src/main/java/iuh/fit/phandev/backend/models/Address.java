package iuh.fit.phandev.backend.models;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "street", length = 150)
    private String street;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "country")
    private CountryCode country;

    @Column(name = "number", length = 20)
    private String number;

    @Column(name = "zipcode", length = 7)
    private String zipcode;

    @OneToOne(mappedBy = "address")
    private Candidate candidate;

    @OneToOne(mappedBy = "address")
    private Company company;
    public Address( String zipcode, String street, String city, String number,CountryCode country) {
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
        this.number = number;
    }

    public Address(long id,String street,  String city, String zipcode,CountryCode country) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
        this.number = number;
    }

}