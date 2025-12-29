package br.com.fiap.service.vehicles.usecase.vehicle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.service.sales.core.usecase.vehicle.VehicleSearchUseCase;
import br.com.fiap.service.sales.core.usecase.vehicle.VehicleSearchUseCaseImpl;
import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleFilterRequest;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleSearchGateway;
import br.com.fiap.service.vehicles.fixtures.vehicle.VehicleFilterRequestFixture;
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
public class VehicleSearchUseCaseUnitTest {

  private VehicleSearchUseCase useCase;

  @Mock private VehicleSearchGateway searchGateway;

  @BeforeEach
  public void setUp() {
    useCase = new VehicleSearchUseCaseImpl(searchGateway);
  }

  @Test
  public void testVehiclesSearchWithSuccess() {
    when(searchGateway.execute(any(VehicleFilterRequest.class)))
        .thenReturn(new PageImpl<>(List.of(VehicleGatewayResponseFixture.validResponse())));

    assertDoesNotThrow(() -> useCase.execute(VehicleFilterRequestFixture.validRequest()));
  }
}
