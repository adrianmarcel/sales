package br.com.fiap.service.sales.core.usecase.webhook;

import br.com.fiap.service.sales.core.usecase.webhook.model.WebhookRequest;
import jakarta.validation.Valid;

public interface WebhookHandleUseCase {

  void execute(@Valid WebhookRequest request);
}
