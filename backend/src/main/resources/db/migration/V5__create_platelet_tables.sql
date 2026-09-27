-- V5__create_platelet_tables.sql
-- Platelet Management Tables

-- Platelet Product Table
CREATE TABLE platelet_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_number VARCHAR(50) NOT NULL UNIQUE,
    hospital_id BIGINT NOT NULL,
    blood_group VARCHAR(10) NOT NULL,
    rh_factor VARCHAR(10) NOT NULL,
    collection_date DATE NOT NULL,
    preparation_date DATE,
    expiry_date DATE NOT NULL,
    status VARCHAR(50) DEFAULT 'AVAILABLE',
    donor_id BIGINT,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (donor_id) REFERENCES donor(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_platelet_product_number (product_number),
    INDEX idx_platelet_product_hospital (hospital_id),
    INDEX idx_platelet_product_blood_group (blood_group),
    INDEX idx_platelet_product_status (status),
    INDEX idx_platelet_product_expiry (expiry_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Platelet Request Table
CREATE TABLE platelet_request (
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
    INDEX idx_platelet_request_number (request_number),
    INDEX idx_platelet_request_hospital (hospital_id),
    INDEX idx_platelet_request_status (status),
    INDEX idx_platelet_request_date (request_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Platelet Request Item Table
CREATE TABLE platelet_request_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    platelet_request_id BIGINT NOT NULL,
    blood_group VARCHAR(10) NOT NULL,
    rh_factor VARCHAR(10) NOT NULL,
    quantity_requested INT NOT NULL,
    quantity_fulfilled INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (platelet_request_id) REFERENCES platelet_request(id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_platelet_request_item_request (platelet_request_id),
    INDEX idx_platelet_request_item_blood_group (blood_group)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Platelet Reservation Table
CREATE TABLE platelet_reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    platelet_request_id BIGINT NOT NULL,
    platelet_product_id BIGINT NOT NULL,
    hospital_id BIGINT NOT NULL,
    reserved_by BIGINT,
    reserved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'RESERVED',
    released_by BIGINT,
    released_at TIMESTAMP,
    notes TEXT,
    FOREIGN KEY (platelet_request_id) REFERENCES platelet_request(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (platelet_product_id) REFERENCES platelet_product(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (reserved_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (released_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_platelet_reservation_request (platelet_request_id),
    INDEX idx_platelet_reservation_product (platelet_product_id),
    INDEX idx_platelet_reservation_hospital (hospital_id),
    INDEX idx_platelet_reservation_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
