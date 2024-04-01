package app.docugeniedb.service;

import app.docugeniedb.dto.MessageDTO;
import app.docugeniedb.entity.Message;

import java.util.List;

public interface MessageService {

    MessageDTO createMessage(MessageDTO messageDTO);

    MessageDTO getMessageById(Long messageId);

    List<MessageDTO> getMessagesByChatId(Long chatId);

    List<MessageDTO> getAllMessages();

    MessageDTO updateMessage(Long messageId, MessageDTO updatedMessage);

    void deleteAllMessagesByChatId(Long chatId);
}
