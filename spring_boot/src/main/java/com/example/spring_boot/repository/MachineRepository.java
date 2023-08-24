package com.example.spring_boot.repository;

import com.example.spring_boot.Entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {

    @Query("SELECT machine.id FROM Machine machine where machine.user = ?1")
    int [] findMachinesByUserId(int userid);

    @Query("SELECT machine FROM Machine machine where machine.user = ?1")
    Machine [] findUserMachines(int userid);
}
