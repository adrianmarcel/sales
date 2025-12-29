package br.com.fiap.service.sales.gateway.database.sales;

import br.com.fiap.service.sales.gateway.database.sales.repository.SalesRepository;
import br.com.fiap.service.sales.gateway.domain.sales.SalesUpdateGateway;
import br.com.fiap.service.sales.gateway.domain.sales.model.SalesGatewayRequest;
import br.com.fiap.service.sales.gateway.domain.sales.model.SalesGatewayResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesUpdateDBGatewayImpl implements SalesUpdateGateway {

  private final SalesRepository repository;

  @Override
  public Optional<SalesGatewayResponse> execute(SalesGatewayRequest request) {
    return repository
        .findByIdAndPaymentCode(request.getId(), request.getPaymentCode())
        .map(
            entity -> {
              entity.setPaymentStatus(request.getPaymentStatus());

              return SalesGatewayResponse.mapper(repository.save(entity));
            });
  }
}
