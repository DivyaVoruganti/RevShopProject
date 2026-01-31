package com.revshop.service;
import java.sql.SQLException;
import java.util.Scanner;
import com.revshop.dao.ReviewDAO;

public class ReviewService {
	private ReviewDAO dao = new ReviewDAO();
    private Scanner sc = new Scanner(System.in);

    public void reviewProduct(int userId) throws SQLException {
        System.out.print("Enter Product ID to review: ");
        int pid = Integer.parseInt(sc.nextLine());

        if (!dao.hasPurchased(userId, pid)) {
            System.out.println("You can review only purchased products.");
            return;
        }

        System.out.print("Rating (1–5): ");
        int rating = Integer.parseInt(sc.nextLine());

        System.out.print("Write review: ");
        String text = sc.nextLine();

        dao.addReview(userId, pid, rating, text);
        System.out.println("Review submitted successfully!");
    }

    public void viewReviews() throws SQLException {
        System.out.print("Enter Product ID: ");
        int pid = Integer.parseInt(sc.nextLine());
        dao.viewProductReviews(pid);
    }

}
