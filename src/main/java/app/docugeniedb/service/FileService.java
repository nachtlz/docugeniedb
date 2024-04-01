package app.docugeniedb.service;

import app.docugeniedb.dto.ChatDTO;
import app.docugeniedb.dto.FileDTO;

import java.util.List;

public interface FileService {

    FileDTO createFile(FileDTO fileDTO);

    FileDTO getFileById(Long fileId);

    List<FileDTO> getFilesByPersonId(Long personId);

    List<FileDTO> getAllFiles();

    FileDTO updateFile(Long fileId, FileDTO updatedFile);

    void deleteFile(Long fileId);
}
