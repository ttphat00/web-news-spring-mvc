package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long> {

}
