package br.com.fiap.service.sales.gateway.api.vehicle.model;

import br.com.fiap.service.sales.core.domain.shared.enums.VehicleStatus;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class VehicleClientRequest {

  private String brand;
  private String model;
  private Integer year;
  private String color;
  private VehicleStatus status;
  private BigDecimal price;

  public static VehicleClientRequest mapper(Object object) {
    var result = VehicleClientRequest.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}
