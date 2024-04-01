package app.docugeniedb.repository;

import app.docugeniedb.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c WHERE c.person.id = :personId")
    List<Chat> findByPersonId(@Param("personId") Long personId);
}
