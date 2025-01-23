package com.haswanthfullstacktaskmanager.fullstack_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haswanthfullstacktaskmanager.fullstack_backend.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
