package com.niit.dao;

import java.util.List;

import com.niit.Model.Job;

public interface JobDao {
void saveJob(Job job);

List<Job> showJob();
Job getJobById(int jid);
}
