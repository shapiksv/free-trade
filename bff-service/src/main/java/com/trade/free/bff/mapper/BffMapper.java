package com.trade.free.bff.mapper;

import com.trade.free.dto.rest.item.ItemRespDto;
import com.trade.free.dto.rest.user.UserExternalRespDto;
import com.trade.free.dto.rest.user.UserRespDto;
import com.trade.free.dto.rest.wallet.WalletRespDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BffMapper {

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "secondName", source = "user.secondName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "dateOfBirthday", source = "user.dateOfBirthday")
    @Mapping(target = "amount", source = "wallet.amount")
    @Mapping(target = "numberOfItems", source = "items", qualifiedByName = "getNumberOfItem")
    UserExternalRespDto toExternal(UserRespDto user, WalletRespDto wallet, List<ItemRespDto> items);

    @Named("getNumberOfItem")
     default Integer getNumberOfItem(List<ItemRespDto> items) {
        return items == null ? 0 : items.size();
    }
}
