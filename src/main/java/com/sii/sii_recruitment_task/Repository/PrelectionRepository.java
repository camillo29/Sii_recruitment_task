package com.sii.sii_recruitment_task.Repository;

import com.sii.sii_recruitment_task.Model.Prelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrelectionRepository extends JpaRepository<Prelection, Long> {

}
