package com.trade.free.item.entities;

import com.trade.free.item.dto.ItemCreateDto;
import com.trade.free.item.dto.ItemUpdateDto;
import com.trade.free.item.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static com.trade.free.item.enums.ItemStatus.*;

@Data
@Table(name = "item")
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image_linc")
    private String imageLinc;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ItemStatus status;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    private Item() {
    }

    public static Item create(Integer ownerId,
                              ItemCreateDto itemCreateDto) {
        Item item = new Item();
        item.ownerId = ownerId;
        item.title = itemCreateDto.getTitle();
        item.description = itemCreateDto.getDescription();
        item.imageLinc = itemCreateDto.getImageLinc();
        item.price = itemCreateDto.getPrice();
        item.status = NOT_AVAILABLE;
        item.createdAt = OffsetDateTime.now();

        return item;
    }

    public Item update(ItemUpdateDto updateDto) {
        this.title = updateDto.getTitle();
        this.description = updateDto.getDescription();
        this.imageLinc = updateDto.getImageLinc();
        this.status = updateDto.getStatus();
        return this;
    }

    public Item marcAsDelete() {
        this.status = DELETED;
        return this;
    }

    public Item marcAsHold() {
        this.status = HOLD;
        return this;
    }

    public Item marcAsAvailable() {
        this.status = AVAILABLE;
        return this;
    }

    public Item assignNewOwner(Integer ownerId) {
        this.ownerId = ownerId;
        this.status = NOT_AVAILABLE;
        return this;
    }
}
