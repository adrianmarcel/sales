package br.com.fiap.service.sales.gateway.database.sales.repository;

import br.com.fiap.service.sales.gateway.database.BaseRepository;
import br.com.fiap.service.sales.gateway.database.sales.model.SalesEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends BaseRepository<SalesEntity, UUID> {

  Optional<SalesEntity> findByIdAndPaymentCode(UUID id, UUID paymentCode);
}
