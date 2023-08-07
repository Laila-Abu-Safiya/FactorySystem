package com.example.spring_boot.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.util.Set;

@Entity
@Table(name = "machine")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name should not be Empty")
    private String name;
    @NotEmpty(message = "Location should not be Empty")
    private String location;

    @ManyToOne
    @JoinColumn(name = "iduser", nullable = false)
    private User users;

   /* @OneToMany(mappedBy = "machines")
    private Set<Job> tasks;*/
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
           name = "machinetasks",
           joinColumns = @JoinColumn(name = "machineid",referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "taskid",referencedColumnName = "taskid"))
   Set<Job> tasks;
    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Machine() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
