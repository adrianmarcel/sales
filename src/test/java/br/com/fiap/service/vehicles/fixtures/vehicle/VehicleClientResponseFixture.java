package br.com.fiap.service.vehicles.fixtures.vehicle;

import br.com.fiap.service.sales.gateway.api.vehicle.model.VehicleClientResponse;
import java.math.BigDecimal;
import java.util.UUID;

public class VehicleClientResponseFixture {

  public static VehicleClientResponse validResponse() {
    return VehicleClientResponse.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .brand("Mercedes Benz")
        .model("C300")
        .year(2023)
        .status("AVAILABLE")
        .color("White")
        .price(new BigDecimal("300000"))
        .build();
  }
}
