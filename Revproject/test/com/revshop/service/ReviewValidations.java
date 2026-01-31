package com.revshop.service;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revshop.dao.ReviewDAO;

public class ReviewValidations {

    @Mock
    private ReviewDAO dao;  

    @InjectMocks
    private ReviewService reviewService; 

    @Before
    public void setUp() {
       
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testReviewProduct_NotPurchased() throws SQLException {
        int userId = 2;

        
        String input = "202\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        
        when(dao.hasPurchased(userId, 202)).thenReturn(false);

        
        reviewService.reviewProduct(userId);

        
        verify(dao, never()).addReview(anyInt(), anyInt(), anyInt(), anyString());
    }

   
}
