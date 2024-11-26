package com.trade.free.item.repository;

import com.trade.free.item.entities.Item;
import com.trade.free.item.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByOwnerId(Integer ownerId);

    List<Item> findAllByOwnerIdNotAndStatus(Integer ownerId, ItemStatus status);

    Optional<Item> findByOwnerIdAndId(Integer ownerId, Integer itemId);
}
