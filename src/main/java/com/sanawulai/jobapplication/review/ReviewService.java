package com.sanawulai.jobapplication.review;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    List<Review> getAllReviews(long companyId);

    Boolean addReview(long companyId,Review review);
}
