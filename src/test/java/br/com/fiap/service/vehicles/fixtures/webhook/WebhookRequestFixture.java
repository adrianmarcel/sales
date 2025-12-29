package br.com.fiap.service.vehicles.fixtures.webhook;

import br.com.fiap.service.sales.core.domain.shared.enums.PaymentStatus;
import br.com.fiap.service.sales.core.usecase.webhook.model.WebhookRequest;
import java.util.UUID;

public class WebhookRequestFixture {

  public static WebhookRequest validRequest() {
    return WebhookRequest.builder()
        .idSale(UUID.fromString("965c2a4c-a981-4e2e-8380-c1faef737e69"))
        .paymentCode(UUID.fromString("d04ae08e-f168-4db4-a53d-0f7fb9200d96"))
        .paymentStatus(PaymentStatus.DONE)
        .build();
  }

  public static WebhookRequest validCancelledRequest() {
    return WebhookRequest.builder()
        .idSale(UUID.fromString("965c2a4c-a981-4e2e-8380-c1faef737e69"))
        .paymentCode(UUID.fromString("d04ae08e-f168-4db4-a53d-0f7fb9200d96"))
        .paymentStatus(PaymentStatus.CANCELED)
        .build();
  }
}
