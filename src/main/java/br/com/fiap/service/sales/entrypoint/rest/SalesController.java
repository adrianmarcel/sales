package br.com.fiap.service.sales.entrypoint.rest;

import static org.springframework.http.ResponseEntity.created;

import br.com.fiap.service.sales.core.usecase.sales.SalesCreateUseCase;
import br.com.fiap.service.sales.core.usecase.sales.model.SalesRequest;
import br.com.fiap.service.sales.core.usecase.sales.model.SalesResponse;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(SalesController.BASE_URI)
public class SalesController {

  public static final String BASE_URI = "/fiap/v1/sales";

  private final SalesCreateUseCase createUseCase;

  @PostMapping
  public ResponseEntity<SalesResponse> create(@Valid @RequestBody SalesRequest request) {
    var response = createUseCase.execute(request);

    var uri = URI.create(BASE_URI.concat("/").concat(response.getId().toString()));

    return created(uri).body(response);
  }
}
