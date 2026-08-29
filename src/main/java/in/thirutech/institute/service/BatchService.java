package in.thirutech.institute.service;

import in.thirutech.institute.dto.request.BatchRequest;
import in.thirutech.institute.dto.response.BatchResponse;

import java.util.List;

public interface BatchService {
    BatchResponse createBatch(BatchRequest request);

    BatchResponse updateBatch(Integer id, BatchRequest request);

    BatchResponse getBatchById(Integer id);

    List<BatchResponse> getAllBatches();

    List<BatchResponse> getActiveBatches();

    void deleteBatch(Integer id);
}
