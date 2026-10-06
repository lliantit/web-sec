package chnu.edu.websec.controller;

/*
  @author   qwert
  @project   web-sec
  @class  ItemController
  @version  1.0.0 
  @since 20.09.2026 - 03.18



*/

import chnu.edu.websec.model.Car;
import chnu.edu.websec.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items/")

@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

        @GetMapping
        public List<Car> getAllItem(String id) {
            return itemService.getALLItems();
        }

        @GetMapping("/{id}")
        public Car getItem(@PathVariable String id) {
            return itemService.getItem(id);
        }
        @PostMapping
        public Car createItem(@RequestBody Car item) {
            return itemService.createItem(item);
        }

        @PutMapping
        public Car updateItem(@RequestBody Car item) {
            return itemService.updateItem(item);
        }
        @DeleteMapping("/{id}")
        public void deleteItem(@PathVariable String id) {
            itemService.deleteItem(id);
        }

}
