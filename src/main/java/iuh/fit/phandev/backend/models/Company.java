package iuh.fit.phandev.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @Column(name = "about", length = 2000)
    private String about;

    @Column(name = "comp_name", nullable = false)
    private String compName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "web_url", nullable = false)
    private String webUrl;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false, length = 150)
    private String username;

    @OneToMany(mappedBy = "company")
    private Set<Job> jobs = new LinkedHashSet<>();

    public Company(Address address, String about, String compName, String email, String phone, String webUrl) {
        this.address = address;
        this.about = about;
        this.compName = compName;
        this.email = email;
        this.phone = phone;
        this.webUrl = webUrl;
    }

    public Company(Address address, String compName, String about, String email, String phone, String webUrl, String password, String username) {
        this.address = address;
        this.compName = compName;
        this.about = about;
        this.email = email;
        this.phone = phone;
        this.webUrl = webUrl;
        this.password = password;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Company{" +
                "address=" + address +
                ", about='" + about + '\'' +
                ", id=" + id +
                ", compName='" + compName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }
}