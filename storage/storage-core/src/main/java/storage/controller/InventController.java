package storage.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personservice.dto.DeliveryRequestDto;
import personservice.model.ListEntity;
import personservice.service.DeliveryRequestService;
import personservice.service.ListService;
import personservice.service.ProductService;
import storage.model.JournalEntity;
import storage.service.JournalService;
import storage.service.StorageService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Как только товары подготовят к отправке,
 * сюда поступает информация об их списке.
 *
 * @invent(..) - Происходит автоматическое обновление остатков, статус заявки на поставку меняется
 * @writeJournal(..) - Запись в журнал о движении товара
 */
@RestController
@RequestMapping("/invent")
public class InventController {

    StorageService storageService;
    JournalService journalService;
    ListService listService;
    ProductService productService;
    DeliveryRequestService deliveryRequestService;

    public InventController(StorageService storageService, JournalService journalService, ListService listService, ProductService productService, DeliveryRequestService deliveryRequestService) {
        this.storageService = storageService;
        this.journalService = journalService;
        this.listService = listService;
        this.productService = productService;
        this.deliveryRequestService = deliveryRequestService;
    }

    @PostMapping("/main")
    void invent (@RequestBody DeliveryRequestDto deliveryRequestDto) throws Exception{
        List<ListEntity> list = listService.getAllByNumber(deliveryRequestDto.getList());
        for (ListEntity l : list){
            Long newCount = storageService.getCount(productService.findNameById(l.getProduct())) - l.getCount();
            if (newCount < 0) {
                throw new RuntimeException("Недостаточно товаров на складе");
            } else {
                storageService.updateCount(newCount, productService.findNameById(l.getProduct()));
                writeJournal(l);
            }
        }
        deliveryRequestService.updateStatus(deliveryRequestDto.getList(), "Done");
    }

    public void writeJournal (ListEntity listEntity){
        JournalEntity journalEntity = new JournalEntity();
        LocalDateTime date = LocalDateTime.now();

        journalEntity.setType("out");
        journalEntity.setProduct(listEntity.getProduct());
        journalEntity.setCategory(productService.findCategoryById(listEntity.getProduct()));
        journalEntity.setCount(listEntity.getCount());
        journalEntity.setDttm(date);
    }
}
