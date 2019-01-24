package kajitool.web.controller;

import kajitool.web.domain.model.Material;
import kajitool.web.service.material.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/material")
public class MaterialResource {
    private final MaterialService service;
    public MaterialResource(final MaterialService service) {
        this.service = service;
    }
    @GetMapping("/")
    public ResponseEntity<List<Material>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
