package br.com.fiap.service.sales.core.usecase.sales.model;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SalesRequest {

  private UUID idVehicle;
  private String document;
}
