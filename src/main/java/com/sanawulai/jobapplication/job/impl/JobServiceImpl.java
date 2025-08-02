package com.sanawulai.jobapplication.job.impl;

import com.sanawulai.jobapplication.job.Job;
import com.sanawulai.jobapplication.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);

    }

    @Override
    public Job getJobById(long id) {
       for (Job job : jobs) {
           if(job.getId()==id){
               return job;
           }
       };
       return null;
    }

    @Override
    public Boolean deleteJobById(long id) {
       Iterator<Job> iterator = jobs.iterator();
       while (iterator.hasNext()){
           Job job = iterator.next();
           if(job.getId()==id){
               iterator.remove();
               return true;
           }
       }
       return false;
    }

}
