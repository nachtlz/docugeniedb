package app.docugeniedb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long message_id;
    private String sender;
    private String message_text;
    private String message_date;
    private Long chat_id;
}
