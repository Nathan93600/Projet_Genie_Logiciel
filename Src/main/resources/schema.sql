DROP TABLE IF EXISTS file_scans;
DROP TABLE IF EXISTS scanned_files;

CREATE TABLE file_scans (
    scan_id SERIAL PRIMARY KEY,
    max_files INT,
    max_depth INT,
    file_name_filter VARCHAR(255),
    file_type_filter VARCHAR(255),
    scan_date TIMESTAMP,
    execution_time BIGINT
);

CREATE TABLE scanned_files (
    file_id SERIAL PRIMARY KEY,
    scan_id INT,
    file_name VARCHAR(255),
    modification_date TIMESTAMP,
    size BIGINT,
    file_type VARCHAR(255),
    containing_folder VARCHAR(255),
    FOREIGN KEY (scan_id) REFERENCES file_scans(scan_id)
);
