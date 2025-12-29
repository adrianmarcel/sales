package br.com.fiap.service.vehicles.usecase.sales;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.service.sales.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.sales.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.sales.core.usecase.sales.SalesCreateUseCase;
import br.com.fiap.service.sales.core.usecase.sales.SalesCreateUseCaseImpl;
import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleFilterRequest;
import br.com.fiap.service.sales.gateway.domain.sales.SalesCreateGateway;
import br.com.fiap.service.sales.gateway.domain.sales.model.SalesGatewayRequest;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleSearchGateway;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleUpdateGateway;
import br.com.fiap.service.vehicles.fixtures.sales.SalesGatewayResponseFixture;
import br.com.fiap.service.vehicles.fixtures.sales.SalesRequestFixture;
import br.com.fiap.service.vehicles.fixtures.vehicle.VehicleGatewayResponseFixture;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class SalesCreateUseCaseUnitTest {

  private SalesCreateUseCase useCase;

  @Mock private SalesCreateGateway createGateway;

  @Mock private VehicleSearchGateway vehicleSearchGateway;

  @Mock private VehicleUpdateGateway vehicleUpdateGateway;

  @BeforeEach
  public void setUp() {
    useCase = new SalesCreateUseCaseImpl(createGateway, vehicleSearchGateway, vehicleUpdateGateway);
  }

  @Test
  public void testSalesCreateWithErrorWhenVehicleNotFound() {
    when(vehicleSearchGateway.execute(any(VehicleFilterRequest.class)))
        .thenReturn(new PageImpl<>(List.of()));

    var be =
        assertThrows(
            BusinessException.class, () -> useCase.execute(SalesRequestFixture.validRequest()));

    assertEquals(ExceptionCode.VEHICLE_NOT_FOUND.getMessage(), be.getMessage());
    assertEquals(ExceptionCode.VEHICLE_NOT_FOUND.getCode().toString(), be.getCode());
  }

  @Test
  public void testSalesCreateWithSuccess() {
    when(vehicleSearchGateway.execute(any(VehicleFilterRequest.class)))
        .thenReturn(new PageImpl<>(List.of(VehicleGatewayResponseFixture.validResponse())));

    when(createGateway.execute(any(SalesGatewayRequest.class)))
        .thenReturn(SalesGatewayResponseFixture.validResponse());

    assertDoesNotThrow(() -> useCase.execute(SalesRequestFixture.validRequest()));
  }
}
