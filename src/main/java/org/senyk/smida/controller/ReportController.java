package org.senyk.smida.controller;

import java.util.List;
import org.senyk.smida.dto.ReportDto;
import org.senyk.smida.entity.ReportDetails;
import org.senyk.smida.service.ReportService;
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
@RequestMapping("/report")
public class ReportController {

  private final ReportService reportService;

  @Autowired
  public ReportController(ReportService reportService) {

    this.reportService = reportService;
  }

  @GetMapping()
  public List<ReportDto> getAllReports(){

    return reportService.getAllReports();
  }

  @GetMapping("{reportId}")
  public ReportDto getReportById(@PathVariable Long reportId){

    return reportService.getReportById(reportId);
  }

  @PostMapping()
  public void addReport(@RequestBody ReportDto reportDto){

    reportService.addReport(reportDto);
  }

  @PutMapping("{reportId}")
  public void updateReport(@PathVariable Long reportId, @RequestBody ReportDto reportDto){

    reportService.updateReport(reportDto, reportId);
  }

  @DeleteMapping("{reportId}")
  public void updateReport(@PathVariable Long reportId){

    reportService.deleteReportById(reportId);
  }

  @GetMapping("company/{companyId}")
  public List<ReportDto> getReportsFromCompany(@PathVariable Long companyId){

    return reportService.getReportsFromCompany(companyId);
  }

  @GetMapping("details/{reportId}")
  public ReportDetails getReportDetails(@PathVariable Long reportId){

    return reportService.getReportDetails(reportId);
  }

  @PostMapping("details")
  public void addReportDetails(@RequestBody ReportDetails reportDetails){

    reportService.addReportDetails(reportDetails);
  }
}
