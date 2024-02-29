package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class B {
    @ManyToOne(optional = false)
    C1 c;
}

