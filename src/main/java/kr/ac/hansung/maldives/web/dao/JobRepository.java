package kr.ac.hansung.maldives.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.hansung.maldives.web.model.Job;

public interface JobRepository  extends JpaRepository<Job, Integer>{

}
