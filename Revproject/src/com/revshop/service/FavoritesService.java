package com.revshop.service;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revshop.dao.FavoriteDAO;

public class FavoritesService {

    private static final Logger logger = Logger.getLogger(FavoritesService.class);

    private FavoriteDAO dao = new FavoriteDAO();
    private Scanner sc = new Scanner(System.in);

    public void favoritesMenu(int userId) throws SQLException {

        while (true) {

            logger.info("Favorites Menu displayed for userId: " + userId);
            logger.info("1. Add Product to Favorites");
            logger.info("2. View Favorites");
            logger.info("3. Remove Product from Favorites");
            logger.info("4. Back to Main Menu");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    logger.info("Add to Favorites selected");
                    int addId = sc.nextInt();
                    dao.addToFavorites(userId, addId);
                    logger.info("Product added to favorites. UserId: " + userId + ", ProductId: " + addId);
                    break;

                case 2:
                    logger.info("View Favorites selected");
                    dao.viewFavorites(userId);
                    break;

                case 3:
                    logger.info("Remove from Favorites selected");
                    int removeId = sc.nextInt();
                    dao.removeFromFavorites(userId, removeId);
                    logger.info("Product removed from favorites. UserId: " + userId + ", ProductId: " + removeId);
                    break;

                case 4:
                    logger.info("Exiting Favorites Menu for userId: " + userId);
                    return;

                default:
                    logger.warn("Invalid choice entered in Favorites Menu by userId: " + userId);
            }
        }
    }
}
