package com.sii.sii_recruitment_task.Model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "Prelections")
public class Prelection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "start_hour")
    private Time startHour;

    @Column(name = "name")
    private String name;

    @Column(name = "atendees")
    @ManyToMany
    @JoinTable(
            name = "Reservations",
            joinColumns = @JoinColumn(name = "prelectionId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Set<User> users;


}
