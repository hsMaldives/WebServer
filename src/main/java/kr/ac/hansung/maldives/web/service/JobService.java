package kr.ac.hansung.maldives.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao.JobRepository;
import kr.ac.hansung.maldives.web.model.Job;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	public Job findOne(int job_idx){
		return jobRepository.findOne(job_idx);
	}
	
	public List<Job> findAll(){
		return jobRepository.findAll();
	}
}
