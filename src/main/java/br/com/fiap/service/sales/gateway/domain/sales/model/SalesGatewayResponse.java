package br.com.fiap.service.sales.gateway.domain.sales.model;

import br.com.fiap.service.sales.core.domain.shared.enums.PaymentStatus;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SalesGatewayResponse {

  private UUID id;
  private UUID idVehicle;
  private String document;
  private LocalDate date;
  private PaymentStatus paymentStatus;
  private UUID paymentCode;

  public static SalesGatewayResponse mapper(Object object) {
    var result = SalesGatewayResponse.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}
