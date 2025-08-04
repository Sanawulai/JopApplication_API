package com.sanawulai.jobapplication.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable long companyId,@RequestBody Review review){

        Boolean isReviewSaved=reviewService.addReview(companyId,review);
        if (isReviewSaved){
            return new ResponseEntity<>("review added successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review not saved",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/reviews/{reviewsId}")
    public ResponseEntity<Review> getReview(@PathVariable long companyId,@PathVariable long reviewsId){
        return new ResponseEntity<>(reviewService.getReview(companyId,reviewsId),HttpStatus.OK);

    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable long companyId,
                                               @PathVariable long reviewId,
                                               @RequestBody Review review) {
        Boolean isReviewUpdated = reviewService.
                updateReview(companyId, reviewId, review);

        if (isReviewUpdated)
            return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
    }






}
