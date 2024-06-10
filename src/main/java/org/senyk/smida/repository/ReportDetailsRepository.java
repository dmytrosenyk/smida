package org.senyk.smida.repository;

import java.util.Optional;
import org.senyk.smida.entity.ReportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDetailsRepository extends MongoRepository<ReportDetails, Long> {

  Optional<ReportDetails> findByReportId(Long reportId);
}

