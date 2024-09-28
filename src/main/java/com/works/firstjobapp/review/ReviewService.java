package com.works.firstjobapp.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);

    Boolean addReview(Long companyId,Review review);

    Review getReview(Long companyId,Long reviewId);

    Boolean updateReview(Long companyId,Long reviewId,Review review);


    Boolean deleteReview(Long companyId, Long reviewID);
}
