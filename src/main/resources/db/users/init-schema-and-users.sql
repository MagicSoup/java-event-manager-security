-- SCHEMA: public
REVOKE ALL ON SCHEMA public FROM public;
-- SCHEMA: security
DROP SCHEMA IF EXISTS security;
CREATE SCHEMA IF NOT EXISTS security AUTHORIZATION "event-manager";

-- User: "event-manager-security-user-r"
CREATE USER "event-manager-security-user-r" WITH PASSWORD 'p@ssw0rd';
GRANT CONNECT ON DATABASE "event-manager" TO "event-manager-security-user-r";
GRANT USAGE ON SCHEMA security TO "event-manager-security-user-r";
GRANT SELECT ON ALL TABLES IN SCHEMA security TO "event-manager-security-user-r";
ALTER DEFAULT PRIVILEGES IN SCHEMA security GRANT SELECT ON TABLES TO "event-manager-security-user-r";

-- User: "event-manager-security-user-rw"
CREATE USER "event-manager-security-user-rw" WITH PASSWORD 'p@ssw0rd';
GRANT CONNECT ON DATABASE "event-manager" TO "event-manager-security-user-rw";
GRANT USAGE, CREATE ON SCHEMA security TO "event-manager-security-user-rw";
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA security TO "event-manager-security-user-rw";
ALTER DEFAULT PRIVILEGES IN SCHEMA security GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO "event-manager-security-user-rw";

-- User: "event-manager-security-user-deploy"
CREATE USER "event-manager-security-user-deploy" WITH PASSWORD 'p@ssw0rd';
GRANT CONNECT ON DATABASE "event-manager" TO "event-manager-security-user-deploy";
GRANT USAGE, CREATE ON SCHEMA security TO "event-manager-security-user-deploy";
GRANT SELECT, INSERT, UPDATE, DELETE, TRUNCATE, REFERENCES, TRIGGER ON ALL TABLES IN SCHEMA security TO "event-manager-security-user-deploy";
ALTER DEFAULT PRIVILEGES IN SCHEMA security GRANT SELECT, INSERT, UPDATE, DELETE, TRUNCATE, REFERENCES, TRIGGER ON TABLES TO "event-manager-security-user-deploy";