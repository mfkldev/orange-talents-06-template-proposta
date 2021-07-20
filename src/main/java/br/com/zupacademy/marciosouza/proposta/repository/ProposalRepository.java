package br.com.zupacademy.marciosouza.proposta.repository;

import br.com.zupacademy.marciosouza.proposta.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
