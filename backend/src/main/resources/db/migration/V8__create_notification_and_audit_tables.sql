-- V8__create_notification_and_audit_tables.sql
-- Notification and Audit Tables

-- Notification Table
CREATE TABLE notification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    recipient_id BIGINT NOT NULL,
    notification_type VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    related_entity_type VARCHAR(50),
    related_entity_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (recipient_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_notification_recipient (recipient_id),
    INDEX idx_notification_type (notification_type),
    INDEX idx_notification_read (is_read),
    INDEX idx_notification_created (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Notification Preference Table
CREATE TABLE notification_preference (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    notification_type VARCHAR(50) NOT NULL,
    in_app_enabled BOOLEAN DEFAULT TRUE,
    email_enabled BOOLEAN DEFAULT FALSE,
    sms_enabled BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE KEY uk_notification_preference (user_id, notification_type),
    INDEX idx_notification_preference_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Wastage Record Table
CREATE TABLE wastage_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    resource_type VARCHAR(50) NOT NULL,
    blood_unit_id BIGINT,
    platelet_product_id BIGINT,
    hospital_id BIGINT NOT NULL,
    wastage_date DATE NOT NULL,
    reason VARCHAR(100) NOT NULL,
    quantity INT DEFAULT 1,
    authorized_by BIGINT,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (blood_unit_id) REFERENCES blood_unit(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (platelet_product_id) REFERENCES platelet_product(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (authorized_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_wastage_record_hospital (hospital_id),
    INDEX idx_wastage_record_type (resource_type),
    INDEX idx_wastage_record_date (wastage_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Audit Log Table
CREATE TABLE audit_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    institution_id BIGINT,
    hospital_id BIGINT,
    action VARCHAR(100) NOT NULL,
    entity_type VARCHAR(50) NOT NULL,
    entity_id BIGINT,
    details TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (institution_id) REFERENCES institution(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_audit_log_user (user_id),
    INDEX idx_audit_log_institution (institution_id),
    INDEX idx_audit_log_hospital (hospital_id),
    INDEX idx_audit_log_action (action),
    INDEX idx_audit_log_entity (entity_type, entity_id),
    INDEX idx_audit_log_timestamp (timestamp)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
