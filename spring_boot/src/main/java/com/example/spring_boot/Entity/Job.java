package com.example.spring_boot.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.crypto.Mac;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskid;

    @NotNull
    private int proirty;
    @NotEmpty
    private String type;
    @NotEmpty
    private String description;
    private String status;
    private Date createddate = new Date();
    private int  lastexcutiontime = 0;
    private int numberoftrails = 0;
    private int nextexcutiontime = 0;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmachine", nullable = false)
    @JsonBackReference
    private Machine machines;*/

    @ManyToMany(mappedBy = "tasks", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    Set<Machine> machines = new HashSet<>();
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public int getProirty() {
        return proirty;
    }

    public void setProirty(int proirty) {
        this.proirty = proirty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public int getLastexcutiontime() {
        return lastexcutiontime;
    }

    public void setLastexcutiontime(int lastexcutiontime) {
        this.lastexcutiontime = lastexcutiontime;
    }

    public int getNumberoftrails() {
        return numberoftrails;
    }

    public void setNumberoftrails(int numberoftrails) {
        this.numberoftrails = numberoftrails;
    }

    public int getNextexcutiontime() {
        return nextexcutiontime;
    }

    public void setNextexcutiontime(int nextexcutiontime) {
        this.nextexcutiontime = nextexcutiontime;
    }

    public Set<Machine> getMachines() {
        return machines;
    }

    public void setMachines(Set<Machine> machines) {
        this.machines = machines;
    }

    public Job() {
        super();
    }
}