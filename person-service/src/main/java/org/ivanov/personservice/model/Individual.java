package org.ivanov.personservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Setter
@Getter
@Entity
@Table(name = "individuals", schema = "person")
public class Individual extends BaseModel {

    @Column(name = "passport_number", nullable = false, unique = true, length = 64)
    private String passportNumber;

    @Column(name = "phone_number", nullable = false, unique = true, length = 64)
    private String phoneNumber;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
