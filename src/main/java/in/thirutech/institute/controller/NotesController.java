package in.thirutech.institute.controller;

import in.thirutech.institute.entity.CourseMaterial;
import in.thirutech.institute.entity.Course;
import in.thirutech.institute.repository.CourseMaterialRepository;
import in.thirutech.institute.repository.CourseRepository;
import in.thirutech.institute.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Value("${app.upload.dir:uploads/notes}")
    private String uploadDir;

    private final CourseMaterialRepository materialRepository;
    private final CourseRepository courseRepository;

    public NotesController(CourseMaterialRepository materialRepository,
            CourseRepository courseRepository) {
        this.materialRepository = materialRepository;
        this.courseRepository = courseRepository;
    }

    // ── Upload a note ───────────────────────────────────────────────
    @PostMapping("/upload")
    public ResponseEntity<?> uploadNote(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "courseId", required = false) Integer courseId,
            @RequestParam(value = "title", required = false) String title) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "File is empty"));
        }

        try {
            // Create upload directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate unique filename to avoid conflicts
            String originalName = StringUtils.cleanPath(file.getOriginalFilename());
            String uniqueName = System.currentTimeMillis() + "_" + originalName;
            Path targetPath = uploadPath.resolve(uniqueName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // Save metadata to DB
            CourseMaterial material = new CourseMaterial();
            material.setFileName(title != null && !title.isBlank() ? title : originalName);
            material.setFilePath(uniqueName);
            material.setUploadedAt(LocalDateTime.now());

            if (courseId != null) {
                Course course = courseRepository.findById(courseId).orElse(null);
                material.setCourse(course);
            }

            CourseMaterial saved = materialRepository.save(material);

            Map<String, Object> response = new HashMap<>();
            response.put("materialId", saved.getMaterialId());
            response.put("fileName", saved.getFileName());
            response.put("uploadedAt", saved.getUploadedAt().toString());
            response.put("courseId", courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to upload file: " + e.getMessage()));
        }
    }

    // ── List all notes (optionally filter by courseId) ─────────────
    @GetMapping
    public ResponseEntity<?> getAllNotes(
            @RequestParam(value = "courseId", required = false) Integer courseId) {

        List<CourseMaterial> materials;
        if (courseId != null) {
            materials = materialRepository.findAll().stream()
                    .filter(m -> m.getCourse() != null && m.getCourse().getCourseId().equals(courseId))
                    .collect(Collectors.toList());
        } else {
            materials = materialRepository.findAll();
        }

        List<Map<String, Object>> result = materials.stream().map(m -> {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("materialId", m.getMaterialId());
            map.put("fileName", m.getFileName());
            map.put("filePath", m.getFilePath());
            map.put("uploadedAt", m.getUploadedAt() != null ? m.getUploadedAt().toString() : null);
            map.put("courseId", m.getCourse() != null ? m.getCourse().getCourseId() : null);
            map.put("courseName", m.getCourse() != null ? m.getCourse().getCourseName() : null);
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    // ── Download a note ────────────────────────────────────────────
    @GetMapping("/download/{materialId}")
    public ResponseEntity<Resource> downloadNote(@PathVariable Integer materialId) {
        CourseMaterial material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        try {
            Path filePath = Paths.get(uploadDir).resolve(material.getFilePath()).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + material.getFileName() + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ── Delete a note ───────────────────────────────────────────────
    @DeleteMapping("/{materialId}")
    public ResponseEntity<?> deleteNote(@PathVariable Integer materialId) {
        CourseMaterial material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        // Delete physical file
        try {
            Path filePath = Paths.get(uploadDir).resolve(material.getFilePath()).normalize();
            Files.deleteIfExists(filePath);
        } catch (IOException ignored) {
            // Continue even if file deletion fails
        }

        materialRepository.deleteById(materialId);
        return ResponseEntity.noContent().build();
    }
}
