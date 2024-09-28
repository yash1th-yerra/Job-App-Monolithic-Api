package com.works.firstjobapp.job;

import java.util.List;

public interface JobService {

     List<Job> findAll();

     String createJob(Job job);


    Job findJob(Long id);

    Boolean removeJob(Long id);
    Boolean updateJob(Long id,Job job);
}
