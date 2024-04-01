package app.docugeniedb.service.impl;

import app.docugeniedb.dto.ChatDTO;
import app.docugeniedb.dto.MessageDTO;
import app.docugeniedb.entity.Message;
import app.docugeniedb.exception.ResourceNotFoundException;
import app.docugeniedb.mapper.ChatMapper;
import app.docugeniedb.mapper.MessageMapper;
import app.docugeniedb.repository.MessageRepository;
import app.docugeniedb.service.ChatService;
import app.docugeniedb.service.MessageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    private ChatService chatService;

    @Override
    @Transactional
    public MessageDTO createMessage(MessageDTO messageDTO) {
        Message message = MessageMapper.mapToMessage(messageDTO);
        ChatDTO chatDTO = chatService.getChatById(messageDTO.getChat_id());
        message.setChat(ChatMapper.mapToChat(chatDTO));
        Message savedMessage = messageRepository.save(message);
        return MessageMapper.mapToMessageDTO(savedMessage);
    }

    @Override
    public MessageDTO getMessageById(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Message does not exist with given id: " + messageId));
        return MessageMapper.mapToMessageDTO(message);
    }

    @Override
    public List<MessageDTO> getMessagesByChatId(Long chatId) {
        List<Message> messageList = messageRepository.findByChatId(chatId);
        return messageList.stream().map(MessageMapper::mapToMessageDTO).toList();
    }

    @Override
    public List<MessageDTO> getAllMessages() {
        List<Message> messageList = messageRepository.findAll();
        return messageList.stream().map(MessageMapper::mapToMessageDTO).toList();
    }

    @Override
    @Transactional
    public MessageDTO updateMessage(Long messageId, MessageDTO updatedMessage) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Message does not exist with given id: " + messageId));
        ChatDTO chatDTO = chatService.getChatById(updatedMessage.getChat_id());
        message.setSender(updatedMessage.getSender());
        message.setMessageText(updatedMessage.getMessage_text());
        message.setMessageDate(Timestamp.valueOf(updatedMessage.getMessage_date()));
        message.setChat(ChatMapper.mapToChat(chatDTO));
        Message savedMessage = messageRepository.save(message);
        return MessageMapper.mapToMessageDTO(savedMessage);
    }

    @Override
    @Transactional
    public void deleteAllMessagesByChatId(Long chatId) {
        List<Message> messageList = messageRepository.findByChatId(chatId);
        messageList.forEach(message -> messageRepository.deleteById(message.getId()));
    }
}
