package com.sanawulai.jobapplication.review.Impl;

import com.sanawulai.jobapplication.company.Company;
import com.sanawulai.jobapplication.company.CompanyService;
import com.sanawulai.jobapplication.review.Review;
import com.sanawulai.jobapplication.review.ReviewRepository;
import com.sanawulai.jobapplication.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Review> getAllReviews(long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Boolean addReview(long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company!=null){
            review.setCompany(company);
            reviewRepository.save(review);

            return true;
        }else {
            return false;
        }
    }
}
