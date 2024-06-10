package org.senyk.smida.service;

import javax.persistence.EntityNotFoundException;
import org.senyk.smida.entity.ReportDetails;
import org.senyk.smida.repository.ReportDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportDetailsService {

  private final ReportDetailsRepository reportDetailsRepository;

  @Autowired
  public ReportDetailsService(ReportDetailsRepository reportDetailsRepository) {

    this.reportDetailsRepository = reportDetailsRepository;
  }

  public ReportDetails getReportDetailsById(Long reportId) {

    return reportDetailsRepository.findByReportId(reportId)
        .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + reportId));
  }

  public void addReportDetails(ReportDetails reportDetails) {

    reportDetailsRepository.save(reportDetails);
  }
}
