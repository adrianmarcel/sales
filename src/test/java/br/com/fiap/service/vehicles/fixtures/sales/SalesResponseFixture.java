package br.com.fiap.service.vehicles.fixtures.sales;

import br.com.fiap.service.sales.core.domain.shared.enums.PaymentStatus;
import br.com.fiap.service.sales.core.usecase.sales.model.SalesResponse;
import java.time.LocalDate;
import java.util.UUID;

public class SalesResponseFixture {

  public static SalesResponse validResponse() {
    return SalesResponse.builder()
        .id(UUID.fromString("0a496508-0d2d-4e0d-8e9f-54791b32dc3f"))
        .idVehicle(UUID.fromString("95cf276e-3d6b-4bf1-8b46-b06cb21ae5f6"))
        .document("52929449985")
        .date(LocalDate.of(2025, 12, 29))
        .paymentStatus(PaymentStatus.WAITING)
        .paymentCode(UUID.fromString("4c7d76b4-2174-4627-85d0-09faaa3d8eba"))
        .build();
  }
}
