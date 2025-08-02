package com.sanawulai.jobapplication.job;

import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
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
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    //create a new job
    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.CREATED);
    }

    //get job by id
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable long id){
      Job job = jobService.getJobById(id);
      if (job != null){
          return new  ResponseEntity<>(job,HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

@DeleteMapping("/jobs/{id}")
public ResponseEntity<String> deleteJob(@PathVariable long id) {
    boolean deleted= jobService.deleteJobById(id);
    if (deleted) {
        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);

    }
    return new ResponseEntity<>("Job doesn't exist", HttpStatus.NOT_FOUND);
}
}