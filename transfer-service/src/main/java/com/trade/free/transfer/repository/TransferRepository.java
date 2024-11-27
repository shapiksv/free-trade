package com.trade.free.transfer.repository;

import com.trade.free.transfer.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    List<Transfer> findAllBySenderUserIdOrReceiverUserId(Integer senderUserId, Integer receiverUserId);

}
