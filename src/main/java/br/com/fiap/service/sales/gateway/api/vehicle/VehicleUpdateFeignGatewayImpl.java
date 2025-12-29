package br.com.fiap.service.sales.gateway.api.vehicle;

import br.com.fiap.service.sales.gateway.api.vehicle.client.VehicleClient;
import br.com.fiap.service.sales.gateway.api.vehicle.model.VehicleClientRequest;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleUpdateGateway;
import br.com.fiap.service.sales.gateway.domain.vehicle.model.VehicleGatewayRequest;
import br.com.fiap.service.sales.gateway.domain.vehicle.model.VehicleGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleUpdateFeignGatewayImpl implements VehicleUpdateGateway {

  private final VehicleClient client;

  @Override
  public VehicleGatewayResponse execute(VehicleGatewayRequest request) {
    var clientRequest = VehicleClientRequest.mapper(request);

    var clientResponse = client.updateVehicle(request.getId(), clientRequest);

    return VehicleGatewayResponse.mapper(clientResponse);
  }
}
