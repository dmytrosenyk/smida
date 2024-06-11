package org.senyk.smida.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.senyk.smida.controller.CompanyController;
import org.senyk.smida.dto.CompanyDto;
import org.senyk.smida.service.CompanyService;


@WebMvcTest(controllers = CompanyController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CompanyControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CompanyService companyService;

  @Autowired
  private ObjectMapper objectMapper;
  private CompanyDto companyDto;

  @BeforeEach
  public void init() {
    companyDto = CompanyDto.builder().name("Dmytro FOP").registrationNumber("1234qwerty")
        .address("Lviv").createdAt(LocalDateTime.now()).build();
  }

  @Test
  public void PokemonController_PokemonDetail_ReturnPokemonDto() throws Exception {

    long companyId = 1;
    when(companyService.getCompanyById(companyId)).thenReturn(companyDto);

    ResultActions response = mockMvc.perform(
        get("/company/1").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(companyDto)));

    response.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(companyDto.getName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.registrationNumber",
            CoreMatchers.is(companyDto.getRegistrationNumber())));
  }
}
