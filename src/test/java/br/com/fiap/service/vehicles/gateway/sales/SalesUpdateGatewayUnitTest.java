package br.com.fiap.service.vehicles.gateway.sales;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.service.sales.gateway.database.sales.SalesUpdateDBGatewayImpl;
import br.com.fiap.service.sales.gateway.database.sales.model.SalesEntity;
import br.com.fiap.service.sales.gateway.database.sales.repository.SalesRepository;
import br.com.fiap.service.sales.gateway.domain.sales.SalesUpdateGateway;
import br.com.fiap.service.vehicles.fixtures.sales.SalesEntityFixture;
import br.com.fiap.service.vehicles.fixtures.sales.SalesGatewayRequestFixture;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class SalesUpdateGatewayUnitTest {

  private SalesUpdateGateway gateway;

  @Mock private SalesRepository repository;

  @BeforeEach
  public void setUp() {
    gateway = new SalesUpdateDBGatewayImpl(repository);
  }

  @Test
  public void testSalesUpdateWithSuccess() {
    when(repository.findByIdAndPaymentCode(any(UUID.class), any(UUID.class)))
        .thenReturn(Optional.of(SalesEntityFixture.validEntity()));

    when(repository.save(any(SalesEntity.class)))
        .thenReturn(SalesEntityFixture.validUpdatedEntity());

    assertDoesNotThrow(
        () -> {
          var result = gateway.execute(SalesGatewayRequestFixture.validRequest());

          assertTrue(result.isPresent());
        });
  }
}
