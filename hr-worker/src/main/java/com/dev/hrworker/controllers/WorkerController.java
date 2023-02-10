package com.dev.hrworker.controllers;

import com.dev.hrworker.entities.Worker;
import com.dev.hrworker.services.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerController {
    @Value("${test.config}")
    private String testConfig;
    private static Logger logger = LoggerFactory.getLogger(WorkerController.class);
    @Autowired
    private Environment env;
    @Autowired
    private WorkerService workerService;
    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs() {
        logger.info("CONFIG = " + testConfig);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> list = workerService.findAll();
        if(list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<Worker>>(list, HttpStatus.OK);
        }    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {

		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        logger.info("PORT = " + env.getProperty("local.server.port"));
        Optional<Worker> obj = workerService.findById(id);
        if(!obj.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<Worker>(obj.get(), HttpStatus.OK);
        }

    }
}
