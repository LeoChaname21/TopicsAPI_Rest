package com.challengues.alura.topicos.domain.respuestas;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RespuestaRepository extends JpaRepository<Respuestas, Long> {

    @Query("SELECT r FROM Topico t JOIN t.respuestas r WHERE t.id = :idtopico AND r.status = true")
    Page<Respuestas> findTopicosRespuestas(Long idtopico, Pageable pag);

    @Query("SELECT r.status FROM Respuestas r WHERE r.id = :id")
    Boolean findStatusById(Long id);
}
