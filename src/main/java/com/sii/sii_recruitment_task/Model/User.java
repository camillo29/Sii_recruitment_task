package com.sii.sii_recruitment_task.Model;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "prelections")
    @ManyToMany
    @JoinTable(
            name = "Reservations",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "prelectionId")
    )
    private List<Prelection> prelections;

    public User(){}

    public User(String login, String email) {
        this.login = login;
        this.email = email;
        this.prelections = new LinkedList<>();
    }

    public boolean isGivenHourReserved(Time hour){
        for(Prelection p: prelections){
            if(p.getStartHour().equals(hour))
                return true;
        }
        return false;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Prelection> getPrelections() {
        return prelections;
    }

    public void setPrelections(List<Prelection> prelections) {
        this.prelections = prelections;
    }
}
