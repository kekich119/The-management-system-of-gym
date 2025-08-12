CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name TEXT NOT NULL,
                       last_name TEXT NOT NULL,
                       date_birth DATE NOT NULL,
                       special_code INTEGER,
                       email VARCHAR(254) NOT NULL UNIQUE,
                       password VARCHAR(256) NOT NULL,
                       date_subscription_start DATE,
                       date_subscription_finish DATE,
                       pay_for INTEGER,
                       active BOOLEAN
);