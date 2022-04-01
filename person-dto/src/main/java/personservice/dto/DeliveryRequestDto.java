package personservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestDto {

    private String status;

    private String store;

    private LocalDate request_dt;

    private Long list;
}
