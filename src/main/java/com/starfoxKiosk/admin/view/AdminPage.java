package com.starfoxKiosk.admin.view;

import com.starfoxKiosk.admin.controller.AdminController;
import com.starfoxKiosk.admin.model.dto.Menu;
import com.starfoxKiosk.admin.model.dto.Option;
import com.starfoxKiosk.admin.model.dto.Order;
import com.starfoxKiosk.admin.model.service.AdminService;

import java.util.List;
import java.util.Scanner;

public class AdminPage {

    private final AdminController ac = new AdminController();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\n관리자 메뉴:");
            System.out.println("1. 상품 관리");
            System.out.println("2. 주문 내역 관리");
            System.out.println("3. 종료");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageProducts();
                    break;
                case "2":
                    manageOrders();
                    break;
                case "3":
                    System.out.println("관리자 콘솔을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }

    private void manageProducts() {
        while (true) {
            System.out.println("\n상품 관리 메뉴:");
            System.out.println("1. 상품 목록 조회");
            System.out.println("2. 상품 추가");
            System.out.println("3. 상품 수정");
            System.out.println("4. 상품 삭제");
            System.out.println("5. 옵션 관리");
            System.out.println("6. 이전 메뉴로 돌아가기");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    ac.viewMenuItems();
                    break;
                case "2":
                    addMenuItem();
                    break;
                case "3":
                    updateMenuItem();
                    break;
                case "4":
                    deleteMenuItem();
                    break;
                case "5":
                    manageOptions();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }

    private void updateMenuItem() {
        System.out.println("\n--- 상품 수정 ---");
        System.out.print("수정할 상품 ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Menu existingMenu = ac.getMenuItemById(id);

        if (existingMenu == null) {
            System.out.println("해당 ID의 상품이 존재하지 않습니다.");
            return;
        }
        System.out.printf("현재 상품 이름 (%s): ", existingMenu.getName());
        String name = scanner.nextLine();
        System.out.printf("현재 상품 가격 (%d): ", existingMenu.getPrice());
        int price = Integer.parseInt(scanner.nextLine());
        System.out.printf("현재 카테고리 ID (%d): ", existingMenu.getCategoryId());
        int categoryId = Integer.parseInt(scanner.nextLine());
        Menu menu = new Menu(id, name, price, categoryId);
        if (ac.updateMenuItem(menu)) {
            System.out.println("상품 정보가 성공적으로 수정되었습니다.");
        } else {
            System.out.println("상품 정보 수정에 실패했습니다.");
        }
    }


    private void addMenuItem() {
        System.out.println("\n--- 상품 추가 ---");
        System.out.print("상품 이름: ");
        String name = scanner.nextLine();
        System.out.print("상품 가격: ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.print("카테고리 ID: ");
        int categoryId = Integer.parseInt(scanner.nextLine());

//        if (ac.addMenuItem(name, price, categoryId)) {
//            System.out.println("상품이 성공적으로 추가되었습니다.");
//        } else {
//            System.out.println("상품 추가에 실패했습니다.");
//        }
    }



    private void deleteMenuItem() {
        System.out.println("\n--- 상품 삭제 ---");
        System.out.print("삭제할 상품 ID: ");
        int id = Integer.parseInt(scanner.nextLine());

//        if (adminService.deleteMenuItem(id)) {
//            System.out.println("상품이 성공적으로 삭제되었습니다.");
//        } else {
//            System.out.println("상품 삭제에 실패했습니다.");
//        }
    }

    private void manageOptions() {
        while (true) {
            System.out.println("\n--- 공통 옵션 관리 메뉴 ---");
            System.out.println("1. 옵션 목록 조회");
            System.out.println("2. 옵션 추가");
            System.out.println("3. 옵션 수정");
            System.out.println("4. 옵션 삭제");
            System.out.println("5. 이전 메뉴로 돌아가기");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllOptions();
                    break;
                case "2":
                    addCommonOption();
                    break;
                case "3":
                    updateCommonOption();
                    break;
                case "4":
                    deleteCommonOption();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }

    private void viewAllOptions() {
//        List<Option> optionList = adminService.getAllOptions();
//        if (optionList.isEmpty()) {
//            System.out.println("등록된 옵션이 없습니다.");
//        } else {
//            System.out.println("\n--- 옵션 목록 ---");
//            for (Option option : optionList) {
//                System.out.println(option);
//            }
//            System.out.println("------------------");
//        }
    }

    private void addCommonOption() {
        System.out.println("\n--- 옵션 추가 ---");
        System.out.print("옵션 이름: ");
        String name = scanner.nextLine();
        System.out.print("옵션 상세 설명: ");
        String detail = scanner.nextLine();
        System.out.print("필수 여부 (true/false): ");
        boolean isRequired = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("옵션 종류 (QUANTITY, CHOICE, BOOLEAN, TEXT): ");
        String optionType = scanner.nextLine();

        Integer maxQuantity = null;
        String allowedValues = null;

        if ("QUANTITY".equalsIgnoreCase(optionType)) {
            System.out.print("최대 수량: ");
            try {
                maxQuantity = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("잘못된 숫자 형식입니다.");
                return;
            }
        } else if ("CHOICE".equalsIgnoreCase(optionType)) {
            System.out.print("허용 값 (쉼표로 구분): ");
            allowedValues = scanner.nextLine();
        }

//        if (adminService.addOption(name, detail, optionType, maxQuantity, allowedValues, isRequired)) {
//            System.out.println("옵션이 성공적으로 추가되었습니다.");
//        } else {
//            System.out.println("옵션 추가에 실패했습니다.");
//        }
    }

    private void updateCommonOption() {
        System.out.println("\n--- 옵션 수정 ---");
        System.out.print("수정할 옵션 ID: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 숫자 형식입니다.");
            return;
        }
        Option existingOption =  null; //adminService.getOptionById(id);
        if (existingOption == null) {
            System.out.println("해당 ID의 옵션이 존재하지 않습니다.");
            return;
        }
        System.out.print("새로운 옵션 이름 (" + existingOption.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("새로운 옵션 상세 설명 (" + existingOption.getDetail() + "): ");
        String detail = scanner.nextLine();
        System.out.print("새로운 필수 여부 (" + existingOption.isRequired() + " true/false): ");
        boolean isRequired = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("새로운 옵션 종류 (" + existingOption.getOptionType() + " QUANTITY, CHOICE, BOOLEAN, TEXT): ");
        String optionType = scanner.nextLine();

        Integer maxQuantity = null;
        String allowedValues = null;

        if ("QUANTITY".equalsIgnoreCase(optionType)) {
            System.out.print("새로운 최대 수량 (" + existingOption.getMaxQuantity() + "): ");
            try {
                maxQuantity = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("잘못된 숫자 형식입니다.");
                return;
            }
        } else if ("CHOICE".equalsIgnoreCase(optionType)) {
            System.out.print("새로운 허용 값 (" + existingOption.getAllowedValues() + " 쉼표로 구분): ");
            allowedValues = scanner.nextLine();
        }

//        if (adminService.updateOption(id, name, detail, optionType, maxQuantity, allowedValues, isRequired)) {
//            System.out.println("옵션 정보가 성공적으로 수정되었습니다.");
//        } else {
//            System.out.println("옵션 정보 수정에 실패했습니다.");
//        }
    }

    private void deleteCommonOption() {
        System.out.println("\n--- 옵션 삭제 ---");
        System.out.print("삭제할 옵션 ID: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 숫자 형식입니다.");
            return;
        }

//        if (adminService.deleteOption(id)) {
//            System.out.println("옵션이 성공적으로 삭제되었습니다.");
//        } else {
//            System.out.println("옵션 삭제에 실패했습니다.");
//        }
    }

    private void manageOrders() {
        while (true) {
            System.out.println("\n주문 내역 관리 메뉴:");
            System.out.println("1. 대기 중인 주문 목록 조회");
            System.out.println("2. 제조 완료 목록 조회");
            System.out.println("3. 이전 메뉴로 돌아가기");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewWaitingOrders();
                    break;
                case "2":
                    view제조완료Orders();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }

    private void viewWaitingOrders() {
        List<Order> waitingOrders = ac.viewWaitingOrders();
        if (waitingOrders.isEmpty()) {
            System.out.println("대기 중인 주문이 없습니다.");
        } else {
            System.out.println("\n--- 대기 중인 주문 목록 ---");
            for (int i = 0; i < waitingOrders.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, waitingOrders.get(i));
            }
            System.out.println("-------------------------");
            System.out.print("처리할 주문 번호를 선택하세요 (0: 이전 메뉴): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0 && choice <= waitingOrders.size()) {
                    int orderId = waitingOrders.get(choice - 1).getId();
                    markOrderAs제조완료(orderId);
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 숫자 형식입니다.");
            }
        }
    }

    private void markOrderAs제조완료(int orderId) {
        System.out.println("\n--- 제조 완료 처리 ---");
//        if (adminService.markOrderAs제조완료(orderId)) {
//            System.out.println("주문 #" + orderId + "이(가) 제조 완료 처리되었습니다.");
//        } else {
//            System.out.println("주문 #" + orderId + " 제조 완료 처리에 실패했습니다.");
//        }
    }

    private void view제조완료Orders() {
        List<Order> 제조완료Orders = null; // adminService.get제조완료Orders();
        if (제조완료Orders.isEmpty()) {
            System.out.println("제조 완료된 주문이 없습니다.");
        } else {
            System.out.println("\n--- 제조 완료된 주문 목록 ---");
            for (int i = 0; i < 제조완료Orders.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, 제조완료Orders.get(i));
            }
            System.out.println("-------------------------");
            System.out.print("처리할 주문 번호를 선택하세요 (0: 이전 메뉴): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0 && choice <= 제조완료Orders.size()) {
                    int orderId = 제조완료Orders.get(choice - 1).getId();
                    markOrderAs픽업완료(orderId);
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 숫자 형식입니다.");
            }
        }
    }

    private void markOrderAs픽업완료(int orderId) {
        System.out.println("\n--- 고객 픽업 완료 처리 ---");
//        if (adminService.markOrderAs픽업완료(orderId)) {
//            System.out.println("주문 #" + orderId + "이(가) 고객 픽업 완료 처리되었습니다.");
//        } else {
//            System.out.println("주문 #" + orderId + " 고객 픽업 완료 처리에 실패했습니다.");
//        }
    }
}