--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: aliment; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA aliment;


ALTER SCHEMA aliment OWNER TO postgres;

--
-- Name: nutrition; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA nutrition;


ALTER SCHEMA nutrition OWNER TO postgres;

SET search_path = aliment, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: aliment; Type: TABLE; Schema: aliment; Owner: postgres; Tablespace: 
--

CREATE TABLE aliment (
    ali_id integer NOT NULL,
    ali_nom character varying(255) NOT NULL,
    ali_href character varying(255)
);


ALTER TABLE aliment.aliment OWNER TO postgres;

--
-- Name: aliment_ali_id_seq; Type: SEQUENCE; Schema: aliment; Owner: postgres
--

CREATE SEQUENCE aliment_ali_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE aliment.aliment_ali_id_seq OWNER TO postgres;

--
-- Name: aliment_ali_id_seq; Type: SEQUENCE OWNED BY; Schema: aliment; Owner: postgres
--

ALTER SEQUENCE aliment_ali_id_seq OWNED BY aliment.ali_id;


--
-- Name: categorie; Type: TABLE; Schema: aliment; Owner: postgres; Tablespace: 
--

CREATE TABLE categorie (
    cat_id integer NOT NULL,
    cat_nom character varying(255) NOT NULL,
    cat_description character varying(20000),
    cat_href character varying(255)
);


ALTER TABLE aliment.categorie OWNER TO postgres;

--
-- Name: categorie_aliment; Type: TABLE; Schema: aliment; Owner: postgres; Tablespace: 
--

CREATE TABLE categorie_aliment (
    caa_id_cat integer NOT NULL,
    caa_id_ali integer NOT NULL
);


ALTER TABLE aliment.categorie_aliment OWNER TO postgres;

--
-- Name: categorie_cat_id_seq; Type: SEQUENCE; Schema: aliment; Owner: postgres
--

CREATE SEQUENCE categorie_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE aliment.categorie_cat_id_seq OWNER TO postgres;

--
-- Name: categorie_cat_id_seq; Type: SEQUENCE OWNED BY; Schema: aliment; Owner: postgres
--

ALTER SEQUENCE categorie_cat_id_seq OWNED BY categorie.cat_id;


--
-- Name: declinaison; Type: TABLE; Schema: aliment; Owner: postgres; Tablespace: 
--

CREATE TABLE declinaison (
    dec_id integer NOT NULL,
    dec_id_ali integer NOT NULL,
    dec_href character varying(255)
);


ALTER TABLE aliment.declinaison OWNER TO postgres;

--
-- Name: declinaison_dec_id_seq; Type: SEQUENCE; Schema: aliment; Owner: postgres
--

CREATE SEQUENCE declinaison_dec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE aliment.declinaison_dec_id_seq OWNER TO postgres;

--
-- Name: declinaison_dec_id_seq; Type: SEQUENCE OWNED BY; Schema: aliment; Owner: postgres
--

ALTER SEQUENCE declinaison_dec_id_seq OWNED BY declinaison.dec_id;


--
-- Name: etat; Type: TABLE; Schema: aliment; Owner: postgres; Tablespace: 
--

CREATE TABLE etat (
    eta_id integer NOT NULL,
    eta_nom character varying(255) NOT NULL
);


ALTER TABLE aliment.etat OWNER TO postgres;

--
-- Name: etat_declinaison; Type: TABLE; Schema: aliment; Owner: postgres; Tablespace: 
--

CREATE TABLE etat_declinaison (
    ede_id_eta integer NOT NULL,
    ede_id_dec integer NOT NULL
);


ALTER TABLE aliment.etat_declinaison OWNER TO postgres;

--
-- Name: etat_eta_id_seq; Type: SEQUENCE; Schema: aliment; Owner: postgres
--

CREATE SEQUENCE etat_eta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE aliment.etat_eta_id_seq OWNER TO postgres;

--
-- Name: etat_eta_id_seq; Type: SEQUENCE OWNED BY; Schema: aliment; Owner: postgres
--

ALTER SEQUENCE etat_eta_id_seq OWNED BY etat.eta_id;


SET search_path = nutrition, pg_catalog;

--
-- Name: glucide; Type: TABLE; Schema: nutrition; Owner: postgres; Tablespace: 
--

CREATE TABLE glucide (
    glu_id integer NOT NULL,
    glu_fibres_alimentaires double precision DEFAULT 0.0,
    glu_id_nut integer
);


ALTER TABLE nutrition.glucide OWNER TO postgres;

--
-- Name: glucide_glu_id_seq; Type: SEQUENCE; Schema: nutrition; Owner: postgres
--

CREATE SEQUENCE glucide_glu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE nutrition.glucide_glu_id_seq OWNER TO postgres;

--
-- Name: glucide_glu_id_seq; Type: SEQUENCE OWNED BY; Schema: nutrition; Owner: postgres
--

ALTER SEQUENCE glucide_glu_id_seq OWNED BY glucide.glu_id;


--
-- Name: lipide; Type: TABLE; Schema: nutrition; Owner: postgres; Tablespace: 
--

CREATE TABLE lipide (
    lip_id integer NOT NULL,
    lip_id_nut integer,
    lip_gras_sature double precision DEFAULT 0.0,
    lip_gras_monoinsature double precision DEFAULT 0.0,
    lip_gras_polyinsature double precision DEFAULT 0.0
);


ALTER TABLE nutrition.lipide OWNER TO postgres;

--
-- Name: lipide_lip_id_seq; Type: SEQUENCE; Schema: nutrition; Owner: postgres
--

CREATE SEQUENCE lipide_lip_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE nutrition.lipide_lip_id_seq OWNER TO postgres;

--
-- Name: lipide_lip_id_seq; Type: SEQUENCE OWNED BY; Schema: nutrition; Owner: postgres
--

ALTER SEQUENCE lipide_lip_id_seq OWNED BY lipide.lip_id;


--
-- Name: nutriment; Type: TABLE; Schema: nutrition; Owner: postgres; Tablespace: 
--

CREATE TABLE nutriment (
    nut_id integer NOT NULL,
    nut_id_typ integer,
    nut_id_tvi integer,
    nut_id_tmi integer,
    nut_id_toe integer,
    nut_id_dec integer,
    nut_apport double precision DEFAULT 0.0,
    nut_details character varying(255)
);


ALTER TABLE nutrition.nutriment OWNER TO postgres;

--
-- Name: nutriment_nut_id_seq; Type: SEQUENCE; Schema: nutrition; Owner: postgres
--

CREATE SEQUENCE nutriment_nut_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE nutrition.nutriment_nut_id_seq OWNER TO postgres;

--
-- Name: nutriment_nut_id_seq; Type: SEQUENCE OWNED BY; Schema: nutrition; Owner: postgres
--

ALTER SEQUENCE nutriment_nut_id_seq OWNED BY nutriment.nut_id;


--
-- Name: type; Type: TABLE; Schema: nutrition; Owner: postgres; Tablespace: 
--

CREATE TABLE type (
    typ_id integer NOT NULL,
    typ_code character varying(255)
);


ALTER TABLE nutrition.type OWNER TO postgres;

--
-- Name: type_mineral; Type: TABLE; Schema: nutrition; Owner: postgres; Tablespace: 
--

CREATE TABLE type_mineral (
    tmi_id integer NOT NULL,
    tmi_nom character varying(100),
    tmi_code character varying(10)
);


ALTER TABLE nutrition.type_mineral OWNER TO postgres;

--
-- Name: type_mineral_tmi_id_seq; Type: SEQUENCE; Schema: nutrition; Owner: postgres
--

CREATE SEQUENCE type_mineral_tmi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE nutrition.type_mineral_tmi_id_seq OWNER TO postgres;

--
-- Name: type_mineral_tmi_id_seq; Type: SEQUENCE OWNED BY; Schema: nutrition; Owner: postgres
--

ALTER SEQUENCE type_mineral_tmi_id_seq OWNED BY type_mineral.tmi_id;


--
-- Name: type_oligo_element; Type: TABLE; Schema: nutrition; Owner: postgres; Tablespace: 
--

CREATE TABLE type_oligo_element (
    toe_id integer NOT NULL,
    toe_nom character varying(100),
    toe_code character varying(10)
);


ALTER TABLE nutrition.type_oligo_element OWNER TO postgres;

--
-- Name: type_oligo_element_toe_id_seq; Type: SEQUENCE; Schema: nutrition; Owner: postgres
--

CREATE SEQUENCE type_oligo_element_toe_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE nutrition.type_oligo_element_toe_id_seq OWNER TO postgres;

--
-- Name: type_oligo_element_toe_id_seq; Type: SEQUENCE OWNED BY; Schema: nutrition; Owner: postgres
--

ALTER SEQUENCE type_oligo_element_toe_id_seq OWNED BY type_oligo_element.toe_id;


--
-- Name: type_typ_id_seq; Type: SEQUENCE; Schema: nutrition; Owner: postgres
--

CREATE SEQUENCE type_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE nutrition.type_typ_id_seq OWNER TO postgres;

--
-- Name: type_typ_id_seq; Type: SEQUENCE OWNED BY; Schema: nutrition; Owner: postgres
--

ALTER SEQUENCE type_typ_id_seq OWNED BY type.typ_id;


--
-- Name: type_vitamine; Type: TABLE; Schema: nutrition; Owner: postgres; Tablespace: 
--

CREATE TABLE type_vitamine (
    tvi_id integer NOT NULL,
    tvi_code character varying(10)
);


ALTER TABLE nutrition.type_vitamine OWNER TO postgres;

--
-- Name: type_vitamine_tvi_id_seq; Type: SEQUENCE; Schema: nutrition; Owner: postgres
--

CREATE SEQUENCE type_vitamine_tvi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE nutrition.type_vitamine_tvi_id_seq OWNER TO postgres;

--
-- Name: type_vitamine_tvi_id_seq; Type: SEQUENCE OWNED BY; Schema: nutrition; Owner: postgres
--

ALTER SEQUENCE type_vitamine_tvi_id_seq OWNED BY type_vitamine.tvi_id;


SET search_path = aliment, pg_catalog;

--
-- Name: ali_id; Type: DEFAULT; Schema: aliment; Owner: postgres
--

ALTER TABLE ONLY aliment ALTER COLUMN ali_id SET DEFAULT nextval('aliment_ali_id_seq'::regclass);


--
-- Name: cat_id; Type: DEFAULT; Schema: aliment; Owner: postgres
--

ALTER TABLE ONLY categorie ALTER COLUMN cat_id SET DEFAULT nextval('categorie_cat_id_seq'::regclass);


--
-- Name: dec_id; Type: DEFAULT; Schema: aliment; Owner: postgres
--

ALTER TABLE ONLY declinaison ALTER COLUMN dec_id SET DEFAULT nextval('declinaison_dec_id_seq'::regclass);


--
-- Name: eta_id; Type: DEFAULT; Schema: aliment; Owner: postgres
--

ALTER TABLE ONLY etat ALTER COLUMN eta_id SET DEFAULT nextval('etat_eta_id_seq'::regclass);


SET search_path = nutrition, pg_catalog;

--
-- Name: glu_id; Type: DEFAULT; Schema: nutrition; Owner: postgres
--

ALTER TABLE ONLY glucide ALTER COLUMN glu_id SET DEFAULT nextval('glucide_glu_id_seq'::regclass);


--
-- Name: lip_id; Type: DEFAULT; Schema: nutrition; Owner: postgres
--

ALTER TABLE ONLY lipide ALTER COLUMN lip_id SET DEFAULT nextval('lipide_lip_id_seq'::regclass);


--
-- Name: nut_id; Type: DEFAULT; Schema: nutrition; Owner: postgres
--

ALTER TABLE ONLY nutriment ALTER COLUMN nut_id SET DEFAULT nextval('nutriment_nut_id_seq'::regclass);


--
-- Name: typ_id; Type: DEFAULT; Schema: nutrition; Owner: postgres
--

ALTER TABLE ONLY type ALTER COLUMN typ_id SET DEFAULT nextval('type_typ_id_seq'::regclass);


--
-- Name: tmi_id; Type: DEFAULT; Schema: nutrition; Owner: postgres
--

ALTER TABLE ONLY type_mineral ALTER COLUMN tmi_id SET DEFAULT nextval('type_mineral_tmi_id_seq'::regclass);


--
-- Name: toe_id; Type: DEFAULT; Schema: nutrition; Owner: postgres
--

ALTER TABLE ONLY type_oligo_element ALTER COLUMN toe_id SET DEFAULT nextval('type_oligo_element_toe_id_seq'::regclass);


--
-- Name: tvi_id; Type: DEFAULT; Schema: nutrition; Owner: postgres
--

ALTER TABLE ONLY type_vitamine ALTER COLUMN tvi_id SET DEFAULT nextval('type_vitamine_tvi_id_seq'::regclass);


--
-- PostgreSQL database dump complete
--

