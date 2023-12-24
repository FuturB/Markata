package com.futureB.backend.dtos;

public class PaymentDetailsDTO {

    private long buyer_id;
    private long order_Id;
    private String name;
    private String email;
    private Double amount;
    private String currency;
    private String type;
    private String paymentMethodId;
    private String cardNumber;
    private Integer exp_month;
    private Integer exp_year;
    private String cvc;

//    static class CreatePaymentItem {
//        @SerializedName("id")
//        String id;
//
//        public String getId() {
//            return id;
//        }
//    }
//
//    static class CreatePayment {
//        @SerializedName("items")
//        CreatePaymentItem[] items;
//
//        public CreatePaymentItem[] getItems() {
//            return items;
//        }
//    }
}
