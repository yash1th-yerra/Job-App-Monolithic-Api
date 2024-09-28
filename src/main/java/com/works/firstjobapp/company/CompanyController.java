package com.works.firstjobapp.company;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company!=null){
        return new ResponseEntity<>(company, HttpStatus.OK);

        }
        return new ResponseEntity<>("Can't Found any Company with that id",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company updatedCompany){
        Boolean updated = companyService.updateCompany(id,updatedCompany);
        if(updated){
            return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Can't find",HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        Boolean isDeleted = companyService.deleteCompany(id);
        if(isDeleted){
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Can't Found Company",HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company newCompany){
        try{
            companyService.addCompany(newCompany);
            return new ResponseEntity<>("Company Added Successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went Wrong",HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/reviews/{reviewId")
    public ResponseEntity<?> getReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        return new ResponseEntity<>(null);
    }


}
