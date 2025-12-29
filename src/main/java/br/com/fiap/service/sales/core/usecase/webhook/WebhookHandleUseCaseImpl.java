package br.com.fiap.service.sales.core.usecase.webhook;

import br.com.fiap.service.sales.core.domain.shared.enums.PaymentStatus;
import br.com.fiap.service.sales.core.domain.shared.enums.VehicleStatus;
import br.com.fiap.service.sales.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.sales.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.sales.core.usecase.webhook.model.WebhookRequest;
import br.com.fiap.service.sales.gateway.domain.sales.SalesUpdateGateway;
import br.com.fiap.service.sales.gateway.domain.sales.model.SalesGatewayRequest;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleUpdateGateway;
import br.com.fiap.service.sales.gateway.domain.vehicle.model.VehicleGatewayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebhookHandleUseCaseImpl implements WebhookHandleUseCase {

  private final SalesUpdateGateway salesUpdateGateway;
  private final VehicleUpdateGateway vehicleUpdateGateway;

  @Override
  public void execute(WebhookRequest request) {
    var salesGatewayRequest =
        SalesGatewayRequest.builder()
            .id(request.getIdSale())
            .paymentCode(request.getPaymentCode())
            .paymentStatus(request.getPaymentStatus())
            .build();

    salesUpdateGateway
        .execute(salesGatewayRequest)
        .ifPresentOrElse(
            s -> {
              var vehicleGatewayRequest =
                  VehicleGatewayRequest.builder().id(s.getIdVehicle()).build();

              if (PaymentStatus.CANCELED.equals(request.getPaymentStatus())) {
                vehicleGatewayRequest.setStatus(VehicleStatus.AVAILABLE);
              } else if (PaymentStatus.DONE.equals(request.getPaymentStatus())) {
                vehicleGatewayRequest.setStatus(VehicleStatus.SOLD);
              }

              vehicleUpdateGateway.execute(vehicleGatewayRequest);
            },
            () -> {
              throw new BusinessException(ExceptionCode.VEHICLE_OR_SALE_NOT_FOUND);
            });
  }
}
