package br.com.fiap.service.sales.gateway.api.vehicle.client;

import br.com.fiap.service.sales.gateway.api.vehicle.model.VehicleClientRequest;
import br.com.fiap.service.sales.gateway.api.vehicle.model.VehicleClientResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "vehicle", url = "${api.vehicles.url}")
public interface VehicleClient {

  @GetMapping("/fiap/v1/vehicles")
  Page<VehicleClientResponse> getVehicleById(@RequestParam("id") UUID id);

  @GetMapping("/fiap/v1/vehicles?status=AVAILABLE&sort=price,asc")
  Page<VehicleClientResponse> getAvailableVehicles();

  @GetMapping("/fiap/v1/vehicles?status=SOLD&sort=price,asc")
  Page<VehicleClientResponse> getSoldVehicles();

  @PutMapping("/fiap/v1/vehicles/{id}")
  VehicleClientResponse updateVehicle(
      @PathVariable("id") UUID id, @RequestBody VehicleClientRequest request);
}
