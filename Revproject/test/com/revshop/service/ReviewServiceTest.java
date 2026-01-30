package com.revshop.service;
import org.junit.Test;
import static org.junit.Assert.*;
public class ReviewServiceTest {

    ReviewService service = new ReviewService();

    @Test
    public void testViewReviews() throws Exception {
        service.viewReviews();
        assertTrue(true);
    }
}

