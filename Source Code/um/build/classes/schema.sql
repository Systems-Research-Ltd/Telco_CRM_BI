-- Table: reports

-- DROP TABLE reports;

CREATE TABLE reports
(
  report_id serial NOT NULL,
  report_column character varying(255) NOT NULL,
  report_title character varying(100) NOT NULL,
  report_description text,
  CONSTRAINT report_pk PRIMARY KEY (report_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE reports
  OWNER TO root;


-- Sequence: reports_report_id_seq

-- DROP SEQUENCE reports_report_id_seq;

CREATE SEQUENCE reports_report_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE reports_report_id_seq
  OWNER TO root;