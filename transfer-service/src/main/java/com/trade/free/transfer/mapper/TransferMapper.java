package com.trade.free.transfer.mapper;

import com.trade.free.dto.rest.transfer.TransferRespDto;
import com.trade.free.transfer.dto.TransferDto;
import com.trade.free.transfer.entities.Transfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    TransferDto mapToDto(Transfer transfer);

    TransferRespDto mapToRespDto(TransferDto transferDto);
}
