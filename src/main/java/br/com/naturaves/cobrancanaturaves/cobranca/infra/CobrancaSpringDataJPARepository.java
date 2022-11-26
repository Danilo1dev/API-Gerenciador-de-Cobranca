package br.com.naturaves.cobrancanaturaves.cobranca.infra;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;

public interface CobrancaSpringDataJPARepository extends JpaRepository<Cobranca, UUID>{
	List<Cobranca> findByIdBoleto(UUID idBoleto);
	find
}
