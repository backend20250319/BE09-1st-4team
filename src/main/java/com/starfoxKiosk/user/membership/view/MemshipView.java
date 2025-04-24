package com.starfoxKiosk.user.membership.view;

import com.starfoxKiosk.user.membership.domain.Membership;

import java.util.Scanner;

public class MemshipView {

    private Scanner sc = new Scanner(System.in);

    public String inputPhoneNum(){
        System.out.print("전화번호를 입력하세요 (숫자만) : ");
        return sc.nextLine();
    }

    public void displayMembershipInfo(Membership membership){
        System.out.println("멤버십 등급 : " + membership.getMembershipName());
        System.out.println("누적 금액 : " + membership.getDefaultPrice() + " 원");
    }



}
