package app.docugeniedb.mapper;

import app.docugeniedb.dto.MessageDTO;
import app.docugeniedb.entity.Message;

import java.sql.Timestamp;

public class MessageMapper {

    public static MessageDTO mapToMessageDTO(Message message) {
        return new MessageDTO(
                message.getId(),
                message.getSender(),
                message.getMessageText(),
                message.getMessageDate().toString(),
                message.getChat().getId()
        );
    }

    public static Message mapToMessage(MessageDTO messageDTO) {
        return new Message(
                messageDTO.getMessage_id(),
                messageDTO.getSender(),
                messageDTO.getMessage_text(),
                Timestamp.valueOf(messageDTO.getMessage_date()),
                null
        );
    }
}
