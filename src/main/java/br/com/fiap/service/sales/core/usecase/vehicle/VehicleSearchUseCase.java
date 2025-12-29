package br.com.fiap.service.sales.core.usecase.vehicle;

import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleFilterRequest;
import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleResponse;
import org.springframework.data.domain.Page;

public interface VehicleSearchUseCase {

  Page<VehicleResponse> execute(VehicleFilterRequest filterRequest);
}
