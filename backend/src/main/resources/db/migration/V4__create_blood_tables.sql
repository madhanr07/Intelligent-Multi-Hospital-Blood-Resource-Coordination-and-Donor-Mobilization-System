-- V4__create_blood_tables.sql
-- Blood Inventory Management Tables

-- Blood Unit Table
CREATE TABLE blood_unit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    unit_number VARCHAR(50) NOT NULL UNIQUE,
    hospital_id BIGINT NOT NULL,
    blood_group VARCHAR(10) NOT NULL,
    rh_factor VARCHAR(10) NOT NULL,
    component VARCHAR(50),
    collection_date DATE NOT NULL,
    expiry_date DATE,
    status VARCHAR(50) DEFAULT 'AVAILABLE',
    donor_id BIGINT,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (donor_id) REFERENCES donor(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_blood_unit_number (unit_number),
    INDEX idx_blood_unit_hospital (hospital_id),
    INDEX idx_blood_unit_blood_group (blood_group),
    INDEX idx_blood_unit_status (status),
    INDEX idx_blood_unit_expiry (expiry_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Blood Request Table
CREATE TABLE blood_request (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    request_number VARCHAR(50) NOT NULL UNIQUE,
    hospital_id BIGINT NOT NULL,
    requested_by BIGINT,
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    urgency VARCHAR(50) DEFAULT 'ROUTINE',
    purpose TEXT,
    status VARCHAR(50) DEFAULT 'PENDING',
    approved_by BIGINT,
    approved_at TIMESTAMP,
    completed_at TIMESTAMP,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (requested_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (approved_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_blood_request_number (request_number),
    INDEX idx_blood_request_hospital (hospital_id),
    INDEX idx_blood_request_status (status),
    INDEX idx_blood_request_date (request_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Blood Request Item Table
CREATE TABLE blood_request_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    blood_request_id BIGINT NOT NULL,
    blood_group VARCHAR(10) NOT NULL,
    rh_factor VARCHAR(10) NOT NULL,
    component VARCHAR(50),
    quantity_requested INT NOT NULL,
    quantity_fulfilled INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (blood_request_id) REFERENCES blood_request(id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_blood_request_item_request (blood_request_id),
    INDEX idx_blood_request_item_blood_group (blood_group)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Blood Reservation Table
CREATE TABLE blood_reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    blood_request_id BIGINT NOT NULL,
    blood_unit_id BIGINT NOT NULL,
    hospital_id BIGINT NOT NULL,
    reserved_by BIGINT,
    reserved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'RESERVED',
    released_by BIGINT,
    released_at TIMESTAMP,
    notes TEXT,
    FOREIGN KEY (blood_request_id) REFERENCES blood_request(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (blood_unit_id) REFERENCES blood_unit(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (reserved_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (released_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_blood_reservation_request (blood_request_id),
    INDEX idx_blood_reservation_unit (blood_unit_id),
    INDEX idx_blood_reservation_hospital (hospital_id),
    INDEX idx_blood_reservation_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Blood Usage Record Table
CREATE TABLE blood_usage_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    blood_unit_id BIGINT NOT NULL,
    hospital_id BIGINT NOT NULL,
    usage_date DATE NOT NULL,
    used_by BIGINT,
    purpose TEXT,
    patient_reference VARCHAR(100),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (blood_unit_id) REFERENCES blood_unit(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (used_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_blood_usage_unit (blood_unit_id),
    INDEX idx_blood_usage_hospital (hospital_id),
    INDEX idx_blood_usage_date (usage_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
