package com.works.firstjobapp.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/reviews")
    public ResponseEntity<?> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }


    @PostMapping("/reviews")
    public ResponseEntity<?> addReview(@PathVariable Long companyId,@RequestBody Review review){
        Boolean isAdded =   reviewService.addReview(companyId,review);
        if(isAdded){
            return new ResponseEntity<>("Review Added Successfully",HttpStatus.CREATED);


        }
        else{

        return new ResponseEntity<>("Company Not Found",HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<?> getReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        if(reviewService.getReview(companyId,reviewId)!=null){

        return new ResponseEntity<>(reviewService.getReview(companyId,reviewId),HttpStatus.OK);
        }
        return new ResponseEntity<>("No Review Available",HttpStatus.NOT_FOUND);
    }


    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review review){

        Boolean isReviewUpdated=reviewService.updateReview(companyId,reviewId,review);

        if(isReviewUpdated){
            return new ResponseEntity<>("Review Updated successfully",HttpStatus.OK);
        }
        else {

        return new ResponseEntity<>("Review Not updated",HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        Boolean isReviewDeleted = reviewService.deleteReview(companyId,reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>(  "Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("No review Available to delete",HttpStatus.REQUEST_TIMEOUT);
    }



}
