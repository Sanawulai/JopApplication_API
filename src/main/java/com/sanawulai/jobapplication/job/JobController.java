package com.sanawulai.jobapplication.job;

import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
  private JobService jobService;

  public JobController(JobService jobService){
       this.jobService = jobService;
   }


    //get a list of all jobs
    @GetMapping("/jobs")
    public List<Job> findAll(){
        return jobService.findAll();
    }

    //create a new job
    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job){
        jobService.createJob(job);
        return "Job added successfully";
    }

    //get job by id
    @GetMapping("/jobs/{id}")
    public Job getJobById(@PathVariable long id){
      Job job = jobService.getJobById(id);
      if (job != null){
          return job;
      }
      return new Job(1L,"TestJob","TestJob Description",
              "1000","2000","Adenta");


    }

}
