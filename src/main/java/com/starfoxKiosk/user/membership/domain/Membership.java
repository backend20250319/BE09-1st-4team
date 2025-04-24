package com.starfoxKiosk.user.membership.domain;

public class Membership {
    private int membershipId;
    private String membershipName;
    private int defaultPrice;
    private int customerId;

    public Membership() {}

    public Membership(int membershipId, String membershipName, int defaultPrice, int customerId) {
        this.membershipId = membershipId;
        this.membershipName = membershipName;
        this.defaultPrice = defaultPrice;
        this.customerId = customerId;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public int getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(int defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "membershipId=" + membershipId +
                ", membershipName='" + membershipName + '\'' +
                ", defaultPrice=" + defaultPrice +
                ", customerId=" + customerId +
                '}';
    }


}
