package br.com.fiap.service.sales.gateway.domain.sales;

import br.com.fiap.service.sales.gateway.domain.sales.model.SalesGatewayRequest;
import br.com.fiap.service.sales.gateway.domain.sales.model.SalesGatewayResponse;
import java.util.Optional;

public interface SalesUpdateGateway {

  Optional<SalesGatewayResponse> execute(SalesGatewayRequest request);
}
