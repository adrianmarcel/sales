package br.com.fiap.service.sales.core.usecase.webhook.model;

import br.com.fiap.service.sales.core.domain.shared.enums.PaymentStatus;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class WebhookRequest {

  private UUID idSale;
  private UUID paymentCode;
  private PaymentStatus paymentStatus;
}
