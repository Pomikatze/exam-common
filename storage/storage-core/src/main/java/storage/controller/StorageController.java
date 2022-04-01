package storage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import personservice.service.ProductService;
import storage.model.StorageEntity;
import storage.service.StorageService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/storage")
public class StorageController {

    StorageService storageService;
    ProductService productService;

    public StorageController(StorageService storageService, ProductService productService) {
        this.storageService = storageService;
        this.productService = productService;
    }

    @GetMapping("/")
    List<StorageEntity> findAll (){
        return storageService.findAll();
    }

    @GetMapping("/{id}")
    StorageEntity findById (@PathVariable Long id) {
        return storageService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    HttpStatus deleteById (@PathVariable Long id){
        storageService.deleteById(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    HttpStatus updateStorage (@RequestBody StorageEntity storageEntity){
        storageService.updateCount(storageEntity.getCount(), productService.findNameById(storageEntity.getProduct()));
        return HttpStatus.OK;
    }
}
