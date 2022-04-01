package storage.service;

import org.springframework.stereotype.Service;
import storage.model.JournalEntity;
import storage.repository.JournalRepository;

import java.util.List;

@Service
public class JournalService {

    JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public List<JournalEntity> findAll(){
        return journalRepository.getAll();
    }

    public void writeJournal (JournalEntity journalEntity){
        journalRepository.writeJournal(journalEntity);
    }
}
