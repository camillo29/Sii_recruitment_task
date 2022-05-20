package com.sii.sii_recruitment_task.Model;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
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
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getStartHour() {
        return startHour;
    }

    public void setStartHour(Time startHour) {
        this.startHour = startHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
