package com.sii.sii_recruitment_task.Repository;

import com.sii.sii_recruitment_task.Model.Prelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface PrelectionRepository extends JpaRepository<Prelection, Long> {
    List<Prelection> findAllByTopic(String topic);
    List<Prelection> findAllByStartHour(Time hour);
}
