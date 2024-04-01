package app.docugeniedb.dto;

import app.docugeniedb.entity.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private Long file_id;
    private String filename;
    private String filepath;
    private String description;
    private String category;
    private Long person_id;
}
