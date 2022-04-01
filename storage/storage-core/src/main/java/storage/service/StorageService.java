package storage.service;

import org.springframework.stereotype.Service;
import storage.model.StorageEntity;
import storage.repository.StorageRepository;

import java.util.List;

@Service
public class StorageService {

    StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<StorageEntity> findAll(){
        return storageRepository.getAll();
    }

    public StorageEntity findById (Long id){
        return storageRepository.getById(id);
    }

    public void deleteById (Long id){
        storageRepository.deleteById(id);
    }

    public Long getCount (String name){
        return storageRepository.getCount(name);
    }

    public void updateCount (Long count, String name){
        storageRepository.updateCount(count, name);
    }
}
