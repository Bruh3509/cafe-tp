package com.pet.cafe.repository;

import com.pet.cafe.entity.SocketSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocketSessionRepository extends JpaRepository<SocketSession, String> {
}
