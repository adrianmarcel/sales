package br.com.fiap.service.sales.core.usecase.vehicle.model;

import br.com.fiap.service.sales.core.domain.shared.enums.VehicleStatus;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VehicleFilterRequest {

  private UUID id;
  private VehicleStatus status;
}
