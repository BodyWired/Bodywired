--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.1
-- Started on 2015-02-12 08:47:54

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 8 (class 2615 OID 93828)
-- Name: recette; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA recette;


ALTER SCHEMA recette OWNER TO postgres;

SET search_path = recette, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 198 (class 1259 OID 94003)
-- Name: categorie; Type: TABLE; Schema: recette; Owner: postgres; Tablespace: 
--

CREATE TABLE categorie (
    cat_id integer NOT NULL,
    cat_nom character varying(255) NOT NULL,
    cat_href character varying(255)
);


ALTER TABLE recette.categorie OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 94001)
-- Name: categorie_cat_id_seq; Type: SEQUENCE; Schema: recette; Owner: postgres
--

CREATE SEQUENCE categorie_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE recette.categorie_cat_id_seq OWNER TO postgres;

--
-- TOC entry 2054 (class 0 OID 0)
-- Dependencies: 197
-- Name: categorie_cat_id_seq; Type: SEQUENCE OWNED BY; Schema: recette; Owner: postgres
--

ALTER SEQUENCE categorie_cat_id_seq OWNED BY categorie.cat_id;


--
-- TOC entry 203 (class 1259 OID 94046)
-- Name: categorie_recette; Type: TABLE; Schema: recette; Owner: postgres; Tablespace: 
--

CREATE TABLE categorie_recette (
    car_id_cat integer NOT NULL,
    car_id_rec integer NOT NULL
);


ALTER TABLE recette.categorie_recette OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 94025)
-- Name: ingredient; Type: TABLE; Schema: recette; Owner: postgres; Tablespace: 
--

CREATE TABLE ingredient (
    ing_id integer NOT NULL,
    ing_type character varying(30) NOT NULL,
    ing_quantite integer,
    ing_id_ali integer,
    ing_id_rec integer,
    ing_id_concernee_rec integer
);


ALTER TABLE recette.ingredient OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 94023)
-- Name: ingredient_ing_id_seq; Type: SEQUENCE; Schema: recette; Owner: postgres
--

CREATE SEQUENCE ingredient_ing_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE recette.ingredient_ing_id_seq OWNER TO postgres;

--
-- TOC entry 2055 (class 0 OID 0)
-- Dependencies: 201
-- Name: ingredient_ing_id_seq; Type: SEQUENCE OWNED BY; Schema: recette; Owner: postgres
--

ALTER SEQUENCE ingredient_ing_id_seq OWNED BY ingredient.ing_id;


--
-- TOC entry 200 (class 1259 OID 94014)
-- Name: recette; Type: TABLE; Schema: recette; Owner: postgres; Tablespace: 
--

CREATE TABLE recette (
    rec_id integer NOT NULL,
    rec_nom character varying(255) NOT NULL,
    rec_note integer,
    rec_tmp_preparation integer,
    rec_tmp_cuisson integer,
    rec_tmp_refrigeration integer,
    rec_tmp_maceration integer,
    rec_preparation text,
    rec_calories integer,
    rec_href character varying(255)
);


ALTER TABLE recette.recette OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 94012)
-- Name: recette_rec_id_seq; Type: SEQUENCE; Schema: recette; Owner: postgres
--

CREATE SEQUENCE recette_rec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE recette.recette_rec_id_seq OWNER TO postgres;

--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 199
-- Name: recette_rec_id_seq; Type: SEQUENCE OWNED BY; Schema: recette; Owner: postgres
--

ALTER SEQUENCE recette_rec_id_seq OWNED BY recette.rec_id;


--
-- TOC entry 1920 (class 2604 OID 94006)
-- Name: cat_id; Type: DEFAULT; Schema: recette; Owner: postgres
--

ALTER TABLE ONLY categorie ALTER COLUMN cat_id SET DEFAULT nextval('categorie_cat_id_seq'::regclass);


--
-- TOC entry 1922 (class 2604 OID 94028)
-- Name: ing_id; Type: DEFAULT; Schema: recette; Owner: postgres
--

ALTER TABLE ONLY ingredient ALTER COLUMN ing_id SET DEFAULT nextval('ingredient_ing_id_seq'::regclass);


--
-- TOC entry 1921 (class 2604 OID 94017)
-- Name: rec_id; Type: DEFAULT; Schema: recette; Owner: postgres
--

ALTER TABLE ONLY recette ALTER COLUMN rec_id SET DEFAULT nextval('recette_rec_id_seq'::regclass);


-- Completed on 2015-02-12 08:47:54

--
-- PostgreSQL database dump complete
--

