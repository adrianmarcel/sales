package br.com.fiap.service.sales.gateway.database.sales.model;

import br.com.fiap.service.sales.core.domain.shared.enums.PaymentStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
public class SalesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column private UUID idVehicle;

  @Column private String document;

  @Column(name = "sale_date")
  private LocalDate date;

  @Column
  @Enumerated(EnumType.STRING)
  private PaymentStatus paymentStatus;

  @Column private UUID paymentCode;

  public static SalesEntity mapper(Object object) {
    var result = SalesEntity.builder().build();
    BeanUtils.copyProperties(object, result);

    return result;
  }
}
