package iuh.fit.phandev.backend.models;

import iuh.fit.phandev.backend.enums.SkillLevel;
import iuh.fit.phandev.backend.ids.CandidateSkillId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "candidate_skill")
public class CandidateSkill {
    @EmbeddedId
    private CandidateSkillId id;

    @MapsId("canId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "can_id", nullable = false)
    private Candidate can;

    @MapsId("skillId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private iuh.fit.phandev.backend.models.Skill skill;

    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;

    @Column(name = "more_infos", length = 1000)
    private String moreInfos;

}