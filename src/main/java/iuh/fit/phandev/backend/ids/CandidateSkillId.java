package iuh.fit.phandev.backend.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CandidateSkillId implements java.io.Serializable {
    private static final long serialVersionUID = -6071680186494900617L;
    @Column(name = "can_id", nullable = false)
    private Long canId;

    @Column(name = "skill_id", nullable = false)
    private Long skillId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CandidateSkillId entity = (CandidateSkillId) o;
        return Objects.equals(this.skillId, entity.skillId) &&
                Objects.equals(this.canId, entity.canId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, canId);
    }

}