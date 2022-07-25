package com.robertogoz.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 70)
    private String name;

    @Column(unique = true, nullable = false, length = 255)
    private String email;

    @Column(nullable = false)
    private String password;
}
