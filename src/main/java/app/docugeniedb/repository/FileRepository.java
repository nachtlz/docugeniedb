package app.docugeniedb.repository;

import app.docugeniedb.entity.Chat;
import app.docugeniedb.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    @Query("SELECT f FROM File f WHERE f.person.id = :personId")
    List<File> findByPersonId(@Param("personId") Long personId);
}
