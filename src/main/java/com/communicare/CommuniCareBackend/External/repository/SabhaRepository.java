package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.Sabha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SabhaRepository extends JpaRepository<Sabha, Integer> {
    //Get is sabhaID for user register
    Optional<Sabha> findById(int id);
}
