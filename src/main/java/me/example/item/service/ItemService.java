package me.example.item.service;

import jakarta.transaction.Transactional;
import me.example.item.domain.Item;
import me.example.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
    Service
    - 핵심 비즈니스 로직 처리
    - Repository 호출
*/

@Service
public class ItemService {
    private final ItemRepository itemRepository;

//    필드주입 순환참조? 왜 생성주 주입 사용하는가.?
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // 전체 조회
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    // 단일 조회
    public Optional<Item> findById(final Long id) {
        return itemRepository.findById(id);
    }

    // 생성
    public Item create(Item item) {
        return itemRepository.save(item);
    }

    // 수정
    @Transactional
    public Item update(
            final Long id,
            final Item updatedItem
    ) {
        return itemRepository.findById(id)
                .map(item -> {
                    // 새로운 객체 생성 후 저장
//                    item = new Item(updatedItem.getTitle(), updatedItem.getAuthor());
                    item.update(updatedItem);
                    return itemRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // 삭제
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
