package storage.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storage.model.JournalEntity;
import storage.service.JournalService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/journal")
public class JournalController {

    JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("/")
    List<JournalEntity> findAll (){
        return journalService.findAll();
    }
}
