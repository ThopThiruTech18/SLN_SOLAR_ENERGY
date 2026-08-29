package in.thirutech.institute.controller;

import in.thirutech.institute.dto.request.BatchRequest;
import in.thirutech.institute.dto.response.BatchResponse;
import in.thirutech.institute.service.BatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping
    public ResponseEntity<BatchResponse> createBatch(@RequestBody BatchRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(batchService.createBatch(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BatchResponse> updateBatch(@PathVariable Integer id, @RequestBody BatchRequest request) {
        return ResponseEntity.ok(batchService.updateBatch(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchResponse> getBatchById(@PathVariable Integer id) {
        return ResponseEntity.ok(batchService.getBatchById(id));
    }

    @GetMapping
    public ResponseEntity<List<BatchResponse>> getAllBatches() {
        return ResponseEntity.ok(batchService.getAllBatches());
    }

    @GetMapping("/ongoing")
    public ResponseEntity<List<BatchResponse>> getOngoingBatches() {
        return ResponseEntity.ok(batchService.getActiveBatches());
    }

    @GetMapping("/active")
    public ResponseEntity<List<BatchResponse>> getActiveBatches() {
        return ResponseEntity.ok(batchService.getActiveBatches());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable Integer id) {
        batchService.deleteBatch(id);
        return ResponseEntity.noContent().build();
    }
}
