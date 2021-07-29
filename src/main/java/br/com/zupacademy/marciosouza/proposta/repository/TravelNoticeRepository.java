package br.com.zupacademy.marciosouza.proposta.repository;

import br.com.zupacademy.marciosouza.proposta.model.TravelNoticeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelNoticeRepository extends JpaRepository<TravelNoticeModel, Long> {
}
