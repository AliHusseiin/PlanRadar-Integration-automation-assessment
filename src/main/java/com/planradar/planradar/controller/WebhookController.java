package com.planradar.planradar.controller;

import com.planradar.planradar.service.PlanRadarService;
import com.planradar.planradar.service.WebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook/v1")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService webhookService;
    private final PlanRadarService planRadarService;

    @PostMapping("/project-created")
    public ResponseEntity<Void> handleProjectCreated() {
        try {
            webhookService.handleWebhook().subscribe();
            return ResponseEntity.ok().build();

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

//    @GetMapping("/projects")
//    public Mono<?> getProjects() {
//        return planRadarService.getFirstProjectId();
//    }
//
//
//
//    @PostMapping("/createform")
//    public Mono<?> createform() {
//        return planRadarService.createForm();
//    }
//
//    @PostMapping("/adformtoproject")
//    public Mono<?> addForm() {
//        return planRadarService.addFormToProject("jwgxeo", );
//    }

}
