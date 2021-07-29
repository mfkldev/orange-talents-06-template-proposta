package br.com.zupacademy.marciosouza.proposta.repository;

import br.com.zupacademy.marciosouza.proposta.model.ProposalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProposalRepository extends JpaRepository<ProposalModel, Long> {

    @Query("SELECT q FROM ProposalModel q WHERE q.status = 'ELEGIVEL' AND q.idCard = null")
    List<ProposalModel> eligibleSearchWithoutCard();

    Optional<ProposalModel> findByIdCard(String idCard);
}
