package org.senyk.smida.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.senyk.smida.dto.CompanyDto;
import org.senyk.smida.entity.Company;
import org.senyk.smida.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

  private final CompanyRepository companyRepository;

  @Autowired
  public CompanyService(CompanyRepository companyRepository) {

    this.companyRepository = companyRepository;
  }

  public List<CompanyDto> getAllCompanies() {

    List<Company> companies = companyRepository.findAll();
    return companies.stream()
        .map(this::convertEntityToDto)
        .sorted(Comparator.comparing(CompanyDto::getId))
        .collect(Collectors.toList());
  }

  public CompanyDto getCompanyById(Long companyId) {

    Company company = companyRepository.findById(companyId)
        .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + companyId));
    return convertEntityToDto(company);
  }

  public void addCompany(CompanyDto companyDto) {

    Company company = new Company();
    company = convertDtoToEntity(companyDto,company.getId());
    companyRepository.save(company);
  }

  public void deleteCompanyById(Long companyId) {

    companyRepository.findById(companyId)
        .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + companyId));
    companyRepository.deleteById(companyId);
  }

  public void updateCompany(CompanyDto companyDto, Long companyId) {

    Company company = companyRepository.findById(companyId)
        .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + companyId));
    company = convertDtoToEntity(companyDto,company.getId());
    companyRepository.save(company);
  }

  private CompanyDto convertEntityToDto(Company company) {

    return CompanyDto.builder()
        .id(company.getId())
        .name(company.getName())
        .registrationNumber(company.getRegistrationNumber())
        .address(company.getAddress())
        .createdAt(company.getCreatedAt())
        .build();
  }

  private Company convertDtoToEntity(CompanyDto companyDto, Long companyId) {

    return Company.builder()
        .id(companyId)
        .name(companyDto.getName())
        .registrationNumber(companyDto.getRegistrationNumber())
        .address(companyDto.getAddress())
        .createdAt(companyDto.getCreatedAt())
        .build();
  }
}
