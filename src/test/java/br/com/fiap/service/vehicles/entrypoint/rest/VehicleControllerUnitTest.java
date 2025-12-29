package br.com.fiap.service.vehicles.entrypoint.rest;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.service.sales.Application;
import br.com.fiap.service.sales.core.domain.shared.exception.SalesExceptionHandler;
import br.com.fiap.service.sales.core.usecase.vehicle.VehicleSearchUseCase;
import br.com.fiap.service.sales.core.usecase.vehicle.model.VehicleFilterRequest;
import br.com.fiap.service.sales.entrypoint.rest.VehicleController;
import br.com.fiap.service.vehicles.fixtures.vehicle.VehicleResponseFixture;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@TestInstance(PER_CLASS)
@WebMvcTest(VehicleController.class)
@Import({SalesExceptionHandler.class})
@ContextConfiguration(classes = Application.class)
public class VehicleControllerUnitTest {

  @Autowired private WebApplicationContext context;

  @Autowired @MockBean private VehicleSearchUseCase searchUseCase;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testSearchSoldVehiclesWithErrorWhenInvalidPath() throws Exception {
    mockMvc
        .perform(
            get("/fiap/v1/vehicles/soldddd")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testSearchAvailableVehiclesWithErrorWhenInvalidPath() throws Exception {
    mockMvc
        .perform(
            get("/fiap/v1/vehicles/availableeee")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testSearchSoldVehiclesWithSuccess() throws Exception {
    when(searchUseCase.execute(any(VehicleFilterRequest.class)))
        .thenReturn(new PageImpl<>(List.of(VehicleResponseFixture.validSoldResponse())));

    mockMvc
        .perform(
            get("/fiap/v1/vehicles/sold")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").isNotEmpty())
        .andExpect(jsonPath("$.content[0].id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.content[0].brand").value("Mercedes Benz"))
        .andExpect(jsonPath("$.content[0].model").value("C300"))
        .andExpect(jsonPath("$.content[0].year").value(2023))
        .andExpect(jsonPath("$.content[0].color").value("White"))
        .andExpect(jsonPath("$.content[0].status").value("SOLD"))
        .andExpect(jsonPath("$.content[0].price").value(300000));
  }

  @Test
  public void testSearchAvailableVehiclesWithSuccess() throws Exception {
    when(searchUseCase.execute(any(VehicleFilterRequest.class)))
        .thenReturn(new PageImpl<>(List.of(VehicleResponseFixture.validAvailableResponse())));

    mockMvc
        .perform(
            get("/fiap/v1/vehicles/available")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").isNotEmpty())
        .andExpect(jsonPath("$.content[0].id").value("67fa861b-78c0-4d90-8b09-5a9437ec7aed"))
        .andExpect(jsonPath("$.content[0].brand").value("BMW"))
        .andExpect(jsonPath("$.content[0].model").value("320i"))
        .andExpect(jsonPath("$.content[0].year").value(2026))
        .andExpect(jsonPath("$.content[0].color").value("Black"))
        .andExpect(jsonPath("$.content[0].status").value("AVAILABLE"))
        .andExpect(jsonPath("$.content[0].price").value(200000));
  }
}
