package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="Status")
public class Status implements Serializable {
    @Id
    private Integer id;

    @Column(name = "name",columnDefinition = "nvarchar(100)")
    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "status")
    private OrderStatus orderStatus;

}
