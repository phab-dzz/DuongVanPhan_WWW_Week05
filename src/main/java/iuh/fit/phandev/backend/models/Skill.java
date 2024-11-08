package iuh.fit.phandev.backend.models;

import iuh.fit.phandev.backend.enums.SkillType;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", nullable = false)
    private Long id;

    @Column(name = "type")
    private SkillType type;

    @Column(name = "skill_description")
    private String skillDescription;

    @Column(name = "skill_name")
    private String skillName;

    @OneToMany(mappedBy = "skill")
    private Set<CandidateSkill> candidateSkills = new LinkedHashSet<>();

    @OneToMany(mappedBy = "skill")
    private Set<JobSkill> jobSkills = new LinkedHashSet<>();

}