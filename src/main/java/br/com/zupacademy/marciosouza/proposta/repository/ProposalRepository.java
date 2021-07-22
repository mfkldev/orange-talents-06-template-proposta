package br.com.zupacademy.marciosouza.proposta.repository;

import br.com.zupacademy.marciosouza.proposta.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    @Query("SELECT q FROM Proposal q WHERE q.status = 'ELEGIVEL' AND q.idCard = null")
    List<Proposal> eligibleSearchWithoutCard();
}
