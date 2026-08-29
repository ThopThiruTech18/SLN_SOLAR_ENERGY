package in.thirutech.institute.service;

import in.thirutech.institute.dto.request.TrainerRequest;
import in.thirutech.institute.dto.response.TrainerResponse;

import java.util.List;

public interface TrainerService {
    TrainerResponse createTrainer(TrainerRequest request);

    TrainerResponse updateTrainer(Integer id, TrainerRequest request);

    TrainerResponse getTrainerById(Integer id);

    List<TrainerResponse> getAllTrainers();

    List<TrainerResponse> getActiveTrainers();

    void deleteTrainer(Integer id);
}
