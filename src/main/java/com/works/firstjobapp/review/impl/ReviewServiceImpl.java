package com.works.firstjobapp.review.impl;

import com.works.firstjobapp.company.Company;
import com.works.firstjobapp.company.CompanyService;
import com.works.firstjobapp.company.impl.CompanyServiceImpl;
import com.works.firstjobapp.review.Review;
import com.works.firstjobapp.review.ReviewRepository;
import com.works.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

//    public ReviewServiceImpl() {
////        reviewRepository = null;
//    }

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;



    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews =getAllReviews(companyId);

        return reviews.stream().
                filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Boolean updateReview(Long companyId, Long reviewId, Review review) {
        if(companyService.getCompanyById(companyId)!=null){
            review.setCompany(companyService.getCompanyById(companyId));
            review.setId(reviewId);
            reviewRepository.save(review);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean deleteReview(Long companyId, Long reviewId) {

        if((companyService.getCompanyById(companyId)!=null) && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(companyId,company);
            reviewRepository.deleteById(reviewId);
            return true;

        }
        else{
            return false;
        }


//        return null;
    }
}
