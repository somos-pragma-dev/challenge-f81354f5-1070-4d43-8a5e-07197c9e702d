package com.pragma.loanmanagement.domain;

import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SmsNotificationService implements NotificationService {
    
    private static final String SMS_GATEWAY_URL = "https://sms.pragma.com/api/send";
    private static final String DEFAULT_SENDER = "LoanMgr";
    private static final int MAX_SMS_LENGTH = 160;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private final String gatewayUrl;
    private final String senderId;
    private boolean gatewayAvailable;
    private int messagesSent;
    private int failedMessages;
    
    public SmsNotificationService() {
        this.gatewayUrl = SMS_GATEWAY_URL;
        this.senderId = DEFAULT_SENDER;
        this.gatewayAvailable = true;
        this.messagesSent = 0;
        this.failedMessages = 0;
    }
    
    public SmsNotificationService(String gatewayUrl, String senderId) {
        this.gatewayUrl = gatewayUrl != null ? gatewayUrl : SMS_GATEWAY_URL;
        this.senderId = senderId != null ? senderId : DEFAULT_SENDER;
        this.gatewayAvailable = true;
        this.messagesSent = 0;
        this.failedMessages = 0;
    }
    
    @Override
    public void sendLoanApprovedNotification(String customerId, Loan loan) {
        if (!gatewayAvailable) {
            throw new IllegalStateException("SMS gateway is not available");
        }
        
        String phoneNumber = normalizePhoneNumber(customerId);
        String message = buildShortApprovalMessage(loan);
        
        sendSms(phoneNumber, message);
    }
    
    @Override
    public void sendLoanRejectedNotification(String customerId, Loan loan, String reason) {
        if (!gatewayAvailable) {
            throw new IllegalStateException("SMS gateway is not available");
        }
        
        String phoneNumber = normalizePhoneNumber(customerId);
        String shortReason = reason.length() > 50 ? reason.substring(0, 47) + "..." : reason;
        String message = "Loan rejected. Reason: " + shortReason + ". Contact support for details.";
        
        sendSms(phoneNumber, message);
    }
    
    @Override
    public void sendPaymentReminder(String customerId, Loan loan, int daysUntilDue) {
        if (!gatewayAvailable) {
            throw new IllegalStateException("SMS gateway is not available");
        }
        
        String phoneNumber = normalizePhoneNumber(customerId);
        String message = String.format("Reminder: Your loan payment is due in %d days. Amount: $%.2f. Please pay on time to avoid fees.",
            daysUntilDue, loan.calculateTotalPayment());
        
        if (message.length() > MAX_SMS_LENGTH) {
            message = String.format("Reminder: Payment due in %d days. Amount: $%.2f. Log in to portal for details.",
                daysUntilDue, loan.calculateTotalPayment());
        }
        
        sendSms(phoneNumber, message);
    }
    
    @Override
    public void sendOverdueNotification(String customerId, Loan loan, int daysOverdue) {
        if (!gatewayAvailable) {
            throw new IllegalStateException("SMS gateway is not available");
        }
        
        String phoneNumber = normalizePhoneNumber(customerId);
        String urgencyMessage = daysOverdue > 30 ? "URGENT: " : "";
        String message = String.format("%sLoan overdue by %d days. Amount: $%.2f. Contact us NOW to avoid further penalties.",
            urgencyMessage, daysOverdue, loan.calculateTotalPayment());
        
        sendSms(phoneNumber, message);
        
        if (daysOverdue > 60) {
            String followUpMessage = "URGENT: Your loan is severely overdue. Immediate payment required. Call now.";
            sendSms(phoneNumber, followUpMessage);
        }
    }
    
    private void sendSms(String phoneNumber, String message) {
        String messageId = UUID.randomUUID().toString();
        String timestamp = LocalDateTime.now().format(FORMATTER);
        
        if (message.length() > MAX_SMS_LENGTH) {
            int segments = (int) Math.ceil((double) message.length() / MAX_SMS_LENGTH);
            System.out.println("[SMS]" + 
                " | MessageID: " + messageId +
                " | From: " + senderId +
                " | To: " + phoneNumber +
                " | Segments: " + segments +
                " | Timestamp: " + timestamp);
        } else {
            System.out.println("[SMS]" + 
                " | MessageID: " + messageId +
                " | From: " + senderId +
                " | To: " + phoneNumber +
                " | Timestamp: " + timestamp);
        }
        System.out.println("[SMS BODY] " + message);
        
        messagesSent++;
    }
    
    private String normalizePhoneNumber(String customerId) {
        String digits = customerId.replaceAll("[^0-9]", "");
        if (digits.length() == 10) {
            return "+1" + digits;
        } else if (digits.length() == 11 && digits.startsWith("1")) {
            return "+" + digits;
        }
        return "+1" + String.format("%010d", Long.parseLong(customerId.replaceAll("[^0-9]", "")));
    }
    
    private String buildShortApprovalMessage(Loan loan) {
        String amountStr = String.format("$%.0f", loan.getAmount());
        return String.format("Loan approved! Amount: %s. Rate: %.1f%%. Log in to accept.",
            amountStr, loan.getInterestRate());
    }
    
    public void setGatewayAvailable(boolean available) {
        this.gatewayAvailable = available;
    }
    
    public boolean isGatewayAvailable() {
        return gatewayAvailable;
    }
    
    public int getMessagesSent() {
        return messagesSent;
    }
    
    public int getFailedMessages() {
        return failedMessages;
    }
    
    public void resetCounters() {
        this.messagesSent = 0;
        this.failedMessages = 0;
    }
}