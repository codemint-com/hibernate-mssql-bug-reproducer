package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class A {
    @ManyToOne(optional = true)
    B b;
}
