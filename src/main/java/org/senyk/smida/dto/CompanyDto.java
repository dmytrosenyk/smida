package org.senyk.smida.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

  private Long id;
  private String name;
  private String registrationNumber;
  private String address;
  private LocalDateTime createdAt;
}
