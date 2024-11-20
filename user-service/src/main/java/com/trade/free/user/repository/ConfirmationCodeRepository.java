package com.trade.free.user.repository;

import com.trade.free.user.entities.ConfirmationCode;
import com.trade.free.user.enums.ConfirmationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationCodeRepository  extends JpaRepository<ConfirmationCode, Integer> {

    Optional<ConfirmationCode> findByStatusAndEmail(ConfirmationStatus status, String email);
}
