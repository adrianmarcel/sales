package br.com.fiap.service.vehicles.fixtures.vehicle;

import br.com.fiap.service.sales.core.domain.shared.enums.VehicleStatus;
import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleFilterRequest;
import java.util.UUID;

public class VehicleFilterRequestFixture {

  public static VehicleFilterRequest validRequest() {
    return VehicleFilterRequest.builder()
        .id(UUID.fromString("8f2dda39-ed84-482d-b71a-c5ac297486c1"))
        .status(VehicleStatus.AVAILABLE)
        .build();
  }

  public static VehicleFilterRequest validAvailableRequest() {
    return VehicleFilterRequest.builder().status(VehicleStatus.AVAILABLE).build();
  }

  public static VehicleFilterRequest validSoldRequest() {
    return VehicleFilterRequest.builder().status(VehicleStatus.SOLD).build();
  }

  public static VehicleFilterRequest validEmptyRequest() {
    return VehicleFilterRequest.builder().build();
  }
}
