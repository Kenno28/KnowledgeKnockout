package com.knowledgeknockout.kk.interfaces;

import com.knowledgeknockout.kk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
