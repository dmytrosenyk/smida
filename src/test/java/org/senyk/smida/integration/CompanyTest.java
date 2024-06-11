package org.senyk.smida.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.senyk.smida.dto.CompanyDto;
import org.senyk.smida.entity.Company;
import org.senyk.smida.repository.CompanyRepository;
import org.senyk.smida.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CompanyTest {

  @Autowired
  private CompanyService companyService;

  @Autowired
  private CompanyRepository companyRepository;

  @BeforeEach
  void setUp() {
    companyRepository.deleteAll();
  }

  @Test
  void testAddAndGetCompany() {

    CompanyDto companyDto = new CompanyDto(null, "Dmytro FOP", "1234qwerty", "Lviv",
        LocalDateTime.now());

    companyService.addCompany(companyDto);
    List<CompanyDto> companies = companyService.getAllCompanies();

    assertThat(companies).hasSize(1);
    CompanyDto savedCompany = companies.get(0);
    assertThat(savedCompany.getName()).isEqualTo("Dmytro FOP");
    assertThat(savedCompany.getRegistrationNumber()).isEqualTo("1234qwerty");
    assertThat(savedCompany.getAddress()).isEqualTo("Lviv");
  }

  @Test
  void testGetCompanyById() {

    Company company = companyRepository.save(
        new Company(null, "Dmytro FOP", "1234qwerty", "Lviv", LocalDateTime.now()));

    CompanyDto foundCompany = companyService.getCompanyById(company.getId());

    assertThat(foundCompany.getName()).isEqualTo("Dmytro FOP");
    assertThat(foundCompany.getRegistrationNumber()).isEqualTo("1234qwerty");
    assertThat(foundCompany.getAddress()).isEqualTo("Lviv");
  }

  @Test
  void testDeleteCompany() {

    Company company = companyRepository.save(
        new Company(null, "Dmytro FOP", "1234qwerty", "Lviv", LocalDateTime.now()));

    companyService.deleteCompanyById(company.getId());

    assertThrows(EntityNotFoundException.class,
        () -> companyService.getCompanyById(company.getId()));
  }
}
