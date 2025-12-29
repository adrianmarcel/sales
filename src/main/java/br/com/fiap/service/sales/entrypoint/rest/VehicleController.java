package br.com.fiap.service.sales.entrypoint.rest;

import static org.springframework.http.ResponseEntity.ok;

import br.com.fiap.service.sales.core.domain.shared.enums.VehicleStatus;
import br.com.fiap.service.sales.core.usecase.vehicle.VehicleSearchUseCase;
import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleFilterRequest;
import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(VehicleController.BASE_URI)
public class VehicleController {

  public static final String BASE_URI = "/fiap/v1/vehicles";

  private final VehicleSearchUseCase searchUseCase;

  @GetMapping("/sold")
  public ResponseEntity<Page<VehicleResponse>> soldVehicles() {
    var filterRequest = VehicleFilterRequest.builder().status(VehicleStatus.SOLD).build();

    return ok(searchUseCase.execute(filterRequest));
  }

  @GetMapping("/available")
  public ResponseEntity<Page<VehicleResponse>> availableVehicles() {
    var filterRequest = VehicleFilterRequest.builder().status(VehicleStatus.AVAILABLE).build();

    return ok(searchUseCase.execute(filterRequest));
  }
}
