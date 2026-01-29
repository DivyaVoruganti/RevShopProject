package com.revshop.service;
import java.sql.SQLException;
import java.util.Scanner;
import com.revshop.dao.PaymentDAO;
public class PaymentService {
	private PaymentDAO dao = new PaymentDAO();
    private Scanner sc = new Scanner(System.in);

    public void makePayment(int userId) throws SQLException {

        int orderId = dao.getLatestOrderId(userId);

        if (orderId == 0) {
            System.out.println("No orders found to make payment.");
            return;
        }

        System.out.println("\n===== PAYMENT =====");
        System.out.println("1. Credit/Debit Card");
        System.out.println("2. UPI");
        System.out.println("3. Cash on Delivery");
        System.out.print("Choose payment method: ");

        int choice = Integer.parseInt(sc.nextLine());
        String method = "";

        switch (choice) {
            case 1:
                method = "CARD";
                System.out.print("Enter Card Number: ");
                sc.nextLine();
                System.out.println("Processing card payment...");
                break;

            case 2:
                method = "UPI";
                System.out.print("Enter UPI ID: ");
                sc.nextLine();
                System.out.println("Processing UPI payment...");
                break;

            case 3:
                method = "COD";
                System.out.println("Cash on Delivery selected.");
                break;

            default:
                System.out.println("Invalid payment option.");
                return;
        }

        dao.updatePayment(orderId, method);
        System.out.println("Payment successful!");
        System.out.println("Order ID " + orderId + " marked as PAID.");
    }

}
