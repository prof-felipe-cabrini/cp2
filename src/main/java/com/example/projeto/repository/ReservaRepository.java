package com.example.projeto.repository;

import com.example.projeto.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r JOIN r.cliente c " +
            "WHERE (:clienteId IS NULL OR c.id = :clienteId) " +
            "AND (:startDate IS NULL OR r.dataCheckIn >= :startDate) " +
            "AND (:endDate IS NULL OR r.dataCheckOut <= :endDate)")
    Page<Reserva> findReservas(@Param("clienteId") Long clienteId,
                               @Param("startDate") LocalDate startDate,
                               @Param("endDate") LocalDate endDate,
                               Pageable pageable);
}