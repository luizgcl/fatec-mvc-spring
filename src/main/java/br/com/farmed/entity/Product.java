package br.com.farmed.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(nullable = false)
    private String nameProduct;

    @Column(unique = true)
    private String slugProduct;

    @Column(unique = true)
    private String skuProduct;

    @ManyToOne
    @JoinColumn(name = "idCategory_fk")
    private Category category;

    @Column(unique = true)
    private String tussCodeProduct;

    @Column(precision = 16, scale = 4)
    private BigDecimal priceProduct;

    @Column(precision = 16, scale = 4)
    private BigDecimal costProduct;
}
