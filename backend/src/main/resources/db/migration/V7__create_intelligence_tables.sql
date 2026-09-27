-- V7__create_intelligence_tables.sql
-- Blood Intelligence and ML Tables

-- Blood Demand Record Table
CREATE TABLE blood_demand_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hospital_id BIGINT NOT NULL,
    record_date DATE NOT NULL,
    blood_group VARCHAR(10) NOT NULL,
    rh_factor VARCHAR(10) NOT NULL,
    component VARCHAR(50),
    quantity_requested INT NOT NULL,
    quantity_issued INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    INDEX idx_blood_demand_hospital (hospital_id),
    INDEX idx_blood_demand_date (record_date),
    INDEX idx_blood_demand_blood_group (blood_group)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Prediction Table
CREATE TABLE prediction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hospital_id BIGINT NOT NULL,
    prediction_type VARCHAR(50) NOT NULL,
    blood_group VARCHAR(10),
    rh_factor VARCHAR(10),
    component VARCHAR(50),
    prediction_period_start DATE NOT NULL,
    prediction_period_end DATE NOT NULL,
    predicted_demand INT NOT NULL,
    actual_demand INT,
    model_version VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    INDEX idx_prediction_hospital (hospital_id),
    INDEX idx_prediction_type (prediction_type),
    INDEX idx_prediction_period (prediction_period_start, prediction_period_end)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Risk Assessment Table
CREATE TABLE risk_assessment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hospital_id BIGINT NOT NULL,
    resource_type VARCHAR(50) NOT NULL,
    blood_group VARCHAR(10),
    rh_factor VARCHAR(10),
    assessment_period_start DATE NOT NULL,
    assessment_period_end DATE NOT NULL,
    risk_level VARCHAR(50) NOT NULL,
    risk_score DECIMAL(10,2),
    prediction_id BIGINT,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (prediction_id) REFERENCES prediction(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_risk_assessment_hospital (hospital_id),
    INDEX idx_risk_assessment_type (resource_type),
    INDEX idx_risk_assessment_level (risk_level),
    INDEX idx_risk_assessment_period (assessment_period_start, assessment_period_end)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
