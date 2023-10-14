package com.poly.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Shipping")
public class Shipping {
	@Id
    @Column(name = "ShippingType", columnDefinition = "nvarchar(max)" )
    private String ShippingType;

    @Column(name = "Time")
    private Float Time;

    @Column(name = "Price")
    private Double Price;
}
