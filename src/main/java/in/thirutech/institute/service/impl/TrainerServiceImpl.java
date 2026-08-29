package in.thirutech.institute.service.impl;

import in.thirutech.institute.dto.request.TrainerRequest;
import in.thirutech.institute.dto.response.TrainerResponse;
import in.thirutech.institute.entity.Batch;
import in.thirutech.institute.entity.Trainer;
import in.thirutech.institute.entity.User;
import in.thirutech.institute.exception.ResourceNotFoundException;
import in.thirutech.institute.repository.BatchRepository;
import in.thirutech.institute.repository.TrainerRepository;
import in.thirutech.institute.repository.UserRepository;
import in.thirutech.institute.service.TrainerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final BatchRepository batchRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository, UserRepository userRepository,
            BatchRepository batchRepository) {
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
        this.batchRepository = batchRepository;
    }

    @Override
    @Transactional
    public TrainerResponse createTrainer(TrainerRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setExpertise(request.getExpertise());
        trainer.setSalary(request.getSalary());
        trainer.setJoiningDate(request.getJoiningDate());

        trainer = trainerRepository.save(trainer);
        return toResponse(trainer);
    }

    @Override
    @Transactional
    public TrainerResponse updateTrainer(Integer id, TrainerRequest request) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));

        trainer.setExpertise(request.getExpertise());
        trainer.setSalary(request.getSalary());
        trainer.setJoiningDate(request.getJoiningDate());

        trainer = trainerRepository.save(trainer);
        return toResponse(trainer);
    }

    @Override
    public TrainerResponse getTrainerById(Integer id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));
        return toResponse(trainer);
    }

    @Override
    public List<TrainerResponse> getAllTrainers() {
        return trainerRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainerResponse> getActiveTrainers() {
        return trainerRepository.findByUser_StatusTrue().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteTrainer(Integer id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));

        // 1. Detach trainer from all assigned batches (set trainer to null, keep batch)
        List<Batch> trainerBatches = batchRepository.findByTrainerTrainerId(id);
        for (Batch batch : trainerBatches) {
            batch.setTrainer(null);
            batchRepository.save(batch);
        }

        // 2. Delete the trainer profile
        trainerRepository.deleteById(id);

        // 3. Delete the linked user account
        if (trainer.getUser() != null) {
            userRepository.deleteById(trainer.getUser().getUserId());
        }
    }

    private TrainerResponse toResponse(Trainer trainer) {
        if (trainer == null) {
            return null;
        }
        TrainerResponse response = new TrainerResponse();
        response.setTrainerId(trainer.getTrainerId());
        if (trainer.getUser() != null) {
            response.setUserId(trainer.getUser().getUserId());
            response.setFullName(trainer.getUser().getFullName());
            response.setEmail(trainer.getUser().getEmail());
            response.setPhone(trainer.getUser().getPhone());
            response.setStatus(trainer.getUser().getStatus());
        }
        response.setExpertise(trainer.getExpertise());
        response.setSalary(trainer.getSalary());
        response.setJoiningDate(trainer.getJoiningDate());
        return response;
    }

}
