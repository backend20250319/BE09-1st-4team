package com.starfoxKiosk.user.membership.controller;

import com.starfoxKiosk.user.membership.domain.Membership;
import com.starfoxKiosk.user.membership.service.MemshipService;
import com.starfoxKiosk.user.membership.view.MemshipView;

public class MembershipController {

    private MemshipService memshipService;
    private MemshipView memshipView;

    public MembershipController() {
        this.memshipService = memshipService;
        this.memshipView = memshipView;
    }

    public Membership start() {
        String phone = memshipView.inputPhoneNum();
        Membership membership = memshipService.findByPhone(phone);

        if (membership != null) {
            memshipView.displayMembership(membership);
        } else {
            System.out.println("해당 번호로 등록된 멤버십 정보가 없습니다.");
        }

        return membership;
    }

}
