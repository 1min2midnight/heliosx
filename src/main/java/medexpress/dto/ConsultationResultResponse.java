package medexpress.dto;

import java.time.LocalDateTime;

public class ConsultationResultResponse {
    private Long consultationId;
    private Boolean prescriptionRequired;
    private String reason;
    private LocalDateTime submittedAt;

    public ConsultationResultResponse() {
    }

    public ConsultationResultResponse(Long consultationId, Boolean prescriptionRequired, String reason, LocalDateTime submittedAt) {
        this.consultationId = consultationId;
        this.prescriptionRequired = prescriptionRequired;
        this.reason = reason;
        this.submittedAt = submittedAt;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public Boolean getPrescriptionRequired() {
        return prescriptionRequired;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
}
