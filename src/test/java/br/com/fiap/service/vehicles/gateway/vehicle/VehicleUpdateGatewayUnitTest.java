package br.com.fiap.service.vehicles.gateway.vehicle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.service.sales.gateway.api.vehicle.VehicleUpdateFeignGatewayImpl;
import br.com.fiap.service.sales.gateway.api.vehicle.client.VehicleClient;
import br.com.fiap.service.sales.gateway.api.vehicle.model.VehicleClientRequest;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleUpdateGateway;
import br.com.fiap.service.vehicles.fixtures.vehicle.VehicleClientResponseFixture;
import br.com.fiap.service.vehicles.fixtures.vehicle.VehicleGatewayRequestFixture;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class VehicleUpdateGatewayUnitTest {

  private VehicleUpdateGateway gateway;

  @Mock private VehicleClient client;

  @BeforeEach
  public void setUp() {
    gateway = new VehicleUpdateFeignGatewayImpl(client);
  }

  @Test
  public void testVehicleUpdateWithSuccess() {
    when(client.updateVehicle(any(UUID.class), any(VehicleClientRequest.class)))
        .thenReturn(VehicleClientResponseFixture.validResponse());

    assertDoesNotThrow(
        () -> {
          var result = gateway.execute(VehicleGatewayRequestFixture.validRequest());

          assertNotNull(result);
        });
  }
}
