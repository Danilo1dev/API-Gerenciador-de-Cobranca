package br.com.naturaves.cobrancanaturaves.boleto.infra;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;

public interface TituloInfraJpaRepository extends JpaRepository<Boleto, UUID> {

	@Query(value = "SELECT B.*"
			+ " FROM BOLETO B JOIN CLIENTE C ON B.ID_CLIENTE_COMERCIAL = C.ID_CLIENTE"
			+ " WHERE C.NOME_VENDEDOR = ?1"
			,nativeQuery=true)
	List<Boleto> buscaBoletoVencidoPorVendedor(String nomeVendedor,
		LocalDate dataVencimento);
}
