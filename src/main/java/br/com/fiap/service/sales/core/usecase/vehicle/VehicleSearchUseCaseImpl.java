package br.com.fiap.service.sales.core.usecase.vehicle;

import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleFilterRequest;
import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleResponse;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleSearchGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleSearchUseCaseImpl implements VehicleSearchUseCase {

  private final VehicleSearchGateway searchGateway;

  @Override
  public Page<VehicleResponse> execute(VehicleFilterRequest filterRequest) {
    return searchGateway.execute(filterRequest).map(VehicleResponse::mapper);
  }
}
