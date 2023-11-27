CREATE TABLE req_log_info
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    trans_id    VARCHAR(50),
    req_path    VARCHAR(128),
    req_start_tsp    TIMESTAMP,
    req_end_tsp    TIMESTAMP,
    query_start_tsp    TIMESTAMP,
    query_end_tsp    TIMESTAMP,
    req_time    VARCHAR(50),
    info VARCHAR(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
