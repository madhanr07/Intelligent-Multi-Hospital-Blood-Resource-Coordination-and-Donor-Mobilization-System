-- V3__create_donor_tables.sql
-- Donor Management Tables

-- Donor Table
CREATE TABLE donor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    registration_number VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE,
    gender VARCHAR(20),
    blood_group VARCHAR(10),
    rh_factor VARCHAR(10),
    email VARCHAR(255),
    phone VARCHAR(50),
    address TEXT,
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    preferred_hospital_id BIGINT,
    verification_status VARCHAR(50) DEFAULT 'PENDING',
    registration_status VARCHAR(50) DEFAULT 'ACTIVE',
    last_donation_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (preferred_hospital_id) REFERENCES hospital(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_donor_registration_number (registration_number),
    INDEX idx_donor_blood_group (blood_group),
    INDEX idx_donor_verification_status (verification_status),
    INDEX idx_donor_preferred_hospital (preferred_hospital_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Donor Document Table
CREATE TABLE donor_document (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    donor_id BIGINT NOT NULL,
    document_type VARCHAR(50) NOT NULL,
    document_path VARCHAR(500),
    document_name VARCHAR(255),
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    verified_at TIMESTAMP,
    verification_status VARCHAR(50) DEFAULT 'PENDING',
    FOREIGN KEY (donor_id) REFERENCES donor(id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_donor_document_donor (donor_id),
    INDEX idx_donor_document_type (document_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Donor Verification Table
CREATE TABLE donor_verification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    donor_id BIGINT NOT NULL,
    verified_by BIGINT,
    verification_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    verification_status VARCHAR(50) NOT NULL,
    notes TEXT,
    FOREIGN KEY (donor_id) REFERENCES donor(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (verified_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_donor_verification_donor (donor_id),
    INDEX idx_donor_verification_status (verification_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Donation Table
CREATE TABLE donation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    donor_id BIGINT NOT NULL,
    hospital_id BIGINT NOT NULL,
    donation_date DATE NOT NULL,
    blood_group VARCHAR(10),
    rh_factor VARCHAR(10),
    volume_ml DECIMAL(10,2),
    status VARCHAR(50) DEFAULT 'COMPLETED',
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (donor_id) REFERENCES donor(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    INDEX idx_donation_donor (donor_id),
    INDEX idx_donation_hospital (hospital_id),
    INDEX idx_donation_date (donation_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Appointment Table
CREATE TABLE appointment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    donor_id BIGINT NOT NULL,
    hospital_id BIGINT NOT NULL,
    appointment_date DATETIME NOT NULL,
    status VARCHAR(50) DEFAULT 'SCHEDULED',
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (donor_id) REFERENCES donor(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    INDEX idx_appointment_donor (donor_id),
    INDEX idx_appointment_hospital (hospital_id),
    INDEX idx_appointment_date (appointment_date),
    INDEX idx_appointment_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Donor Response Table
CREATE TABLE donor_response (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    donor_id BIGINT NOT NULL,
    request_type VARCHAR(50) NOT NULL,
    request_reference_id BIGINT,
    response VARCHAR(50) NOT NULL,
    response_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notes TEXT,
    FOREIGN KEY (donor_id) REFERENCES donor(id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_donor_response_donor (donor_id),
    INDEX idx_donor_response_type (request_type),
    INDEX idx_donor_response_date (response_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
