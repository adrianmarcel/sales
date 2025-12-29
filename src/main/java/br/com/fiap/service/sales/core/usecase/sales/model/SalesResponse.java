package br.com.fiap.service.sales.core.usecase.sales.model;

import br.com.fiap.service.sales.core.domain.shared.enums.PaymentStatus;
import java.time.LocalDate;
import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SalesResponse {

  private UUID id;
  private UUID idVehicle;
  private String document;
  private LocalDate date;
  private PaymentStatus paymentStatus;
  private UUID paymentCode;

  @SneakyThrows
  public static SalesResponse mapper(Object object) {
    var result = SalesResponse.builder().build();
    BeanUtils.copyProperties(object, result);

    return result;
  }
}
