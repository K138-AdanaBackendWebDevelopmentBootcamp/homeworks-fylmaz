package com.hw.repositories;

import com.hw.models.PermanentResearcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermanentResearcherRepository extends JpaRepository<PermanentResearcher, Integer> {

}

