package app.docugeniedb.dto;

import app.docugeniedb.entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private Long chat_id;
    private String name;
    private String description;
    private String creation_date;
    private String mode;
    private String header;
    private String color;
    private String sender_color;
    private String receiver_color;
    private String background_color;
    private String external_color;
    private String orientation;
    private List<MessageDTO> messages;
    private Long person_id;
    private Set<FileDTO> files;
}
