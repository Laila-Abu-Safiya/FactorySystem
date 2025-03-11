package com.example.spring_boot.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Job {
    @Id
    private int taskid;
    private int machineid;
    private int proirty;
    private String type;
    private String description;

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public int getMachineid() {
        return machineid;
    }

    public void setMachineid(int machineid) {
        this.machineid = machineid;
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


    public Job() {
        super();
    }
}
