package app.docugeniedb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long person_id;
    private String username;
    private String password;
    private List<FileDTO> files;
    private List<ChatDTO> chats;
}
