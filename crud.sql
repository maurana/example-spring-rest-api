--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4 (Debian 16.4-1)
-- Dumped by pg_dump version 16.4 (Debian 16.4-1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: nationality; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nationality (
    n_id character varying(255) NOT NULL,
    n_desc character varying(255),
    n_name character varying(255) NOT NULL
);


ALTER TABLE public.nationality OWNER TO postgres;

--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    p_id character varying(255) NOT NULL,
    p_age integer,
    p_birthday timestamp(6) without time zone,
    p_created timestamp(6) without time zone NOT NULL,
    p_email character varying(255),
    p_gender character varying(255),
    p_income numeric(38,2),
    p_name character varying(255),
    p_status boolean,
    p_updated timestamp(6) without time zone,
    p_n_id character varying(255) NOT NULL,
    CONSTRAINT person_p_gender_check CHECK (((p_gender)::text = ANY ((ARRAY['MALE'::character varying, 'FEMALE'::character varying])::text[])))
);


ALTER TABLE public.person OWNER TO postgres;

--
-- Data for Name: nationality; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.nationality (n_id, n_desc, n_name) FROM stdin;
ff80818192aea9350192aeaa2c0d0000	EUROPE	Netherland
ff80818192aea9350192aeaad3630001	AFRICA	Zimbabwe
ff80818192aea9350192aeab7bbe0002	AMERICA	Argentina
ff80818192aea9350192aeabe5a00003	ASIA	Japan
ff80818192aea9350192aeac5cb10004	ASEAN	Indonesia
\.


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person (p_id, p_age, p_birthday, p_created, p_email, p_gender, p_income, p_name, p_status, p_updated, p_n_id) FROM stdin;
ff80818192aeb7de0192aeb862400000	27	2025-09-07 07:00:00	2024-10-21 17:56:12.380619	yukihara@crud.com	FEMALE	6.90	Yukihara Minamoto	t	2024-10-21 17:56:12.380757	ff80818192aea9350192aeabe5a00003
ff80818192aeb7de0192aeb9f0eb0001	30	2025-10-01 07:00:00	2024-10-21 17:57:54.413214	maulana@crud.com	MALE	0.70	Maulana Hasanudin	t	2024-10-21 18:01:36.721279	ff80818192aea9350192aeac5cb10004
\.


--
-- Name: nationality nationality_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nationality
    ADD CONSTRAINT nationality_pkey PRIMARY KEY (n_id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (p_id);


--
-- Name: nationality uk59j2ucdyf9fvp4v6uvujlt6jv; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nationality
    ADD CONSTRAINT uk59j2ucdyf9fvp4v6uvujlt6jv UNIQUE (n_name);


--
-- Name: person uka57bptqlvlp3qsoetclmbq0no; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT uka57bptqlvlp3qsoetclmbq0no UNIQUE (p_email);


--
-- Name: person fk1ekfn658t95lklvm3pwsoxujm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT fk1ekfn658t95lklvm3pwsoxujm FOREIGN KEY (p_n_id) REFERENCES public.nationality(n_id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

