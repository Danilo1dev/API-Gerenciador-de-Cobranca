package br.com.naturaves.cobrancanaturaves.boleto.infra;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;

public interface TituloInfraJpaRepository extends JpaRepository<Boleto, String> {

	List<Boleto> buscaBoletoVencidoPorVendedor(@Param ("nomeVendedor") String nomeVendedor,@Param ("dataVencimento") LocalDate dataVencimento);
}
