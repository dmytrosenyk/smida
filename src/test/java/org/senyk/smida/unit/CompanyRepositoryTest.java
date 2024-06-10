package org.senyk.smida.unit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.senyk.smida.entity.Company;
import org.senyk.smida.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CompanyRepositoryTest {

  @Autowired
  private CompanyRepository companyRepository;

  @Test
  public void CompanyRepository_SaveAll_ReturnSavedCompany() {

    Company company = Company.builder()
        .name("ФОП Дмитро")
        .registrationNumber("1234qwerty")
        .address("Lviv")
        .createdAt(LocalDateTime.now())
        .build();

    Company savedCompany = companyRepository.save(company);

    Assertions.assertThat(savedCompany).isNotNull();
    Assertions.assertThat(savedCompany.getId()).isGreaterThan(0);
  }

  @Test
  public void CompanyRepository_GetAll_ReturnMoreThenOneCompany() {

    Company company = Company.builder()
        .name("ФОП Дмитро")
        .registrationNumber("1234qwerty")
        .address("Lviv")
        .createdAt(LocalDateTime.now())
        .build();
    Company company2 = Company.builder()
        .name("ФОП Назар")
        .registrationNumber("1234qwerty2")
        .address("Lviv")
        .createdAt(LocalDateTime.now())
        .build();

    companyRepository.save(company);
    companyRepository.save(company2);

    List<Company> companyList = companyRepository.findAll();

    Assertions.assertThat(companyList).isNotNull();
    Assertions.assertThat(companyList.size()).isEqualTo(2);
  }

  @Test
  public void CompanyRepository_FindById_ReturnCompany() {

    Company company = Company.builder()
        .name("ФОП Дмитро")
        .registrationNumber("1234qwerty")
        .address("Lviv")
        .createdAt(LocalDateTime.now())
        .build();

    companyRepository.save(company);

    Company companyList = companyRepository.findById(company.getId()).get();

    Assertions.assertThat(companyList).isNotNull();
  }

  @Test
  public void CompanyRepository_UpdateCompany_ReturnCompanyNotNull() {

    Company company = Company.builder()
        .name("ФОП Дмитро")
        .build();

    companyRepository.save(company);

    Company companySave = companyRepository.findById(company.getId()).get();
    companySave.setRegistrationNumber("1234qwerty");

    Company updatedCompany = companyRepository.save(companySave);

    Assertions.assertThat(updatedCompany.getRegistrationNumber()).isNotNull();
  }

  @Test
  public void CompanyRepository_CompanyDelete_ReturnCompanyIsEmpty() {

    Company company = Company.builder()
        .name("ФОП Дмитро")
        .build();

    companyRepository.save(company);

    companyRepository.deleteById(company.getId());
    Optional<Company> companyReturn = companyRepository.findById(company.getId());

    Assertions.assertThat(companyReturn).isEmpty();
  }

}
