package com.dombi.krakn2.repository;


import com.dombi.krakn2.model.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepository extends JpaRepository<Wine, Long> {
}
