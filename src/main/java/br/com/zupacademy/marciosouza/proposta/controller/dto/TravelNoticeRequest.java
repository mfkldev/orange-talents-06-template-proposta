package br.com.zupacademy.marciosouza.proposta.controller.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TravelNoticeRequest {

    @NotEmpty
    private String destiny;
    @Future @NotNull
    private LocalDate tripEndDate;

    public TravelNoticeRequest(String destiny, LocalDate tripEndDate) {
        this.destiny = destiny;
        this.tripEndDate = tripEndDate;
    }

    public String getDestiny() {
        return destiny;
    }

    public LocalDate getTripEndDate() {
        return tripEndDate;
    }
}
