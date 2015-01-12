package org.bodywired.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseRecettes {

	public Document getDoc ( String httpurl ) {
		try {
			URL url = new URL(httpurl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();

			uc.connect();

			String line = null;
			StringBuffer tmp = new StringBuffer();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			while ( ( line = in.readLine() ) != null ) {
				tmp.append(line);
			}
			return Jsoup.parse(String.valueOf(tmp));
		} catch ( Exception ex ) {
			System.err.println("EX : " + httpurl);
			return null;
		}

	}

	public void run () throws Exception {
		long start = System.currentTimeMillis();
		Set <Thread> threads = new LinkedHashSet <Thread>();

		// Récupère la page avec les différentes catégories de recettes
		Document listeDesCategoriesDoc = getDoc("http://www.guide-des-aliments.com/dietetique/recettes_par_categorie.html");

		// Récupère les liens vers les différentes catégories
		Elements categorieElements = listeDesCategoriesDoc.select("td[class=capsule-vert2]")
				.select("a[href]");

		/*
		 * Créer un nouvel objet catégorie avec le nom associé CategorieRecette
		 */
		CategorieRecette categorie = new CategorieRecette();
		categorie.setNom(categorieElements.html());

		for ( int i = 0; i < categorieElements.size(); i++ ) {
			// Récupère la longueur de l'url pointée par une catégorie
			int urlLength = categorieElements.get(0).attr("href").length();

			// Décompose l'url pointée par une catégorie afin de pouvoir
			// naviguer
			// sur toutes les pages de cette catégorie
			String startUrl = (String) categorieElements.get(0).attr("href")
					.subSequence(0, urlLength - 6);
			String endUrl = ".html";
			int page = 1;
			boolean continueParse = true;
			Document listeDesRecettesDoc = getDoc("http://www.guide-des-aliments.com/dietetique/"
					+ startUrl + page + endUrl);

			while ( continueParse == true ) {
				// traiter les recettes de la page
				Elements recettesElements = listeDesRecettesDoc.select("a[href*=recette_]");

				Document recetteDoc = getDoc("http://www.guide-des-aliments.com/dietetique/"
						+ recettesElements.get(0).attr("href"));

				Recette recette = new Recette();

				// Catégories
				// System.out.println("######################\nCATÉGORIES\n#####################");
				Elements categories = recetteDoc.select(".capsule-vert2 + .cellule-vert4").get(4)
						.select("a");
				for ( Element c : categories ) {
					// System.out.println(c.text());
					CategorieRecette cat = new CategorieRecette();
					cat.setNom(c.text());
					// recette.addCategories(cat);
				}
				// System.out.println(recette.getCategories().toString());

				// aliments composants la recette
				// System.out.println("######################\nLIENS ALIMENTS\n#####################");
				// Elements alimentsUrl = recetteDoc.select("a[href*=fiche_]");
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
				Elements aliments = recetteDoc.select(".cellule-vert4 + .cellule-vert1");
				Elements quantite = recetteDoc.select(".cellule-vert4:nth-last-child(3)");
				for ( int a = 0; a < aliments.size(); a++ ) {
					if ( aliments.get(a).select("a[href*=recette_]").size() > 0 ) {
						Recette ingredient = new Recette();
						ingredient.setNom(aliments.get(a).text());
						int qte = Integer.parseInt(quantite.get(a).text()
								.substring(0, quantite.get(a).text().length() - 2));
						// recette.addIngredient(ingredient, qte);
					}
					else {
						Aliment ingredient = new Aliment();
						ingredient.setNom(aliments.get(a).text());
						int qte = Integer.parseInt(quantite.get(a).text()
								.substring(0, quantite.get(a).text().length() - 2));
						// recette.addIngredient(ingredient, qte);
					}
				}
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
				Elements test = recetteDoc.select(".capsule-vert2 + .cellule-vert4");
				Element poids = test.get(test.size() - 1);
				// System.out.println(poids.text());

				// préparation
				// System.out.println("######################\nPRÉPARATION\n#####################");
				Element preparation = recetteDoc.select(".cellule-vert2 p").get(0);
				recette.setPreparation(preparation.text());
				// System.out.println(preparation.text());

				// calories pour la recette (pour 500g)
				// lipides/glucides/protéines -> 1 (bleu) à 17 (rouge) vert de 8
				// à 11

				// PB AVEC Chou-fleur aux croutons

				// System.out.println("######################\nCALORIES\n#####################");
				Elements caloriesImg = recetteDoc.select("img[src*=pic/compteur/Kcal]");
				String calories = "";
				for ( Element c : caloriesImg ) {
					calories += c.attr("src").substring(17, 18);
				}
				Elements caloriesDetail = recetteDoc.select("img[src*=pic/LGP/Jauge]");
				// System.out.println(calories);
				if ( caloriesDetail.size() > 3 ) {
					Element lipide = caloriesDetail.get(0);
					Element glucides = caloriesDetail.get(1);
					Element proteines = caloriesDetail.get(2);
					// System.out.println(lipide.attr("src").substring(13, 15));
					// System.out.println(glucides.attr("src").substring(13,
					// 15));
					// System.out.println(proteines.attr("src").substring(13,
					// 15));
				}
				// Passer page suivante si elle existe
				if ( listeDesRecettesDoc.select("img[src*=page-suivante]").size() > 0 ) {
					page++;
					listeDesRecettesDoc = getDoc("http://www.guide-des-aliments.com/dietetique/"
							+ startUrl + page + endUrl);
				}
				else {
					continueParse = false;
				}
			}

		}
	}

	public static void main ( String[] args ) throws Exception {
		ParseRecettes parser = new ParseRecettes();
		parser.run();
	}

}
