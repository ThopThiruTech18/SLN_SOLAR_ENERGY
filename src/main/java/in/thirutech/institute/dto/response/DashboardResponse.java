package in.thirutech.institute.dto.response;

import java.math.BigDecimal;

public class DashboardResponse {
    private Long totalStudents;
    private Long activeStudents;
    private Long totalCourses;
    private Long totalBatches;
    private Long ongoingBatches;
    private Long totalTrainers;
    private BigDecimal totalRevenue;
    private Long totalPayments;

    public DashboardResponse() {
    }

    public DashboardResponse(Long totalStudents, Long activeStudents, Long totalCourses,
            Long totalBatches, Long ongoingBatches, Long totalTrainers,
            BigDecimal totalRevenue, Long totalPayments) {
        this.totalStudents = totalStudents;
        this.activeStudents = activeStudents;
        this.totalCourses = totalCourses;
        this.totalBatches = totalBatches;
        this.ongoingBatches = ongoingBatches;
        this.totalTrainers = totalTrainers;
        this.totalRevenue = totalRevenue;
        this.totalPayments = totalPayments;
    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Long getActiveStudents() {
        return activeStudents;
    }

    public void setActiveStudents(Long activeStudents) {
        this.activeStudents = activeStudents;
    }

    public Long getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(Long totalCourses) {
        this.totalCourses = totalCourses;
    }

    public Long getTotalBatches() {
        return totalBatches;
    }

    public void setTotalBatches(Long totalBatches) {
        this.totalBatches = totalBatches;
    }

    public Long getOngoingBatches() {
        return ongoingBatches;
    }

    public void setOngoingBatches(Long ongoingBatches) {
        this.ongoingBatches = ongoingBatches;
    }

    public Long getTotalTrainers() {
        return totalTrainers;
    }

    public void setTotalTrainers(Long totalTrainers) {
        this.totalTrainers = totalTrainers;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Long totalPayments) {
        this.totalPayments = totalPayments;
    }
}
