package com.revshop.service;
import static org.junit.Assert.*;
import org.junit.Test;
import com.revshop.dao.*;

public class FavoriteServiceTest {
	@Test
	public void testAddToFavorites() throws Exception {
	    FavoriteDAO dao = new FavoriteDAO();
	    dao.addToFavorites(1, 101);
	    assertTrue(true);
	}

}
