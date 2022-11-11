package br.com.naturaves.cobrancanaturaves.cliente.infra;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.naturaves.cobrancanaturaves.cliente.domain.Cliente;

public interface ClienteSpringDataJPARepository extends JpaRepository<Cliente, UUID> {
    Cliente findByCliente(String cliente);
}
