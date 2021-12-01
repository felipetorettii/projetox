package com.esucri.projetox.domain.ticket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CheckinModel {

    private Long eventId;
    private String userEmail;
}
