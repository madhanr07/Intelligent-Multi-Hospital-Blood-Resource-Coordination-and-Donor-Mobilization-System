-- V6__create_transfer_tables.sql
-- Inter-Hospital Resource Transfer Tables

-- Resource Transfer Table
CREATE TABLE resource_transfer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transfer_number VARCHAR(50) NOT NULL UNIQUE,
    sending_hospital_id BIGINT NOT NULL,
    receiving_hospital_id BIGINT NOT NULL,
    resource_type VARCHAR(50) NOT NULL,
    requested_by BIGINT,
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'REQUESTED',
    approved_by BIGINT,
    approved_at TIMESTAMP,
    dispatched_by BIGINT,
    dispatched_at TIMESTAMP,
    received_by BIGINT,
    received_at TIMESTAMP,
    completed_at TIMESTAMP,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (sending_hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (receiving_hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (requested_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (approved_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (dispatched_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (received_by) REFERENCES user(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_resource_transfer_number (transfer_number),
    INDEX idx_resource_transfer_sending (sending_hospital_id),
    INDEX idx_resource_transfer_receiving (receiving_hospital_id),
    INDEX idx_resource_transfer_status (status),
    INDEX idx_resource_transfer_type (resource_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Resource Transfer Item Table
CREATE TABLE resource_transfer_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    resource_transfer_id BIGINT NOT NULL,
    resource_type VARCHAR(50) NOT NULL,
    blood_unit_id BIGINT,
    platelet_product_id BIGINT,
    blood_reservation_id BIGINT,
    platelet_reservation_id BIGINT,
    quantity INT DEFAULT 1,
    notes TEXT,
    FOREIGN KEY (resource_transfer_id) REFERENCES resource_transfer(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (blood_unit_id) REFERENCES blood_unit(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (platelet_product_id) REFERENCES platelet_product(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (blood_reservation_id) REFERENCES blood_reservation(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (platelet_reservation_id) REFERENCES platelet_reservation(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_resource_transfer_item_transfer (resource_transfer_id),
    INDEX idx_resource_transfer_item_type (resource_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
