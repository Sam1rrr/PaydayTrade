//package com.gradiant.paydaytrade.util;
//
//import com.gradiant.paydaytrade.dto.card.CardDto;
//import com.gradiant.paydaytrade.entity.Card;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CardDtoConverter {
//    public CardDto converter(Card card) {
//        CardDto cardDto = CardDto.builder()
//                .cartType(card.getCartType())
//                .cardNumber(card.getCardNumber())
//                .cvv(card.getCvv())
//                .holderName(card.getHolderName())
//                .expiredDate(card.getExpiredDate())
//                .build();
//        return cardDto;
//    }
//
//}
