package com.sii.sii_recruitment_task.Repository;

import com.sii.sii_recruitment_task.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
