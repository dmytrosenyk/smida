package org.senyk.smida.unit;


import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.senyk.smida.dto.CompanyDto;
import org.senyk.smida.entity.Company;
import org.senyk.smida.repository.CompanyRepository;
import org.senyk.smida.service.CompanyService;

import java.util.Optional;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTests {
  @Mock
  private CompanyRepository companyRepository;

  @InjectMocks
  private CompanyService companyService;

  @Test
  public void CompanyService_FindById_ReturnCompanyDto() {

    long companyId = 1;
    Company company = Company.builder()
        .id(companyId)
        .name("ФОП Дмитро")
        .registrationNumber("1234qwerty")
        .address("Lviv")
        .createdAt(LocalDateTime.now())
        .build();

    when(companyRepository.findById(companyId)).thenReturn(Optional.ofNullable(company));

    CompanyDto companyReturn = companyService.getCompanyById(companyId);

    Assertions.assertThat(companyReturn).isNotNull();
  }
}
