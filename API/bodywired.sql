DROP SCHEMA IF EXISTS aliment cascade;
DROP SCHEMA IF EXISTS nutrition cascade;
DROP SCHEMA IF EXISTS recette cascade;

CREATE SCHEMA aliment;
CREATE SCHEMA nutrition;
CREATE SCHEMA recette;

/*
CREATE TABLE aliment.Variete (
	var_id SERIAL PRIMARY KEY,
	var_nom varchar(255) UNIQUE
);

CREATE TABLE aliment.FamilleVariete (
	fmv_id SERIAL PRIMARY KEY,
	fmv_nom varchar(255) UNIQUE
);*/

CREATE TABLE aliment.Categorie (
	cat_id serial PRIMARY KEY,
	cat_nom varchar(255) NOT NULL,
	cat_description varchar(20000)
);

CREATE TABLE aliment.Aliment (
	ali_id serial PRIMARY KEY,
	ali_nom varchar(255) NOT NULL
	/*ali_id_cat integer references aliment.Categorie(cat_id) NOT NULL
	ali_id_var integer references aliment.Variete(var_id),
	ali_id_fmv integer references aliment.FamilleVariete(fmv_id)*/
);

CREATE TABLE aliment.Categorie_Aliment (
	primary key(caa_id_cat, caa_id_ali),
	caa_id_cat integer references aliment.Categorie(cat_id) NOT NULL,
	caa_id_ali integer references aliment.Aliment(ali_id) NOT NULL
);

CREATE TABLE aliment.Etat(
	eta_id serial primary key,
	eta_nom varchar(255) UNIQUE NOT NULL
);

CREATE TABLE aliment.Declinaison(
	dec_id serial primary key,
	dec_id_ali integer references aliment.Aliment(ali_id) NOT NULL
);

CREATE TABLE aliment.Etat_Declinaison(
	primary key(ede_id_dec, ede_id_eta),
	ede_id_eta integer references aliment.Etat(eta_id) NOT NULL,
	ede_id_dec integer references aliment.Declinaison(dec_id) NOT NULL
);



/*
*
*
*/

CREATE TABLE nutrition.Type (
	typ_id serial primary key,
	typ_code varchar(255)
);

INSERT INTO nutrition.type(
            typ_code)
    VALUES ('CAL'), ('CHO'), ('EAU'), ('PRO');

CREATE TABLE nutrition.Type_Vitamine(
	tvi_id serial primary key,
	tvi_code varchar(10)
);

INSERT INTO nutrition.type(
            typ_code)
    VALUES ('VIT');
    
INSERT INTO nutrition.type_vitamine(tvi_code)
    VALUES 
    ('A'), 
    ('B1'), 
    ('B2'), 
    ('B3'), 
    ('B5'), 
    ('B6'), 
    ('B9'), 
    ('B12'), 
    ('C'), 
    ('D'), 
    ('E'),
    ('K');

        
CREATE TABLE nutrition.Type_Mineral(
	tmi_id serial primary key,
	tmi_nom varchar(100),
	tmi_code varchar(10)
);

INSERT INTO nutrition.type_mineral(
            tmi_nom, tmi_code)
    VALUES
    ('Ca', 'calcium'),
    ('Mg', 'magnesium'),
    ('P','phosphore'),
    ('K','potassium'),
    ('Na','sodium');

INSERT INTO nutrition.type(
            typ_code)
    VALUES ('MIN');



CREATE TABLE nutrition.Type_Oligo_Element(
	toe_id serial primary key,
	toe_nom varchar(100),
	toe_code varchar(10)
);

INSERT INTO nutrition.type_oligo_element(
	toe_nom, toe_code)
    VALUES 
    ('Fe', 'fer'),
    ('Zn', 'zinc'),
    ('Cu', 'cuivre'),
    ('Se', 'selenium');

INSERT INTO nutrition.type(
            typ_code)
    VALUES ('OEL');
    
CREATE TABLE nutrition.Nutriment(
	nut_id serial primary key,
	nut_id_typ integer references nutrition.Type(typ_id), 

	nut_id_tvi integer references nutrition.type_vitamine(tvi_id),
	nut_id_tmi integer references nutrition.Type_Mineral(tmi_id),
	nut_id_toe integer references nutrition.type_oligo_element(toe_id),
	
	nut_id_dec integer references aliment.Declinaison(dec_id),
	nut_apport float default 0.0,
	nut_details varchar(255)
);

CREATE TABLE nutrition.Lipide(
	lip_id serial primary key,
	lip_id_nut integer references nutrition.Nutriment(nut_id),
	lip_gras_sature float default 0.0,
	lip_gras_monoinsature float default 0.0,
	lip_gras_polyinsature float default 0.0
);
INSERT INTO nutrition.type(
            typ_code)
    VALUES ('LIP');
    
CREATE TABLE nutrition.Glucide(
	glu_id serial primary key,
	glu_fibres_alimentaires float default 0.0,
	glu_id_nut integer references nutrition.Nutriment(nut_id)
);
INSERT INTO nutrition.type(
            typ_code)
    VALUES ('GLU');
    
/*
CREATE TABLE nutrition.Vitamine(
	vit_id serial primary key,
	vit_type integer references nutrition.Type_Vitamine(tvi_id),
	vit_id_nut integer references nutrition.Nutriment(nut_id)
);
    
CREATE TABLE nutrition.Mineral(
	min_id serial primary key,
	min_type integer references nutrition.Type_Mineral(tmi_id),
	min_id_nut integer references nutrition.Nutriment(nut_id)
);
    
CREATE TABLE nutrition.OligoElement(
	oel_id serial primary key,
	oel_type integer references nutrition.Type_Oligo_Element(toe_id),
	oel_id_nut integer references nutrition.Nutriment(nut_id)
);*/
    
/* RECETTES */
CREATE TABLE recette.Categorie (
	cat_id serial PRIMARY KEY,
	cat_nom varchar(255) NOT NULL
);

CREATE TABLE recette.Recette (
	rec_id serial PRIMARY KEY,
	rec_nom varchar(255) NOT NULL,
	rec_tmp_preparation integer,
	rec_tmp_cuisson integer,
	rec_tmp_refrigeration integer,
	rec_tmp_maceration integer,
	rec_preparation varchar(255),
	rec_calories integer
);

CREATE TABLE recette.Categorie_Recette (
	primary key(car_id_cat, car_id_rec),
	car_id_cat integer references recette.Categorie(cat_id) NOT NULL,
	car_id_rec integer references recette.Recette(rec_id) NOT NULL
);

CREATE TABLE recette.Ingredient_Recette (
	primary key(ir_id_ing, ir_id_rec),
	ir_id_ing integer references recette.Categorie(cat_id) NOT NULL,
	ir_id_rec integer references recette.Recette(rec_id) NOT NULL
);