package br.com.fiap.service.vehicles.fixtures.vehicle;

import br.com.fiap.service.sales.core.domain.shared.enums.VehicleStatus;
import br.com.fiap.service.sales.gateway.domain.vehicle.model.VehicleGatewayRequest;
import java.math.BigDecimal;
import java.util.UUID;

public class VehicleGatewayRequestFixture {

  public static VehicleGatewayRequest validRequest() {
    return VehicleGatewayRequest.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .brand("Mercedes Benz")
        .model("C300")
        .year(2023)
        .color("White")
        .status(VehicleStatus.AVAILABLE)
        .price(new BigDecimal("300000"))
        .build();
  }
}
