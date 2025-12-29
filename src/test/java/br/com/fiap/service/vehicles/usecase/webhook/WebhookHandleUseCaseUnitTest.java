package br.com.fiap.service.vehicles.usecase.webhook;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.service.sales.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.sales.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.sales.core.usecase.webhook.WebhookHandleUseCase;
import br.com.fiap.service.sales.core.usecase.webhook.WebhookHandleUseCaseImpl;
import br.com.fiap.service.sales.gateway.domain.sales.SalesUpdateGateway;
import br.com.fiap.service.sales.gateway.domain.sales.model.SalesGatewayRequest;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleUpdateGateway;
import br.com.fiap.service.vehicles.fixtures.sales.SalesGatewayResponseFixture;
import br.com.fiap.service.vehicles.fixtures.webhook.WebhookRequestFixture;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class WebhookHandleUseCaseUnitTest {

  private WebhookHandleUseCase useCase;

  @Mock private SalesUpdateGateway salesUpdateGateway;

  @Mock private VehicleUpdateGateway vehicleUpdateGateway;

  @BeforeEach
  public void setUp() {
    useCase = new WebhookHandleUseCaseImpl(salesUpdateGateway, vehicleUpdateGateway);
  }

  @Test
  public void testWebhookHandleWithErrorWhenVehicleOrSaleNotFound() {
    when(salesUpdateGateway.execute(any(SalesGatewayRequest.class))).thenReturn(Optional.empty());

    var be =
        assertThrows(
            BusinessException.class, () -> useCase.execute(WebhookRequestFixture.validRequest()));

    assertEquals(ExceptionCode.VEHICLE_OR_SALE_NOT_FOUND.getMessage(), be.getMessage());
    assertEquals(ExceptionCode.VEHICLE_OR_SALE_NOT_FOUND.getCode().toString(), be.getCode());
  }

  @Test
  public void testWebhookHandleWithSuccessWhenPaymentStatusIsCancelled() {
    when(salesUpdateGateway.execute(any(SalesGatewayRequest.class)))
        .thenReturn(Optional.of(SalesGatewayResponseFixture.validResponse()));

    assertDoesNotThrow(() -> useCase.execute(WebhookRequestFixture.validCancelledRequest()));
  }

  @Test
  public void testWebhookHandleWithSuccessWhenPaymentStatusIsDone() {
    when(salesUpdateGateway.execute(any(SalesGatewayRequest.class)))
        .thenReturn(Optional.of(SalesGatewayResponseFixture.validResponse()));

    assertDoesNotThrow(() -> useCase.execute(WebhookRequestFixture.validRequest()));
  }
}
