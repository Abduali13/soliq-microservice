package com.company.cardservice.service;

import com.company.cardservice.dto.CardRequest;
import com.company.cardservice.dto.CardResponse;
import com.company.cardservice.dto.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    ResponseDto<CardResponse> create(CardRequest dto, Integer userId);

    ResponseDto<CardResponse> get(Integer cardId);

    ResponseDto<CardResponse> update(Integer cardId, CardRequest dto);

    ResponseDto<CardResponse> delete(Integer cardId);

    ResponseDto<List<CardResponse>> getAll();

    ResponseDto<List<CardResponse>> getByUserId(Integer userId);
}
