package com.sanawulai.jobapplication.job;

import java.util.List;

public interface JobService {

    //get all jobs
    List<Job> findAll();

    //create job
    void createJob(Job job);

    //get job by id
    Job getJobById(long id);

    //delete job by id
    boolean deleteJobById(long id);

    boolean updateJobById(long id, Job updatedJob);
}
