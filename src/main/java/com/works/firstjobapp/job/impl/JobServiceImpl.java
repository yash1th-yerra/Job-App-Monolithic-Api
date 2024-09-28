package com.works.firstjobapp.job.impl;


import com.works.firstjobapp.job.Job;
import com.works.firstjobapp.job.JobRepository;
import com.works.firstjobapp.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

//    private List<Job> jobs = new ArrayList<>();

    private final JobRepository repo;


    public JobServiceImpl(JobRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Job> findAll() {
        return repo.findAll();
    }

    @Override
    public String createJob(Job job) {
//        jobs.add(job);
        try{
            repo.save(job);
            return "Job Created Successfully";

        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    public Job findJob(Long id) {
//        for(Job job :jobs){
//            if(job.getId().equals(id)){
//                return job;
//            }
//
//        }

        return repo.findById(id).orElse(null);


    }

    @Override
    public Boolean removeJob(Long id) {
//        for(Job job: jobs){
//            if(job.getId().equals(id)){
////                Job x = job ;
//                jobs.remove(job);
//                return Boolean.TRUE;
//            }
//
//        }
        Optional<Job> job = repo.findById(id);
            if(job.isPresent()) {
                repo.deleteById(id);
                return true;
            }

            return false;

        }

//        return Boolean.FALSE;


    @Override
    public Boolean updateJob(Long id, Job jobUp) {
        Optional<Job> jobOptional = repo.findById(id);
        if (jobOptional.isPresent()){
            jobUp.setTitle(jobUp.getTitle());
            jobUp.setDescription(jobUp.getDescription());
            jobUp.setLocation(jobUp.getLocation());
            jobUp.setMinSalary(jobUp.getMinSalary());
            jobUp.setMaxSalary(jobUp.getMaxSalary());
            repo.save(jobUp);
            return true;
        }

        return false;

    }


}
