package me.baggi.webrayze.mapper;

import me.baggi.webrayze.dto.SubscriptionDTO;
import me.baggi.webrayze.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionDTO toDTO(Subscription subscription);

    List<SubscriptionDTO> toDTO(List<Subscription> subscriptions);
}
