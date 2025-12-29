package br.com.fiap.service.vehicles.fixtures.vehicle;

import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleResponse;
import java.math.BigDecimal;
import java.util.UUID;

public class VehicleResponseFixture {

  public static VehicleResponse validAvailableResponse() {
    return VehicleResponse.builder()
        .id(UUID.fromString("67fa861b-78c0-4d90-8b09-5a9437ec7aed"))
        .brand("BMW")
        .model("320i")
        .year(2026)
        .color("Black")
        .status("AVAILABLE")
        .price(new BigDecimal("200000"))
        .build();
  }

  public static VehicleResponse validSoldResponse() {
    return VehicleResponse.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .brand("Mercedes Benz")
        .model("C300")
        .year(2023)
        .color("White")
        .status("SOLD")
        .price(new BigDecimal("300000"))
        .build();
  }
}
