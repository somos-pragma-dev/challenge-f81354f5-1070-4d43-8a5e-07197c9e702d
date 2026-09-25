package com.pragma.loanmanagement.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface NotificationService {
    void sendLoanApprovedNotification(Loan loan);
    void sendLoanRejectedNotification(Loan loan);
    void sendPaymentReminder(Loan loan, int daysUntilDue);
    void sendOverdueNotification(Loan loan);
    void sendLoanCreatedNotification(Loan loan);
    void sendSmsNotification(String phoneNumber, String message);
    void sendEmailNotification(String email, String subject, String body);
    void sendPushNotification(String userId, String title, String message);
    void sendBulkNotification(List<String> recipients, String message);
    Map<String, Object> getNotificationStatus(String notificationId);
    void retryFailedNotification(String notificationId);
    List<Map<String, Object>> getNotificationHistory(String recipientId);
    void scheduleNotification(Loan loan, String notificationType, LocalDateTime scheduledTime);
    void cancelScheduledNotification(String notificationId);
    boolean isNotificationServiceAvailable();
    int getPendingNotificationCount();
    void markNotificationAsRead(String notificationId);
}