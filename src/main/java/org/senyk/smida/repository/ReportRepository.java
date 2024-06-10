package org.senyk.smida.repository;

import java.util.List;
import org.senyk.smida.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

  List<Report> findAllByCompanyId(Long companyId);
}
