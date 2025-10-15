package me.example.item.controller;

import me.example.item.domain.Item;
import me.example.item.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    Controller
    - HTTP 요청 수신
    - Service 호출
    - JSON 응답 반환
*/
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 전체 조회
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.findAll();
    }

    // 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(
            @PathVariable final Long id
    ) {
        return itemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 생성
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.create(item);
    }

    // 수정
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.update(id, item);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
