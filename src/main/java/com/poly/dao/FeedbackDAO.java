package com.poly.dao;


import com.poly.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackDAO extends JpaRepository<Feedback, Integer>{

}
