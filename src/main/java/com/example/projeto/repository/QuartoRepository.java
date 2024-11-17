package com.example.projeto.repository;

import com.example.projeto.model.Hotel;
import com.example.projeto.model.Quarto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    @Query("SELECT q FROM Quarto q WHERE q.tipo = :tipo AND q.hotel = :hotel")
    Page<Quarto> findAllByTipoAndHotel(@Param("tipo") String tipo, @Param("hotel") Hotel hotel, Pageable pageable);

    @Query("SELECT q FROM Quarto q LEFT JOIN q.reservas r " +
            "WHERE :availableDate NOT BETWEEN r.dataCheckIn AND r.dataCheckOut")
    Page<Quarto> findQuartosDisponiveis(@Param("availableDate") LocalDate availableDate, Pageable pageable);
}