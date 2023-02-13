package com.dev.hrworker.services;

import com.dev.hrworker.entities.Worker;
import com.dev.hrworker.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;
    public Optional<Worker> findById(Long id) {
        return workerRepository.findById(id);
    }
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }
}
