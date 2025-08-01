package com.sanawulai.jobapplication.job;

import java.util.List;

public interface JobService {

    //get all jobs
    List<Job> findAll();

    //creae job
    void createJob(Job job);

    //get job by id
    Job getJobById(long id);
}
