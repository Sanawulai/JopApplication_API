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

    @Override
    public Review getReview(long companyId, long reviewId) {
      List<Review> reviews = reviewRepository.findByCompanyId(companyId);
       return reviews.stream()
               .filter(review ->
                       review.getId().equals(reviewId))
               .findFirst()
               .orElse(null);
    }

    @Override
    public Boolean updateReview(long companyId, long reviewId, Review updatedreview) {
        if ((companyService.getCompanyById(companyId) !=null)){
            updatedreview.setCompany(companyService.getCompanyById(companyId));
            updatedreview.setId(reviewId);
            reviewRepository.save(updatedreview);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(long companyId, long reviewId) {
        if (companyService.getCompanyById(companyId) !=null
        && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company,companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        }else{
            return false;
        }
    }
}
