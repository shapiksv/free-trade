package com.trade.free.wallet_service.mapper;

import com.trade.free.dto.rest.wallet.WalletRespDto;
import com.trade.free.wallet_service.dto.WalletDto;
import com.trade.free.wallet_service.entities.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    @Mapping(source = "mutablePart.amount", target = "amount")
    WalletDto mapToDto(Wallet wallet);

    WalletRespDto mapDtoToRESP(WalletDto walletDto);
}
