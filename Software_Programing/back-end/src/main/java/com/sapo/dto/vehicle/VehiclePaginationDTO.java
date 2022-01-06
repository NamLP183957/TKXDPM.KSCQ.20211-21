package com.sapo.dto.vehicle;

import com.sapo.dto.common.Pagination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiclePaginationDTO {
  private List<VehicleDTOResponse> vehicleDTOResponses;
  private Pagination pagination;
}
