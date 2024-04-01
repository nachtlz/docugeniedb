package app.docugeniedb.repository;

import app.docugeniedb.entity.File;
import app.docugeniedb.entity.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.chat.id = :chatId")
    List<Message> findByChatId(@Param("chatId") Long chatId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Message m WHERE m.chat.id = :chatId")
    void deleteAllMessagesByChatId(@Param("chatId") Long chatId);
}
