INSERT INTO file_scans (max_files, max_depth, file_name_filter, file_type_filter, scan_date, execution_time) VALUES
(100, 5, '%.java%', '', NOW(), 120000);

-- Supposant que le scan_id généré ci-dessus est 1
INSERT INTO scanned_files (scan_id, file_name, modification_date, size, file_type, containing_folder) VALUES
(1, 'ExampleFile.java', NOW(), 1024, 'java', '/path/to/file');
