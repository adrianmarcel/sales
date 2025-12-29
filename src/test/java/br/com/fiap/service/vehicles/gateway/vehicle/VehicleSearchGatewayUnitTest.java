package br.com.fiap.service.vehicles.gateway.vehicle;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.service.sales.gateway.api.vehicle.VehicleSearchFeignGatewayImpl;
import br.com.fiap.service.sales.gateway.api.vehicle.client.VehicleClient;
import br.com.fiap.service.sales.gateway.domain.vehicle.VehicleSearchGateway;
import br.com.fiap.service.vehicles.fixtures.vehicle.VehicleClientResponseFixture;
import br.com.fiap.service.vehicles.fixtures.vehicle.VehicleFilterRequestFixture;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class VehicleSearchGatewayUnitTest {

  private VehicleSearchGateway gateway;

  @Mock private VehicleClient client;

  @BeforeEach
  public void setUp() {
    gateway = new VehicleSearchFeignGatewayImpl(client);
  }

  @Test
  public void testVehicleSearchByIdWithSuccess() {
    when(client.getVehicleById(any(UUID.class)))
        .thenReturn(new PageImpl<>(List.of(VehicleClientResponseFixture.validResponse())));

    assertDoesNotThrow(
        () -> {
          var result = gateway.execute(VehicleFilterRequestFixture.validRequest());

          assertNotNull(result);
          assertNotNull(result.getContent());
          assertEquals(1, result.getSize());
        });
  }

  @Test
  public void testVehicleSearchAvailableWithSuccess() {
    when(client.getAvailableVehicles())
        .thenReturn(new PageImpl<>(List.of(VehicleClientResponseFixture.validResponse())));

    assertDoesNotThrow(
        () -> {
          var result = gateway.execute(VehicleFilterRequestFixture.validAvailableRequest());

          assertNotNull(result);
          assertNotNull(result.getContent());
          assertEquals(1, result.getSize());
        });
  }

  @Test
  public void testVehicleSearchSoldWithSuccess() {
    when(client.getSoldVehicles())
        .thenReturn(new PageImpl<>(List.of(VehicleClientResponseFixture.validResponse())));

    assertDoesNotThrow(
        () -> {
          var result = gateway.execute(VehicleFilterRequestFixture.validSoldRequest());

          assertNotNull(result);
          assertNotNull(result.getContent());
          assertEquals(1, result.getSize());
        });
  }

  @Test
  public void testVehicleSearchEmptyRequestWithSuccess() {
    assertDoesNotThrow(
        () -> {
          var result = gateway.execute(VehicleFilterRequestFixture.validEmptyRequest());

          assertNotNull(result);
          assertNotNull(result.getContent());
          assertEquals(0, result.getSize());
        });
  }
}
