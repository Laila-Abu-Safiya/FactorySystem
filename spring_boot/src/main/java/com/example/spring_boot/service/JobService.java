package com.example.spring_boot.service;

import com.example.spring_boot.Entity.Job;
import com.example.spring_boot.Entity.JobMachineRequest;
import com.example.spring_boot.Entity.Machine;
import com.example.spring_boot.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job addNewJob(Job task) {
        return jobRepository.save(task);
    }

    public void deleteTask(int taskId) {

        jobRepository.deleteById(taskId);
    }

    public boolean CheckIfTaskExists(int taskId) {
        boolean exists = jobRepository.existsById(taskId);
        return exists;
    }

    public Optional<Job> getTaskById(int taskId) {
        return jobRepository.findById(taskId);

    }

    public boolean checkIfUserIsOwner(int userId, int taskId) throws Exception {
        int[] exists = jobRepository.CheckJobOwner(userId);
        boolean flag = false;
        for (int elem: exists){
            if(elem == taskId)
                flag = true;
        }
        if (exists.length != 0 && flag == true) {
            return true;
        } else {
            return false;
        }
    }

}

