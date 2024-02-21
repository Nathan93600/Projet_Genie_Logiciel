-- Suppression des données existantes pour garantir l'indépendance des tests
DELETE FROM scanned_files;
DELETE FROM file_scans;

-- Insertion de données de test pour file_scans
INSERT INTO file_scans (max_files, max_depth, file_name_filter, file_type_filter, scan_date, execution_time) VALUES
(10, 2, '%Test%', 'txt', CURRENT_TIMESTAMP, 5000),
(20, 3, '%Data%', 'log', CURRENT_TIMESTAMP, 15000);

-- Insertion de données de test pour scanned_files
-- Supposant que les scan_id générés ci-dessus sont 1 et 2 respectivement
INSERT INTO scanned_files (scan_id, file_name, modification_date, size, file_type, containing_folder) VALUES
(1, 'TestFile1.txt', CURRENT_TIMESTAMP, 2048, 'txt', '/test/path/one'),
(1, 'TestFile2.txt', CURRENT_TIMESTAMP, 1024, 'txt', '/test/path/two'),
(2, 'DataLog1.log', CURRENT_TIMESTAMP, 4096, 'log', '/data/path/one'),
(2, 'DataLog2.log', CURRENT_TIMESTAMP, 5120, 'log', '/data/path/two');
