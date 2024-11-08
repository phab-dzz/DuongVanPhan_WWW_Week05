package iuh.fit.phandev.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @OneToMany(mappedBy = "can")
    private Set<CandidateSkill> candidateSkills = new LinkedHashSet<>();

    public Candidate( String fullName,LocalDate dob, Address address, String phone, String email,String username,  String password) {
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public Candidate(LocalDate dob, String phone, Address address, String email, String fullName) {
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}