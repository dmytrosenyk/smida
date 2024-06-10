package org.senyk.smida.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "report_details")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDetails {

  @MongoId
  private Long reportId;
  private List<String> financialData;
  private String comments;
}
