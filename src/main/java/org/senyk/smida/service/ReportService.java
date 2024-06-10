package org.senyk.smida.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.senyk.smida.dto.ReportDto;
import org.senyk.smida.entity.Report;
import org.senyk.smida.entity.ReportDetails;
import org.senyk.smida.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

  private final ReportRepository reportRepository;
  private final ReportDetailsService reportDetailsService;

  @Autowired
  public ReportService(ReportRepository reportRepository,
      ReportDetailsService reportDetailsService) {

    this.reportRepository = reportRepository;
    this.reportDetailsService = reportDetailsService;
  }

  public List<ReportDto> getAllReports() {

    List<Report> reports = reportRepository.findAll();
    return reports.stream()
        .map(this::convertEntityToDto)
        .sorted(Comparator.comparing(ReportDto::getId))
        .collect(Collectors.toList());
  }

  public ReportDto getReportById(Long reportId) {

    Report report = reportRepository.findById(reportId)
        .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + reportId));
    return convertEntityToDto(report);
  }

  public void addReport(ReportDto reportDto) {

    Report report = new Report();
    report = convertDtoToEntity(reportDto,report.getId());
    reportRepository.save(report);
  }

  public void deleteReportById(Long reportId) {

    reportRepository.findById(reportId)
        .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + reportId));
    reportRepository.deleteById(reportId);
  }

  public void updateReport(ReportDto reportDto, Long reportId) {

    Report report = reportRepository.findById(reportId)
        .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + reportId));
    report = convertDtoToEntity(reportDto,report.getId());
    reportRepository.save(report);
  }

  public List<ReportDto> getReportsFromCompany(Long companyId) {

    List<Report> reports = reportRepository.findAllByCompanyId(companyId);
    return reports.stream()
        .map(this::convertEntityToDto)
        .sorted(Comparator.comparing(ReportDto::getId))
        .collect(Collectors.toList());
  }

  public ReportDetails getReportDetails(Long reportId) {

    return reportDetailsService.getReportDetailsById(reportId);
  }

  public void addReportDetails(ReportDetails reportDetails) {

    reportRepository.findById(reportDetails.getReportId())
        .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + reportDetails.getReportId()));
    reportDetailsService.addReportDetails(reportDetails);
  }

  private ReportDto convertEntityToDto(Report report) {

    return ReportDto.builder()
        .id(report.getId())
        .companyId(report.getCompanyId())
        .reportDate(report.getReportDate())
        .totalRevenue(report.getTotalRevenue())
        .netProfit(report.getNetProfit())
        .build();
  }

  private Report convertDtoToEntity(ReportDto reportDto, Long reportId) {

    return Report.builder()
        .id(reportId)
        .companyId(reportDto.getCompanyId())
        .reportDate(reportDto.getReportDate())
        .totalRevenue(reportDto.getTotalRevenue())
        .netProfit(reportDto.getNetProfit())
        .build();
  }
}
