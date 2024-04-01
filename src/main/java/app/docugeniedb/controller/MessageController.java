package app.docugeniedb.controller;

import app.docugeniedb.dto.MessageDTO;
import app.docugeniedb.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/messages")
public class MessageController {

    private MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageDTO> createMessage(@RequestBody MessageDTO messageDTO) {
        MessageDTO savedMessageDTO = messageService.createMessage(messageDTO);
        return new ResponseEntity<>(savedMessageDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable("id") Long messageId) {
        MessageDTO savedMessageDTO = messageService.getMessageById(messageId);
        return ResponseEntity.ok(savedMessageDTO);
    }

    @GetMapping("/chat/{id}")
    public ResponseEntity<List<MessageDTO>> getMessagesByChatId(@PathVariable("id") Long chatId) {
        List<MessageDTO> messageDTOS = messageService.getMessagesByChatId(chatId);
        return ResponseEntity.ok(messageDTOS);
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAllMessages() {
        List<MessageDTO> messageDTOS = messageService.getAllMessages();
        return ResponseEntity.ok(messageDTOS);
    }

    @PutMapping("{id}")
    public ResponseEntity<MessageDTO> updateMessage(@PathVariable("id") Long messageId, @RequestBody MessageDTO messageDTO) {
        MessageDTO updateMessageDTO = messageService.updateMessage(messageId, messageDTO);
        return ResponseEntity.ok(updateMessageDTO);
    }

    @DeleteMapping("chat/{id}")
    public ResponseEntity<String> deleteAllMessagesByChatId(@PathVariable("id") Long chatId) {
        messageService.deleteAllMessagesByChatId(chatId);
        return ResponseEntity.ok("Messages deleted successfully!");
    }
}
