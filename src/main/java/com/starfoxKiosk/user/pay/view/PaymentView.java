package com.starfoxKiosk.user.pay.view;

import java.util.Scanner;

public class PaymentView {

    private final Scanner sc = new Scanner(System.in);

    public String selectPaymentMethod() {
        System.out.println("""
                결제 수단을 선택하세요. 
                1. 카드
                2. 간편결제
                3. 삼성페이
                """);

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                return "카드";
            case 2:
                return "간편결제";
            case 3:
            default:
                System.out.println("잘못된 선택입니다. 기본값 '카드'로 진행합니다.");
                return "카드";
        }


    }

    public void showResult(boolean b) {
    }
}
