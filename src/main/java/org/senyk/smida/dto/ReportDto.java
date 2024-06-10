package org.senyk.smida.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

  private Long id;
  private Long companyId;
  private LocalDateTime reportDate;
  private BigDecimal totalRevenue;
  private BigDecimal netProfit;
}
