package com.pragma.loanmanagement.domain;

import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailNotificationService implements NotificationService {
    
    private static final String SMTP_HOST = "smtp.pragma.com";
    private static final int SMTP_PORT = 587;
    private static final String SENDER_EMAIL = "noreply@loanmanagement.pragma.com";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private final String smtpHost;
    private final int smtpPort;
    private final String senderEmail;
    private boolean emailServerAvailable;
    
    public EmailNotificationService() {
        this.smtpHost = SMTP_HOST;
        this.smtpPort = SMTP_PORT;
        this.senderEmail = SENDER_EMAIL;
        this.emailServerAvailable = true;
    }
    
    public EmailNotificationService(String smtpHost, int smtpPort, String senderEmail) {
        this.smtpHost = smtpHost != null ? smtpHost : SMTP_HOST;
        this.smtpPort = smtpPort > 0 ? smtpPort : SMTP_PORT;
        this.senderEmail = senderEmail != null ? senderEmail : SENDER_EMAIL;
        this.emailServerAvailable = true;
    }
    
    @Override
    public void sendLoanApprovedNotification(String customerId, Loan loan) {
        if (!emailServerAvailable) {
            throw new IllegalStateException("Email server is not available");
        }
        
        String recipient = customerId + "@pragma.com";
        String subject = "Your Loan Has Been Approved";
        String body = buildApprovalEmailBody(loan);
        
        sendEmail(recipient, subject, body);
    }
    
    @Override
    public void sendLoanRejectedNotification(String customerId, Loan loan, String reason) {
        if (!emailServerAvailable) {
            throw new IllegalStateException("Email server is not available");
        }
        
        String recipient = customerId + "@pragma.com";
        String subject = "Your Loan Application Status";
        String body = buildRejectionEmailBody(loan, reason);
        
        sendEmail(recipient, subject, body);
    }
    
    @Override
    public void sendPaymentReminder(String customerId, Loan loan, int daysUntilDue) {
        if (!emailServerAvailable) {
            throw new IllegalStateException("Email server is not available");
        }
        
        String recipient = customerId + "@pragma.com";
        String subject = "Payment Reminder - " + daysUntilDue + " Days Remaining";
        String body = buildReminderEmailBody(loan, daysUntilDue);
        
        sendEmail(recipient, subject, body);
    }
    
    @Override
    public void sendOverdueNotification(String customerId, Loan loan, int daysOverdue) {
        if (!emailServerAvailable) {
            throw new IllegalStateException("Email server is not available");
        }
        
        String recipient = customerId + "@pragma.com";
        String subject = "URGENT: Loan Payment Overdue";
        String body = buildOverdueEmailBody(loan, daysOverdue);
        
        sendEmail(recipient, subject, body);
        
        if (daysOverdue > 30) {
            sendEmail(recipient, "URGENT: Immediate Action Required", 
                "Your loan is significantly overdue. Please contact us immediately.");
        }
    }
    
    private void sendEmail(String recipient, String subject, String body) {
        String messageId = UUID.randomUUID().toString();
        String timestamp = LocalDateTime.now().format(FORMATTER);
        
        System.out.println("[EMAIL]" + 
            " | MessageID: " + messageId +
            " | From: " + senderEmail +
            " | To: " + recipient +
            " | Subject: " + subject +
            " | Timestamp: " + timestamp);
        System.out.println("[EMAIL BODY]\n" + body);
    }
    
    private String buildApprovalEmailBody(Loan loan) {
        StringBuilder body = new StringBuilder();
        body.append("Dear Customer,\n\n");
        body.append("We are pleased to inform you that your loan application has been APPROVED.\n\n");
        body.append("Loan Details:\n");
        body.append("- Loan ID: ").append(loan.getId()).append("\n");
        body.append("- Amount: $").append(loan.getAmount()).append("\n");
        body.append("- Interest Rate: ").append(loan.getInterestRate()).append("%");
        body.append("\n");
        body.append("- Start Date: ").append(loan.getStartDate()).append("\n");
        body.append("- End Date: ").append(loan.getEndDate()).append("\n");
        body.append("- Purpose: ").append(loan.getPurpose()).append("\n\n");
        body.append("Total Payment: $").append(loan.calculateTotalPayment()).append("\n\n");
        body.append("Thank you for choosing our services.\n\n");
        body.append("Best regards,\n");
        body.append("Loan Management Team");
        return body.toString();
    }
    
    private String buildRejectionEmailBody(Loan loan, String reason) {
        StringBuilder body = new StringBuilder();
        body.append("Dear Customer,\n\n");
        body.append("Thank you for your loan application. After careful review, ");
        body.append("we regret to inform you that your application has been declined.\n\n");
        body.append("Reason: ").append(reason).append("\n\n");
        body.append("If you have any questions, please contact our support team.\n\n");
        body.append("Best regards,\n");
        body.append("Loan Management Team");
        return body.toString();
    }
    
    private String buildReminderEmailBody(Loan loan, int daysUntilDue) {
        StringBuilder body = new StringBuilder();
        body.append("Dear Customer,\n\n");
        body.append("This is a friendly reminder that your loan payment is due in ");
        body.append(daysUntilDue).append(" days.\n\n");
        body.append("Loan Details:\n");
        body.append("- Loan ID: ").append(loan.getId()).append("\n");
        body.append("- Due Date: ").append(loan.getEndDate()).append("\n");
        body.append("- Amount Due: $").append(loan.calculateTotalPayment()).append("\n\n");
        body.append("Please ensure your payment is made on time to avoid any late fees.\n\n");
        body.append("Best regards,\n");
        body.append("Loan Management Team");
        return body.toString();
    }
    
    private String buildOverdueEmailBody(Loan loan, int daysOverdue) {
        StringBuilder body = new StringBuilder();
        body.append("URGENT NOTICE\n\n");
        body.append("Dear Customer,\n\n");
        body.append("Your loan payment is ").append(daysOverdue).append(" days overdue. ");
        body.append("Please make your payment immediately to avoid further penalties.\n\n");
        body.append("Loan Details:\n");
        body.append("- Loan ID: ").append(loan.getId()).append("\n");
        body.append("- Days Overdue: ").append(daysOverdue).append("\n");
        body.append("- Total Amount Due: $").append(loan.calculateTotalPayment()).append("\n\n");
        body.append("Contact us immediately to discuss payment options.\n\n");
        body.append("Regards,\n");
        body.append("Loan Management Team");
        return body.toString();
    }
    
    public void setEmailServerAvailable(boolean available) {
        this.emailServerAvailable = available;
    }
    
    public boolean isEmailServerAvailable() {
        return emailServerAvailable;
    }
}