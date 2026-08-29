package in.thirutech.institute.mapper;

import in.thirutech.institute.dto.request.BatchRequest;
import in.thirutech.institute.dto.response.BatchResponse;
import in.thirutech.institute.entity.Batch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BatchMapper {

    // courseName maps from batch.course.courseName
    // trainerName maps from batch.trainer.user.fullName
    @Mapping(target = "courseName", source = "course.courseName")
    @Mapping(target = "trainerName", source = "trainer.user.fullName")
    @Mapping(target = "enrolledStudents", expression = "java(batch.getStudentBatches() != null ? batch.getStudentBatches().size() : 0)")
    BatchResponse toResponse(Batch batch);

    // toEntity and updateEntity ignore course/trainer (they are set via IDs in the
    // service)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "trainer", ignore = true)
    @Mapping(target = "studentBatches", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    Batch toEntity(BatchRequest request);

    @Mapping(target = "course", ignore = true)
    @Mapping(target = "trainer", ignore = true)
    @Mapping(target = "studentBatches", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    void updateEntity(@MappingTarget Batch batch, BatchRequest request);
}
