package com.company.cardservice.controller;

import com.company.cardservice.dto.CardRequest;
import com.company.cardservice.dto.CardResponse;
import com.company.cardservice.dto.ResponseDto;
import com.company.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.company.cardservice.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/card")
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<ResponseDto<CardResponse>> create(@RequestBody CardRequest dto, @RequestParam(value = "id") Integer userId) {
        return convertStatusCodeByData(this.cardService.create(dto, userId));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<CardResponse>> get(@RequestParam(value = "id") Integer cardId) {
        return convertStatusCodeByData(this.cardService.get(cardId));
    }

    @GetMapping(value = "user-id")
    ResponseEntity<ResponseDto<List<CardResponse>>> getByUserId(@RequestParam(value = "id") Integer userId){
        return convertStatusCodeByData(this.cardService.getByUserId(userId));
    }

    @PutMapping
    public ResponseEntity<ResponseDto<CardResponse>> update(@RequestParam(value = "id") Integer cardId,
                                                                     @RequestBody CardRequest dto) {
        return convertStatusCodeByData(this.cardService.update(cardId, dto));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<CardResponse>> delete(@RequestParam(value = "id") Integer cardId) {
        return convertStatusCodeByData(this.cardService.delete(cardId));
    }

    @GetMapping(value = "get-all")
    public ResponseEntity<ResponseDto<List<CardResponse>>> getAll(){
        return convertStatusCodeByData(this.cardService.getAll());}

}
