package br.com.fiap.service.vehicles.fixtures.sales;

import br.com.fiap.service.sales.core.usecase.sales.model.SalesRequest;
import java.util.UUID;

public class SalesRequestFixture {

  public static SalesRequest validRequest() {
    return SalesRequest.builder()
        .idVehicle(UUID.fromString("95cf276e-3d6b-4bf1-8b46-b06cb21ae5f6"))
        .document("52929449985")
        .build();
  }
}
