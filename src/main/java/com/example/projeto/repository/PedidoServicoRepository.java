package com.example.projeto.repository;

import com.example.projeto.model.PedidoServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoServicoRepository extends JpaRepository<PedidoServico, Long> {
    boolean existsByServicos_Id(Long servicoId);

    Page<PedidoServico> findAllByReservaId(Long reservaId, Pageable pageable);
}