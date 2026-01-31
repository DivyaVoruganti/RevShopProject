package com.revshop.service;
import java.sql.SQLException;
import java.util.Scanner;
import com.revshop.dao.FavoriteDAO;
public class FavoritesService {
	 private FavoriteDAO dao = new FavoriteDAO();
	    private Scanner sc = new Scanner(System.in);

	  
	    public void favoritesMenu(int userId) throws SQLException {
	        while (true) {
	            System.out.println("\n===== FAVORITES MENU =====");
	            System.out.println("1. Add Product to Favorites");
	            System.out.println("2. View Favorites");
	            System.out.println("3. Remove Product from Favorites");
	            System.out.println("4. Back to Main Menu");
	            System.out.print("Choice: ");
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter Product ID to favorite: ");
	                    int addId = sc.nextInt();
	                    dao.addToFavorites(userId, addId);
	                    break;
	                case 2:
	                    dao.viewFavorites(userId);
	                    break;
	                case 3:
	                    System.out.print("Enter Product ID to remove: ");
	                    int removeId = sc.nextInt();
	                    dao.removeFromFavorites(userId, removeId);
	                    break;
	                case 4:
	                    return; 
	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }
	

}
