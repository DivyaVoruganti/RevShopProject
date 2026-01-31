package com.revshop.service;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revshop.dao.ReviewDAO;

public class ReviewService {

    private static final Logger logger = Logger.getLogger(ReviewService.class);

    private ReviewDAO dao = new ReviewDAO();
    private Scanner sc = new Scanner(System.in);

    public void reviewProduct(int userId) throws SQLException {

        logger.info("Waiting for product ID for review. UserId: " + userId);
        int pid = Integer.parseInt(sc.nextLine());

        if (!dao.hasPurchased(userId, pid)) {
            logger.warn("User has not purchased product. UserId: " + userId + ", ProductId: " + pid);
            return;
        }

        logger.info("Waiting for rating (1-5). UserId: " + userId + ", ProductId: " + pid);
        int rating = Integer.parseInt(sc.nextLine());

        logger.info("Waiting for review text. UserId: " + userId + ", ProductId: " + pid);
        String text = sc.nextLine();

        dao.addReview(userId, pid, rating, text);

        logger.info("Review submitted successfully. UserId: " + userId + ", ProductId: " + pid);
    }

    public void viewReviews() throws SQLException {

        logger.info("Waiting for product ID to view reviews");
        int pid = Integer.parseInt(sc.nextLine());

        dao.viewProductReviews(pid);
        logger.info("Displayed reviews for ProductId: " + pid);
    }
}
