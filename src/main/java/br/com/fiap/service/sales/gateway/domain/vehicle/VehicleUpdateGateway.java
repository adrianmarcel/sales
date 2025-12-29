package br.com.fiap.service.sales.gateway.domain.vehicle;

import br.com.fiap.service.sales.gateway.domain.vehicle.model.VehicleGatewayRequest;
import br.com.fiap.service.sales.gateway.domain.vehicle.model.VehicleGatewayResponse;

public interface VehicleUpdateGateway {

  VehicleGatewayResponse execute(VehicleGatewayRequest request);
}
