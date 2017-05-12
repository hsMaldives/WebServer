package kr.ac.hansung.maldives.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import kr.ac.hansung.maldives.web.dao.JobRepository;
import kr.ac.hansung.maldives.web.model.Job;

public class JobService {
	@Autowired
	private JobRepository jobRepository;
	
	public Job findOne(int job_idx){
		return jobRepository.findOne(job_idx);
	}
}
