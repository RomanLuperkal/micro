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
@Table(name = "addresses", schema = "person")
public class Address extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "address", nullable = false, length = 128)
    private String address;

    @Column(name = "zip_code", nullable = false, length = 32)
    private String zipCode;

    @Column(name = "city", nullable = false, length = 128)
    private String city;
}
