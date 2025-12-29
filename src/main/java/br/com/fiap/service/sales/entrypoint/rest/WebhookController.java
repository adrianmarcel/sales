package br.com.fiap.service.sales.entrypoint.rest;

import br.com.fiap.service.sales.core.usecase.webhook.WebhookHandleUseCase;
import br.com.fiap.service.sales.core.usecase.webhook.model.WebhookRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(WebhookController.BASE_URI)
public class WebhookController {

  private final WebhookHandleUseCase useCase;

  public static final String BASE_URI = "/fiap/v1/webhook";

  @PostMapping
  public ResponseEntity<Void> handle(@Valid @RequestBody WebhookRequest request) {
    useCase.execute(request);
    return ResponseEntity.noContent().build();
  }
}
