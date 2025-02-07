package br.com.zupacademy.marciosouza.proposta.repository;

import br.com.zupacademy.marciosouza.proposta.model.CardlockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardlockRepository extends JpaRepository<CardlockModel, Long> {
}
