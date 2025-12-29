package br.com.fiap.service.sales.core.usecase.sales;

import br.com.fiap.service.sales.core.domain.shared.enums.PaymentStatus;
import br.com.fiap.service.sales.core.domain.shared.enums.VehicleStatus;
import br.com.fiap.service.sales.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.sales.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.sales.core.usecase.sales.model.SalesRequest;
import br.com.fiap.service.sales.core.usecase.sales.model.SalesResponse;
import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleFilterRequest;
import br.com.fiap.service.sales.gateway.domain.sales.SalesCreateGateway;
import br.com.fiap.service.sales.gateway.domain.sales.model.SalesGatewayRequest;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleSearchGateway;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleUpdateGateway;
import br.com.fiap.service.sales.gateway.domain.vehicle.model.VehicleGatewayRequest;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesCreateUseCaseImpl implements SalesCreateUseCase {

  private final SalesCreateGateway createGateway;
  private final VehicleSearchGateway vehicleSearchGateway;
  private final VehicleUpdateGateway vehicleUpdateGateway;

  @Override
  public SalesResponse execute(SalesRequest request) {
    var filterRequest = VehicleFilterRequest.builder().id(request.getIdVehicle()).build();

    return vehicleSearchGateway.execute(filterRequest).stream()
        .findFirst()
        .filter(v -> VehicleStatus.AVAILABLE.toString().equalsIgnoreCase(v.getStatus()))
        .map(
            v -> {
              var gatewayRequest = SalesGatewayRequest.mapper(request);
              gatewayRequest.setDate(LocalDate.now());
              gatewayRequest.setPaymentCode(UUID.randomUUID());
              gatewayRequest.setPaymentStatus(PaymentStatus.WAITING);

              var gatewayResponse = createGateway.execute(gatewayRequest);

              var vehicleGatewayRequest = VehicleGatewayRequest.mapper(v);
              vehicleGatewayRequest.setStatus(VehicleStatus.RESERVED);

              vehicleUpdateGateway.execute(vehicleGatewayRequest);

              return SalesResponse.mapper(gatewayResponse);
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.VEHICLE_NOT_FOUND));
  }
}
