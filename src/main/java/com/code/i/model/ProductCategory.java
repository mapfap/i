package com.code.i.model;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter @Setter @ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
public class ProductCategory {

    @Id
    @NaturalId
    @EqualsAndHashCode.Include
    @Column(updatable = false, nullable = false)
    private UUID uuid;

    @NotBlank
    @Column(nullable = false)
    private String name;
}
