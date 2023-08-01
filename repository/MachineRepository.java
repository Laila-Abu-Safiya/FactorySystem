package com.example.trainningspringproject.repository;

import com.example.trainningspringproject.entity.Job;
import com.example.trainningspringproject.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {

    @Query("SELECT machine.id FROM Machine machine where machine.user = ?1")
    int [] findMachinesByUserId(int userid);

    @Query("SELECT machine FROM Machine machine where machine.user = ?1")
    Machine [] findUserMachines(int userid);
}
