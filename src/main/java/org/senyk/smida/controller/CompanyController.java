package org.senyk.smida.controller;

import java.util.List;
import org.senyk.smida.dto.CompanyDto;
import org.senyk.smida.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

  private final CompanyService companyService;

  @Autowired
  public CompanyController(CompanyService companyService) {

    this.companyService = companyService;
  }

  @GetMapping()
  public List<CompanyDto> getAllCompanies(){

    return companyService.getAllCompanies();
  }

  @GetMapping("{companyId}")
  public CompanyDto getCompanyById(@PathVariable Long companyId){

    return companyService.getCompanyById(companyId);
  }

  @PostMapping()
  public void addCompany(@RequestBody CompanyDto companyDto){

    companyService.addCompany(companyDto);
  }

  @PutMapping("{companyId}")
  public void updateCompany(@RequestBody CompanyDto companyDto, @PathVariable Long companyId){

    companyService.updateCompany(companyDto, companyId);
  }

  @DeleteMapping("{companyId}")
  public void updateCompany(@PathVariable Long companyId){

    companyService.deleteCompanyById(companyId);
  }
}
