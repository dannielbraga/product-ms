package com.compasso.productms.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "Field name is required")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "Field description is required")
    private String description;

    @Column(name = "price", precision = 20, scale = 2)
    @NotNull(message = "Field price is required")
    @Positive(message = "Field price must be positive")
    private BigDecimal price;
}
