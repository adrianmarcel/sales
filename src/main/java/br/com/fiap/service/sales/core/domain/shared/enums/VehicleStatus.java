package br.com.fiap.service.sales.core.domain.shared.enums;

import java.util.Objects;

public enum VehicleStatus {
  AVAILABLE,
  RESERVED,
  SOLD;

  public static VehicleStatus compare(VehicleStatus a, VehicleStatus b) {
    if (Objects.nonNull(a) && b != a) {
      return a;
    }

    return b;
  }
}
