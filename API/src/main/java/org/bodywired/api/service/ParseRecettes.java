package org.bodywired.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scala.collection.mutable.LinkedHashSet;

@Service
public class ParseRecettes {

	public Document getDoc(String httpurl) {
		try {
			URL url = new URL(httpurl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			// Proxy proxy = new Proxy(Proxy.Type.HTTP, new
			// InetSocketAddress("cache.univ-lille1.fr", 3128));
			// HttpURLConnection uc = (HttpURLConnection)
			// url.openConnection(proxy);
			// uc.connect();

			String line = null;
			StringBuffer tmp = new StringBuffer();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			while ((line = in.readLine()) != null) {
				tmp.append(line);
			}
			return Jsoup.parse(String.valueOf(tmp));
		} catch (Exception ex) {
			System.err.println("EX : " + httpurl);
			return null;
		}

	}

	@Autowired
	private AlimentService alimentService;

	@Autowired
	private RecetteService recetteService;

	@Autowired
	private CategorieService categorieService;

	private static final Logger LOGGER = Logger.getLogger(ParseRecettes.class);

	public void run() throws Exception {

		// Récupère la page avec les différentes catégories de recettes
		Document listeDesCategoriesDoc = getDoc("http://www.guide-des-aliments.com/dietetique/recettes_par_categorie.html");

		// Récupère les liens vers les différentes catégories
		Elements categorieElements = listeDesCategoriesDoc.select("td[class=capsule-vert2]").select("a[href]");

		for (int i = 0; i < categorieElements.size(); i++) {
			/*
			 * Créer un nouvel objet catégorie avec le nom associé
			 * CategorieRecette
			 */
			CategorieRecette categorie = new CategorieRecette();
			categorie.setNom(categorieElements.get(i).html());
			categorieService.sauvegarderCategorieRecette(categorie);
			LOGGER.debug("New CategorieRecette : " + categorie.getNom());

			// Récupère la longueur de l'url pointée par une catégorie
			LOGGER.debug(categorieElements.get(i).attr("href"));
			boolean continueParse = true;
			Document listeDesRecettesDoc = getDoc("http://www.guide-des-aliments.com/dietetique/" + categorieElements.get(i).attr("href"));

			while (continueParse == true) {
				// traiter les recettes de la page
				Elements recettesElements = listeDesRecettesDoc.select("a[href*=recette_]");

				for (Element recetteElement : recettesElements) {

					if (recettesElements.size() > 0) {
						Document recetteDoc = getDoc("http://www.guide-des-aliments.com/dietetique/" + recetteElement.attr("href"));

						// Nouvelle recette
						Recette recette = new Recette();
						recette.setHref("http://www.guide-des-aliments.com/dietetique/" + recetteElement.attr("href"));
						LOGGER.debug(recetteDoc.location());

						// On récupère le nom de la recette
						try {
							recette.setNom(recetteDoc.select(".titre-article div").get(0).html().trim());
						} catch (Exception e) {
							LOGGER.debug(recette.getHref(), e);
							continue;
						}

						// On récupère les temps de préparation et de cuisson de
						// la
						// recette
						Elements capsVert2 = recetteDoc.select(".capsule-vert2");
						Elements cellVert4 = recetteDoc.select(".capsule-vert2 + .cellule-vert4");

						String tmpPrepa = "";
						String tmpCuisson = "";
						String tmpRefrigeration = "";
						String tmpMaceration = "";

						for (int q = 0; q < capsVert2.size(); q++) {
							if (capsVert2.get(q).text().equals("Cuisson :"))
								tmpCuisson = cellVert4.get(q).text().substring(0, cellVert4.get(q).text().length() - 4);
							if (capsVert2.get(q).text().equals("Préparation :"))
								tmpPrepa = cellVert4.get(q).text().substring(0, cellVert4.get(q).text().length() - 4);
							if (capsVert2.get(q).text().equals("Réfrigération :"))
								tmpRefrigeration = cellVert4.get(q).text().substring(0, cellVert4.get(q).text().length() - 4);
							if (capsVert2.get(q).text().equals("Macération :"))
								tmpMaceration = cellVert4.get(q).text().substring(0, cellVert4.get(q).text().length() - 4);
						}

						if (tmpCuisson.equals("")) {
							recette.setTmpCuisson(0);
						} else {
							recette.setTmpCuisson(Integer.parseInt(tmpCuisson));
						}
						if (tmpPrepa.equals("")) {
							recette.setTmpPreparation(0);
						} else {
							recette.setTmpPreparation(Integer.parseInt(tmpPrepa));
						}
						if (tmpRefrigeration.equals("")) {
							recette.setTmpRefrigeration(0);
						} else {
							recette.setTmpRefrigeration(Integer.parseInt(tmpRefrigeration));
						}
						if (tmpMaceration.equals("")) {
							recette.setTmpMaceration(0);
						} else {
							recette.setTmpMaceration(Integer.parseInt(tmpMaceration));
						}

						// Catégories
						// System.out.println("######################\nCATÉGORIES\n#####################");
						/*
						 * Elements categories =
						 * recetteDoc.select(".capsule-vert2 + .cellule-vert4"
						 * ).get(4).select("a"); for (Element c : categories) {
						 * // CategorieRecette cat = new CategorieRecette(); //
						 * cat.setNom(c.text()); CategorieRecette cat =
						 * categorieService
						 * .rechercherCategorieRecette(c.text());
						 * recette.addCategories(cat); }
						 */
						// System.out.println(recette.getCategories().toString());

						// aliments composants la recette
						// System.out.println("######################\nLIENS ALIMENTS\n#####################");
						// Elements alimentsUrl =
						// recetteDoc.select("a[href*=fiche_]");
						// Elements recettesUrl =
						// recetteDoc.select("a[href*=recette_]");
						// for ( Element a : alimentsUrl ) {
						// System.out.println("http://www.guide-des-aliments.com/dietetique/"
						// + a.attr("href"));
						// }
						// for ( Element r : recettesUrl ) {
						// System.out.println("http://www.guide-des-aliments.com/dietetique/"
						// + r.attr("href"));
						// }

						// aliments
						// System.out.println("######################\nALIMENTS\n#####################");

						// System.out.println(recette.getIngredients().toString());
						// for ( Element a : aliments ) {
						// if ( a.select("a[href*=recette_]").size() > 0 ) {
						// System.out.println("recette");
						// // if ( categorie.getRecettes().contains(arg0) )
						// // recette.addIngredient(ingredient, quantite);
						// }
						// else {
						// System.out.println(a.text());
						// }
						// }

						// quantité - la dernière valeur
						// System.out.println("######################\nQUANTITÉ\n#####################");
						// Elements quantite =
						// recetteDoc.select(".cellule-vert4:nth-last-child(3)");
						// for ( int z = 0; z < aliments.size(); z++ ) {
						// System.out.println(quantite.get(z).text());
						// }

						// poids pour 1 portion => dernier de la liste
						// System.out.println("######################\nPOIDS\n#####################");
						// Elements test =
						// recetteDoc.select(".capsule-vert2 + .cellule-vert4");
						// Element poids = test.get(test.size() - 1);
						// System.out.println(poids.text());

						// préparation
						// System.out.println("######################\nPRÉPARATION\n#####################");

						if (recetteDoc.select(".cellule-vert2 p").size() > 0) {
							Element preparation = recetteDoc.select(".cellule-vert2 p").get(0);
							recette.setPreparation(preparation.text());
						}
						// System.out.println(preparation.text());

						// calories pour la recette (pour 500g)
						// lipides/glucides/protéines -> 1 (bleu) à 17 (rouge)
						// vert
						// de 8 à 11

						// PB AVEC Chou-fleur aux croutons

						// System.out.println("######################\nCALORIES\n#####################");

						// On récupère les calories liées à la recette
						Elements caloriesImg = recetteDoc.select("img[src*=pic/compteur/Kcal]");
						String calories = "";
						for (Element c : caloriesImg) {
							if (!(calories.length() <= 0 && c.attr("src").substring(17, 18).equals("0"))) {
								calories += c.attr("src").substring(17, 18);
							}
						}

						if (!calories.equals(""))
							recette.setCalories(Integer.parseInt(calories));

						Elements caloriesDetail = recetteDoc.select("img[src*=pic/LGP/Jauge]");

						// System.out.println(calories);
						if (caloriesDetail.size() > 3) {
							Element lipide = caloriesDetail.get(0);
							Element glucides = caloriesDetail.get(1);
							Element proteines = caloriesDetail.get(2);
							// System.out.println(lipide.attr("src").substring(13,
							// 15));
							// System.out.println(glucides.attr("src").substring(13,
							// 15));
							// System.out.println(proteines.attr("src").substring(13,
							// 15));
						}

						LOGGER.info("New Recette : ");
						LOGGER.info("nom :" + recette.getNom());
						LOGGER.info("preparation : " + recette.getPreparation());
						LOGGER.info("categories : " + recette.getCategories().size());
						LOGGER.info("type : " + recette.getType());
						LOGGER.info("calories : " + recette.getCalories());
						LOGGER.info("temps Preparation : " + recette.getTmpPreparation());
						LOGGER.info("temps Cuisson : " + recette.getTmpCuisson());
						LOGGER.info("temps Refrigreration : " + recette.getTmpRefrigeration());
						LOGGER.info("temps Maceration : " + recette.getTmpMaceration());
						LOGGER.info("aliments : " + recette.getAliments().size());
						LOGGER.info("recettes : " + recette.getRecettes().size());
						LOGGER.info("--");

						// service ajouterRecette(recette);
						// recette.getCategories().add(categorie);
						recetteService.sauvegarderRecette(recette);
					}
				}
				// Passer page suivante si elle existe
				Elements ps = listeDesRecettesDoc.select("img[src*=page-suivante]");
				if (ps.size() > 0) {
					listeDesRecettesDoc = getDoc("http://www.guide-des-aliments.com/dietetique/" + ps.parents().get(0).attr("href"));
				} else {
					continueParse = false;
				}

			}

		}

		// FIN PREMIERE BOUCLE

		LinkedHashSet<String> urlDone = new LinkedHashSet<String>();
		for (int i = 0; i < categorieElements.size(); i++) {
			// Récupère la longueur de l'url pointée par une catégorie
			// Décompose l'url pointée par une catégorie afin de pouvoir
			// naviguer
			// sur toutes les pages de cette catégorie
			boolean continueParse = true;
			Document listeDesRecettesDoc = getDoc("http://www.guide-des-aliments.com/dietetique/" + categorieElements.get(i).attr("href"));

			while (continueParse == true) {
				// traiter les recettes de la page
				Elements recettesElements = listeDesRecettesDoc.select("a[href*=recette_]");

				for (Element recetteElement : recettesElements) {

					Document recetteDoc = getDoc("http://www.guide-des-aliments.com/dietetique/" + recetteElement.attr("href"));
					if (recettesElements.size() > 0 && !urlDone.contains("http://www.guide-des-aliments.com/dietetique/" + recetteElement.attr("href"))) {
						urlDone.add("http://www.guide-des-aliments.com/dietetique/" + recetteElement.attr("href"));
						// On recupere la recette
						// service getRecette(nom);
						Recette recette = null;
						try {
							if (!recetteDoc.select(".titre-entete div").isEmpty())
								recette = recetteService.rechercherRecetteParNom(recetteDoc.select(".titre-entete div").get(0).html().substring(10));
						} catch (Exception e) {
							LOGGER.error(e);
						}
						// On récupère le nom de la recette
						// nom :
						// setNom(recetteDoc.select(".titre-entete div").get(0).html().substring(10));

						// Catégories
						if (recette != null) {
							Elements categories = recetteDoc.select(".capsule-vert2 + .cellule-vert4").get(4).select("a");
							for (Element c : categories) {
								// Recuperer categorie
								CategorieRecette catRecette = categorieService.rechercherCategorieRecette(c.text());
								/*
								 * CategorieRecette cat = new
								 * CategorieRecette(); cat.setNom(c.text());
								 */
								// ajouter categorie
								// recette.addCategories(cat);
								recetteService.ajouterCategorieRecette(catRecette, recette);
								// recette.addCategories(catRecette);

							}

							Elements aliments = recetteDoc.select(".cellule-vert4 + .cellule-vert1");
							Elements quantite = recetteDoc.select(".cellule-vert4:nth-last-child(3)");
							for (int a = 0; a < aliments.size(); a++) {
								String href = aliments.get(a).select("a[href]").attr("href");
								if (href.contains("recette_")) {
									// recuperer ingredient
									/*
									 * Recette ingredient = new Recette();
									 * ingredient
									 * .setNom(aliments.get(a).text());
									 */
									int qte = Integer.parseInt(quantite.get(a).text().substring(0, quantite.get(a).text().length() - 2));
									// ajouter ingredient
									// recette.addIngredient(ingredient, qte);
									// Recette ingredient =
									// recetteService.rechercherRecetteParNom(aliments.get(a).text());
									Recette ingredient = recetteService.rechercherRecetteParHref("http://www.guide-des-aliments.com/dietetique/" + href);
									// recette.addIngredient(ingredient, qte);
									recetteService.ajouterRecetteIngredient(ingredient, qte, recette);

								} else if (href.contains("fiche_")) {
									// recuperer ingredient
									/*
									 * Aliment ingredient = new Aliment();
									 * ingredient
									 * .setNom(aliments.get(a).text());
									 */
									int qte = Integer.parseInt(quantite.get(a).text().substring(0, quantite.get(a).text().length() - 2));

									// ajouter ingredient
									// recette.addIngredient(ingredient, qte);

									// Aliment ingredient =
									// alimentService.getAliment(aliments.get(a).text());
									Aliment ingredient = alimentService.rechercherAlimentParHref("http://www.guide-des-aliments.com/dietetique/" + href);
									// recette.addIngredient(ingredient, qte);
									recetteService.ajouterAlimentIngredient(ingredient, qte, recette);
								}
							}
						}
					}
				}
				// Passer page suivante si elle existe
				Elements ps = listeDesRecettesDoc.select("img[src*=page-suivante]");
				if (ps.size() > 0) {
					listeDesRecettesDoc = getDoc("http://www.guide-des-aliments.com/dietetique/" + ps.parents().get(0).attr("href"));
				} else {
					continueParse = false;
				}

			}
		}

		// FIN DEUXIEME BOUCLE

	}

	public static void main(String[] args) throws Exception {
		ParseRecettes parser = new ParseRecettes();
		parser.run();
	}

}
