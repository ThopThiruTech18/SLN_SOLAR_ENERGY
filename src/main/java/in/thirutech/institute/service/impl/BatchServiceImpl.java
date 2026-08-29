package in.thirutech.institute.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.thirutech.institute.dto.request.BatchRequest;
import in.thirutech.institute.dto.response.BatchResponse;
import in.thirutech.institute.entity.Batch;
import in.thirutech.institute.entity.Course;
import in.thirutech.institute.entity.Trainer;
import in.thirutech.institute.enums.BatchStatus;
import in.thirutech.institute.exception.ResourceNotFoundException;
import in.thirutech.institute.mapper.BatchMapper;
import in.thirutech.institute.repository.BatchRepository;
import in.thirutech.institute.repository.CourseRepository;
import in.thirutech.institute.repository.TrainerRepository;
import in.thirutech.institute.service.BatchService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final BatchMapper batchMapper;
    private final CourseRepository courseRepository;
    private final TrainerRepository trainerRepository;

    @Override
    @Transactional
    public BatchResponse createBatch(BatchRequest request) {
        Batch batch = batchMapper.toEntity(request);

        // Set Course by ID if provided
        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Course not found with id: " + request.getCourseId()));
            batch.setCourse(course);
        }

        // Set Trainer by ID if provided
        if (request.getTrainerId() != null) {
            Trainer trainer = trainerRepository.findById(request.getTrainerId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Trainer not found with id: " + request.getTrainerId()));
            batch.setTrainer(trainer);
        }

        batch = batchRepository.save(batch);
        return batchMapper.toResponse(batch);
    }

    @Override
    @Transactional
    public BatchResponse updateBatch(Integer id, BatchRequest request) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));

        batchMapper.updateEntity(batch, request);

        // Update Course by ID if provided
        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Course not found with id: " + request.getCourseId()));
            batch.setCourse(course);
        } else {
            batch.setCourse(null);
        }

        // Update Trainer by ID if provided
        if (request.getTrainerId() != null) {
            Trainer trainer = trainerRepository.findById(request.getTrainerId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Trainer not found with id: " + request.getTrainerId()));
            batch.setTrainer(trainer);
        } else {
            batch.setTrainer(null);
        }

        batch = batchRepository.save(batch);
        return batchMapper.toResponse(batch);
    }

    @Override
    public BatchResponse getBatchById(Integer id) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));
        return batchMapper.toResponse(batch);
    }

    @Override
    public List<BatchResponse> getAllBatches() {
        return batchRepository.findAll().stream()
                .map(batchMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<BatchResponse> getOngoingBatches() {
        return batchRepository.findByStatus(BatchStatus.ONGOING)
                .stream()
                .map(batchMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteBatch(Integer id) {
        if (!batchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Batch not found");
        }
        batchRepository.deleteById(id);
    }

    @Override
    public List<BatchResponse> getActiveBatches() {
        return batchRepository.findByStatus(BatchStatus.ONGOING)
                .stream()
                .map(batchMapper::toResponse)
                .toList();
    }
}
