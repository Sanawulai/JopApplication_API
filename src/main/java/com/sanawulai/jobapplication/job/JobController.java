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
@RequestMapping("jobs")
public class JobController {
  private JobService jobService;

  public JobController(JobService jobService){
       this.jobService = jobService;
   }


    //get a list of all jobs
    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    //create a new job
    @PostMapping
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

//delete job by id
@DeleteMapping("/{id}")
public ResponseEntity<String> deleteJob(@PathVariable long id) {
    boolean deleted= jobService.deleteJobById(id);
    if (deleted) {
        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);

    }
    return new ResponseEntity<>("Job doesn't exist", HttpStatus.NOT_FOUND);
}


//update job by {id}
  //  @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT) another way of doing this
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable long id, @RequestBody Job updatedJob){
      boolean updated = jobService.updateJobById(id,updatedJob);
      if (updated){
          return new  ResponseEntity<>("Job updated successfully",HttpStatus.OK);

      }
      return new ResponseEntity<>("Job doesn't exist",HttpStatus.NOT_FOUND);

    }



}