package me.example.item.repository;

import me.example.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    Repository
    - DB CRUD 전용
    - Service에서 호출
*/

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
