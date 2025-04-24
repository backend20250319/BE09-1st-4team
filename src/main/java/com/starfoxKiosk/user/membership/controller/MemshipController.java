package com.starfoxKiosk.user.membership.controller;

import com.starfoxKiosk.user.membership.service.MemshipService;
import com.starfoxKiosk.user.membership.view.MemshipView;

public class MemshipController {

    private MemshipService memshipService;
    private MemshipView memshipView;

    private MemshipController() {
        String phone = memshipView.inputPhoneNum();
        Membership membership = memshipService.findByPhone(phone);
    }
}
