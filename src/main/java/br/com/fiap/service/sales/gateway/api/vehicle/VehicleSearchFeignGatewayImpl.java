package br.com.fiap.service.sales.gateway.api.vehicle;

import br.com.fiap.service.sales.core.domain.shared.enums.VehicleStatus;
import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleFilterRequest;
import br.com.fiap.service.sales.gateway.api.vehicle.client.VehicleClient;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleSearchGateway;
import br.com.fiap.service.sales.gateway.domain.vehicle.model.VehicleGatewayResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleSearchFeignGatewayImpl implements VehicleSearchGateway {

  private final VehicleClient client;

  @Override
  public Page<VehicleGatewayResponse> execute(VehicleFilterRequest filterRequest) {
    if (Objects.nonNull(filterRequest.getId())) {
      return client.getVehicleById(filterRequest.getId()).map(VehicleGatewayResponse::mapper);
    }

    if (VehicleStatus.AVAILABLE.equals(filterRequest.getStatus())) {
      return client.getAvailableVehicles().map(VehicleGatewayResponse::mapper);
    }

    if (VehicleStatus.SOLD.equals(filterRequest.getStatus())) {
      return client.getSoldVehicles().map(VehicleGatewayResponse::mapper);
    }

    return Page.empty();
  }
}
