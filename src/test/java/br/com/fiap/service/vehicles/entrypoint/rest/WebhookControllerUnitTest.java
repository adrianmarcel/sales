package br.com.fiap.service.vehicles.entrypoint.rest;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.service.sales.Application;
import br.com.fiap.service.sales.core.domain.shared.exception.SalesExceptionHandler;
import br.com.fiap.service.sales.core.usecase.webhook.WebhookHandleUseCase;
import br.com.fiap.service.sales.core.usecase.webhook.model.WebhookRequest;
import br.com.fiap.service.sales.entrypoint.rest.WebhookController;
import br.com.fiap.service.vehicles.fixtures.webhook.WebhookRequestFixture;
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
@WebMvcTest(WebhookController.class)
@Import({SalesExceptionHandler.class})
@ContextConfiguration(classes = Application.class)
public class WebhookControllerUnitTest {

  @Autowired private WebApplicationContext context;

  @Autowired @MockBean private WebhookHandleUseCase useCase;

  private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testHandleWebhookWithErrorWhenInvalidPath() throws Exception {
    mockMvc
        .perform(
            post("/fiap/v1/webhoook")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testHandleWebhookWithSuccess() throws Exception {
    doNothing().when(useCase).execute(any(WebhookRequest.class));

    mockMvc
        .perform(
            post("/fiap/v1/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(WebhookRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isNoContent());
  }
}
