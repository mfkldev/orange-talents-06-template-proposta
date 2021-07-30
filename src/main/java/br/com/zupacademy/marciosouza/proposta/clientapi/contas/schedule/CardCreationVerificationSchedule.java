package br.com.zupacademy.marciosouza.proposta.clientapi.contas.schedule;

import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.CardCreationVerificationResponse;
import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.ElegibleProposalRequest;
import br.com.zupacademy.marciosouza.proposta.clientapi.contas.feignclient.AccountApi;
import br.com.zupacademy.marciosouza.proposta.model.ProposalModel;
import br.com.zupacademy.marciosouza.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CardCreationVerificationSchedule {

    @Autowired
    private AccountApi accountApi;

    @Autowired
    private ProposalRepository proposalRepository;

    @Scheduled(fixedDelay = 10000)
    protected void run() {
        List<ProposalModel> eligibleProposalModels = proposalRepository.eligibleSearchWithoutCard();

        eligibleProposalModels.forEach(
                oneEligibleProposal -> {
                    ElegibleProposalRequest requests = new ElegibleProposalRequest(oneEligibleProposal);

                    ResponseEntity<CardCreationVerificationResponse> response = accountApi.cardCreationVerification(requests.getId());

                    if (response.getBody().getId() != null && response.getStatusCode().is2xxSuccessful()) {
                        oneEligibleProposal.setIdCard(response.getBody().getId());
                        proposalRepository.save(oneEligibleProposal);
                    }
                }
        );
    }
}
