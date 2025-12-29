package br.com.fiap.service.vehicles.entrypoint.rest;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.service.sales.Application;
import br.com.fiap.service.sales.core.domain.shared.exception.SalesExceptionHandler;
import br.com.fiap.service.sales.core.usecase.sales.SalesCreateUseCase;
import br.com.fiap.service.sales.core.usecase.sales.model.SalesRequest;
import br.com.fiap.service.sales.entrypoint.rest.SalesController;
import br.com.fiap.service.vehicles.fixtures.sales.SalesRequestFixture;
import br.com.fiap.service.vehicles.fixtures.sales.SalesResponseFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@TestInstance(PER_CLASS)
@WebMvcTest(SalesController.class)
@Import({SalesExceptionHandler.class})
@ContextConfiguration(classes = Application.class)
public class SalesControllerUnitTest {

  @Autowired private WebApplicationContext context;

  @Autowired @MockBean private SalesCreateUseCase createUseCase;

  private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testCreateSaleWithErrorWhenInvalidPath() throws Exception {
    mockMvc
        .perform(
            post("/fiap/v1/salesssss")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(SalesRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testCreateSaleWithSuccess() throws Exception {
    when(createUseCase.execute(any(SalesRequest.class)))
        .thenReturn(SalesResponseFixture.validResponse());

    mockMvc
        .perform(
            post("/fiap/v1/sales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(SalesRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("0a496508-0d2d-4e0d-8e9f-54791b32dc3f"))
        .andExpect(jsonPath("$.idVehicle").value("95cf276e-3d6b-4bf1-8b46-b06cb21ae5f6"))
        .andExpect(jsonPath("$.document").value("52929449985"))
        .andExpect(jsonPath("$.date").value("2025-12-29"))
        .andExpect(jsonPath("$.paymentStatus").value("WAITING"))
        .andExpect(jsonPath("$.paymentCode").value("4c7d76b4-2174-4627-85d0-09faaa3d8eba"));
  }
}
