package iuh.fit.phandev.backend.ids;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class JobSkillId implements Serializable {
    private long job;
    private long skill;


}
