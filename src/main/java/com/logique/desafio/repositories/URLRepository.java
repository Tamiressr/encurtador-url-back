package com.logique.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logique.desafio.domain.Url;

@Repository
public interface URLRepository extends JpaRepository<Url, Integer> {

}
