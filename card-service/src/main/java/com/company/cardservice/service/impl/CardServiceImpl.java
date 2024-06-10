package com.company.cardservice.service.impl;

import com.company.cardservice.client.UserServiceClient;
import com.company.cardservice.client.dto.UserResponse;
import com.company.cardservice.dto.CardRequest;
import com.company.cardservice.dto.CardResponse;
import com.company.cardservice.dto.ResponseDto;
import com.company.cardservice.entity.Card;
import com.company.cardservice.exception.ResourceNotFoundException;
import com.company.cardservice.repository.CardRepository;
import com.company.cardservice.service.CardService;
import com.company.cardservice.service.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;
    private final CardRepository cardRepository;
    private final UserServiceClient userServiceClient;

    @Override
    public ResponseDto<CardResponse> create(CardRequest dto, Integer userId) {
        try {
            Card entity = this.cardMapper.toEntity(dto);
            UserResponse userResponse = Objects.requireNonNull(this.userServiceClient.get(userId).getBody()).getContent();
            entity.setCardOwnerId(userResponse.getUserId());
            return ResponseDto.<CardResponse>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.cardMapper.toDto(
                                    this.cardRepository.save(entity)
                            )
                    ).build();
        } catch (Exception e) {
            return ResponseDto.<CardResponse>builder()
                    .code(-3)
                    .message(e.getMessage()).build();
        }

    }

    @Override
    public ResponseDto<CardResponse> get(Integer cardId) {
        try {
            Card card = this.cardRepository.findCardByCardIdAndDeletedAtIsNull(cardId).orElseThrow(() -> new ResourceNotFoundException("Card", "cardId", cardId));

            return ResponseDto.<CardResponse>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.cardMapper.toDto(card)
                    ).build();
        } catch (Exception e) {
            return ResponseDto.<CardResponse>builder()
                    .code(-3)
                    .message(e.getMessage())
                    .build();
        }

    }

    @Override
    public ResponseDto<CardResponse> update(Integer cardId, CardRequest dto) {
        try {
            Card card = this.cardRepository.findCardByCardIdAndDeletedAtIsNull(cardId).orElseThrow(() -> new ResourceNotFoundException("Card", "cardId", cardId));

            return ResponseDto.<CardResponse>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.cardMapper.toDto(
                                    this.cardRepository.save(
                                            this.cardMapper.update(card, dto)
                                    )
                            )
                    ).build();
        } catch (Exception e) {
            return ResponseDto.<CardResponse>builder()
                    .code(-3)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<CardResponse> delete(Integer cardId) {
        try {
            Card card = this.cardRepository.findCardByCardIdAndDeletedAtIsNull(cardId).orElseThrow(() -> new ResourceNotFoundException("Card", "cardId", cardId));

            card.setDeletedAt(LocalDateTime.now());
            return ResponseDto.<CardResponse>builder()
                    .success(true)
                    .message("OK")
                    .content(this.cardMapper.toDto(this.cardRepository.save(card)
                            )
                    ).build();
        } catch (Exception e) {
            return ResponseDto.<CardResponse>builder()
                    .code(-3)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<CardResponse>> getAll() {
        List<Card> all = this.cardRepository.findAll();
        return ResponseDto.<List<CardResponse>>builder()
                .success(true)
                .message("OK")
                .content(all.stream().map(this.cardMapper::toDto).toList())
                .build();
    }

    @Override
    public ResponseDto<List<CardResponse>> getByUserId(Integer userId) {
        try {
            List<Card> list = this.cardRepository.findCardByCardOwnerIdAndDeletedAtIsNull(userId);
            return ResponseDto.<List<CardResponse>>builder()
                    .success(true)
                    .message("OK")
                    .content(list.stream().map(this.cardMapper::toDto).toList()).build();
        }
        catch (Exception e ){
            throw new ResourceNotFoundException("Card", "userId", userId);
        }

    }
}
