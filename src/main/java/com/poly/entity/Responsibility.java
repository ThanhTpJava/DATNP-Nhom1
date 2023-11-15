package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="Responsibility")
public class Responsibility implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "orderid", columnDefinition = "varchar(8)")
    private Order orderResponsi;

    @ManyToOne
    @JoinColumn(name ="role_id")
    private Role role;
}
