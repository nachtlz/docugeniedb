package app.docugeniedb.service.impl;

import app.docugeniedb.dto.FileDTO;
import app.docugeniedb.dto.PersonDTO;
import app.docugeniedb.entity.File;
import app.docugeniedb.exception.ResourceNotFoundException;
import app.docugeniedb.mapper.FileMapper;
import app.docugeniedb.mapper.PersonMapper;
import app.docugeniedb.repository.FileRepository;
import app.docugeniedb.service.FileService;
import app.docugeniedb.service.PersonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    private PersonService personService;

    @Override
    @Transactional
    public FileDTO createFile(FileDTO fileDTO) {
        File file = FileMapper.mapToFile(fileDTO);
        PersonDTO personDTO = personService.getPersonById(fileDTO.getPerson_id());
        file.setPerson(PersonMapper.mapToPerson(personDTO));
        File savedFile = fileRepository.save(file);
        return FileMapper.mapToFileDTO(savedFile);
    }

    @Override
    public FileDTO getFileById(Long fileId) {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("File does not exist with given id: " + fileId));
        return FileMapper.mapToFileDTO(file);
    }

    @Override
    public List<FileDTO> getFilesByPersonId(Long personId) {
        List<File> fileList = fileRepository.findByPersonId(personId);
        return fileList.stream().map(FileMapper::mapToFileDTO).toList();
    }

    @Override
    public List<FileDTO> getAllFiles() {
        List<File> fileList = fileRepository.findAll();
        return fileList.stream().map(FileMapper::mapToFileDTO).toList();
    }

    @Override
    @Transactional
    public FileDTO updateFile(Long fileId, FileDTO updatedFile) {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("File does not exist with given id: " + fileId));
        PersonDTO personDTO = personService.getPersonById(updatedFile.getPerson_id());
        file.setFilename(updatedFile.getFilename());
        file.setFilepath(updatedFile.getFilepath());
        file.setDescription(updatedFile.getDescription());
        file.setCategory(updatedFile.getCategory());
        file.setPerson(PersonMapper.mapToPerson(personDTO));
        File savedFile = fileRepository.save(file);
        return FileMapper.mapToFileDTO(savedFile);
    }

    @Override
    @Transactional
    public void deleteFile(Long fileId) {
        fileRepository.findById(fileId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("File does not exist with given id: " + fileId));
        fileRepository.deleteById(fileId);
    }
}
