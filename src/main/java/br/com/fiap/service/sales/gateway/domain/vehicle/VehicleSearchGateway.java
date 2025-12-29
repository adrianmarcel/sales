package br.com.fiap.service.sales.gateway.domain.vehicle;

import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleFilterRequest;
import br.com.fiap.service.sales.gateway.domain.vehicle.model.VehicleGatewayResponse;
import org.springframework.data.domain.Page;

public interface VehicleSearchGateway {

  Page<VehicleGatewayResponse> execute(VehicleFilterRequest filterRequest);
}
