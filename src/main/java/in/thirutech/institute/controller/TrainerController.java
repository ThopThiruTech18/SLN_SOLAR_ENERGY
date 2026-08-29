package in.thirutech.institute.controller;

import in.thirutech.institute.dto.request.TrainerRequest;
import in.thirutech.institute.dto.response.TrainerResponse;
import in.thirutech.institute.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<TrainerResponse> createTrainer(@RequestBody TrainerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trainerService.createTrainer(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerResponse> updateTrainer(@PathVariable Integer id,
            @RequestBody TrainerRequest request) {
        return ResponseEntity.ok(trainerService.updateTrainer(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerResponse> getTrainerById(@PathVariable Integer id) {
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @GetMapping
    public ResponseEntity<List<TrainerResponse>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }

    @GetMapping("/active")
    public ResponseEntity<List<TrainerResponse>> getActiveTrainers() {
        return ResponseEntity.ok(trainerService.getActiveTrainers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Integer id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}
