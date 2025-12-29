package br.com.fiap.service.sales.gateway.domain.vehicle.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
public class VehicleGatewayResponse {

  private UUID id;
  private String brand;
  private String model;
  private String color;
  private Integer year;
  private String status;
  private BigDecimal price;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public static VehicleGatewayResponse mapper(Object object) {
    var result = VehicleGatewayResponse.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}
