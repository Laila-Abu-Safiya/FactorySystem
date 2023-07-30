package com.example.trainningspringproject.entity;

import com.example.trainningspringproject.Exeptions.EmptyException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;

@Entity

public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String location;
    @NonNull
    private int user;

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

    public void setName(String name) throws EmptyException {
        validateForEmpty(name);
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) throws EmptyException {
        validateForEmpty(location);
        this.location = location;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void validateForEmpty(String name) throws EmptyException {
        if (name.equals("")) {
            throw new EmptyException("Name should not be Empty");
        }
    }
}
