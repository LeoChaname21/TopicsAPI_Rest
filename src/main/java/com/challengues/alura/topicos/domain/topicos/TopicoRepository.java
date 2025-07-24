package com.challengues.alura.topicos.domain.topicos;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Boolean existsByTituloAndMensaje(String titulo,String mensaje);

    Page<Topico> findAllByStatusTrue(Pageable pag);

    @Query("SELECT t.status FROM Topico t WHERE t.id = :idtopico")
    Boolean findStatusById(Long idtopico);


}
