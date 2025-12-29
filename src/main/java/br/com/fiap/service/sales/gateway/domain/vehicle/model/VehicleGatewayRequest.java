package br.com.fiap.service.sales.gateway.domain.vehicle.model;

import br.com.fiap.service.sales.core.domain.shared.enums.VehicleStatus;
import java.math.BigDecimal;
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
public class VehicleGatewayRequest {

  private UUID id;
  private String brand;
  private String model;
  private Integer year;
  private String color;
  private VehicleStatus status;
  private BigDecimal price;

  public static VehicleGatewayRequest mapper(Object object) {
    var result = VehicleGatewayRequest.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}
