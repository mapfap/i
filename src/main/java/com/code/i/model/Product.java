package com.code.i.model;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter @Setter @ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
public class Product {

    @Id
    @NaturalId
    @EqualsAndHashCode.Include
    @Column(updatable = false, nullable = false)
    private String sku;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_category_uuid", nullable = false)
    private ProductCategory category;

    @NotBlank
    @Column(nullable = false)
    private String name;

}
