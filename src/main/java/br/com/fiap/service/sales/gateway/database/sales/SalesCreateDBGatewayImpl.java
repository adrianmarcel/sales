package br.com.fiap.service.sales.gateway.database.sales;

import br.com.fiap.service.sales.gateway.database.sales.model.SalesEntity;
import br.com.fiap.service.sales.gateway.database.sales.repository.SalesRepository;
import br.com.fiap.service.sales.gateway.domain.sales.SalesCreateGateway;
import br.com.fiap.service.sales.gateway.domain.sales.model.SalesGatewayRequest;
import br.com.fiap.service.sales.gateway.domain.sales.model.SalesGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesCreateDBGatewayImpl implements SalesCreateGateway {

  private final SalesRepository repository;

  @Override
  public SalesGatewayResponse execute(SalesGatewayRequest request) {
    return SalesGatewayResponse.mapper(repository.save(SalesEntity.mapper(request)));
  }
}
