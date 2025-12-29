package br.com.fiap.service.sales.gateway.api.vehicle.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class VehicleClientResponse {

  private UUID id;
  private String brand;
  private String model;
  private Integer year;
  private String color;
  private String status;
  private BigDecimal price;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
}
