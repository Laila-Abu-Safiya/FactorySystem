package com.example.spring_boot.service;

import com.example.spring_boot.Entity.Job;
import com.example.spring_boot.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

}
