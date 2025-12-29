package br.com.fiap.service.sales.core.usecase.sales;

import br.com.fiap.service.sales.core.usecase.sales.model.SalesRequest;
import br.com.fiap.service.sales.core.usecase.sales.model.SalesResponse;
import jakarta.validation.Valid;

public interface SalesCreateUseCase {

  SalesResponse execute(@Valid SalesRequest request);
}
