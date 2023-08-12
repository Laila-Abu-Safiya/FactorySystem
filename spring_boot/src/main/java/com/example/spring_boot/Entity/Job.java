//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.spring_boot.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(
    name = "job"
)
public class Job {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int taskid;
    private @NotNull int proirty;
    private @NotEmpty String type;
    private @NotEmpty String description;
    private String status;
    private Date createddate;
    private int lastexecutiontime;
    private int numoftrails;
    private int nextexcutiontime;
    @ManyToMany(
        fetch = FetchType.LAZY,
        mappedBy = "tasks",
        cascade = {CascadeType.ALL}
    )
    @JsonBackReference
    List<Machine> machines = new ArrayList();

    public Date getCreateddate() {
        return this.createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public int getLastexcutiontime() {
        return this.lastexecutiontime;
    }

    public void setLastexcutiontime(int lastexcutiontime) {
        this.lastexecutiontime = lastexcutiontime;
    }

    public int getNumberoftrails() {
        return this.numoftrails;
    }

    public void setNumberoftrails(int numberoftrails) {
        this.numoftrails = numberoftrails;
    }

    public int getNextexcutiontime() {
        return this.nextexcutiontime;
    }

    public void setNextexcutiontime(int nextexcutiontime) {
        this.nextexcutiontime = nextexcutiontime;
    }

    public List<Machine> getMachines() {
        return this.machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTaskid() {
        return this.taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public int getProirty() {
        return this.proirty;
    }

    public void setProirty(int proirty) {
        this.proirty = proirty;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Job() {
    }
}
