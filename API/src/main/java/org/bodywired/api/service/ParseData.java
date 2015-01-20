package org.bodywired.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.classement.Categorie;
import org.bodywired.api.model.classement.Etat;
import org.bodywired.api.model.nutriment.AbstractNutriment;
import org.bodywired.api.model.nutriment.AbstractNutriment.MIN_TYPE;
import org.bodywired.api.model.nutriment.AbstractNutriment.OEL_TYPE;
import org.bodywired.api.model.nutriment.AbstractNutriment.VIT_TYPE;
import org.bodywired.api.model.nutriment.Calorie;
import org.bodywired.api.model.nutriment.Cholesterol;
import org.bodywired.api.model.nutriment.Eau;
import org.bodywired.api.model.nutriment.Glucide;
import org.bodywired.api.model.nutriment.Lipide;
import org.bodywired.api.model.nutriment.Mineral;
import org.bodywired.api.model.nutriment.OligoElement;
import org.bodywired.api.model.nutriment.Proteine;
import org.bodywired.api.model.nutriment.Vitamine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParseData {

	@Autowired
	private AlimentService alimentService;

	@Autowired
	private DeclinaisonService declinaisonService;

	@Autowired
	private CategorieService categorieService;

	public Document getDoc(String httpurl) {
		try {
			URL url = new URL(httpurl);
			// Proxy proxy = new Proxy(Proxy.Type.HTTP, new
			// InetSocketAddress("cache.univ-lille1.fr", 3128));
			// HttpURLConnection uc = (HttpURLConnection)
			// url.openConnection(proxy);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();

			uc.connect();

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

	public void run() throws Exception {
		// Document doc =
		// Jsoup.connect("http://www.guide-des-aliments.com/dietetique/aliments_par_categorie.html").get();
		// Elements categorieElements =
		// doc.select("td[class=capsule-vert2]").select("a[href]");
		// System.out.println(categorieElements.size());

		// Document doc = Jsoup.connect("http://www.google.com").get();

		long start = System.currentTimeMillis();

		Document listeDesCategoriesDoc = getDoc("http://www.guide-des-aliments.com/dietetique/aliments_par_categorie.html");

		Elements categorieElements = listeDesCategoriesDoc.select("td[class=capsule-vert2]").select("a[href]");

		/* CATEGORIE */
		for (final Element categorieElement : categorieElements) {

			Categorie categorie = new Categorie();
			categorie.setNom(categorieElement.html());

			// ALIMENT
			Document listeDesAlimentsDoc = getDoc("http://www.guide-des-aliments.com/dietetique/" + categorieElement.attr("href"));
			Elements alimentElements = listeDesAlimentsDoc.select("a[href*=fiche_]");

			String desc = listeDesAlimentsDoc.select("td.texte-article div[align=left]").html();
			categorie.setDescription(desc);

			if (alimentElements.isEmpty()) {
				return;
			}

			categorieService.sauvegarderCategorieAliment(categorie);
			for (Element alimentElement : alimentElements) {
				Aliment aliment = null;
				String nom = alimentElement.html();

				aliment = alimentService.getAliment(nom);
				if (aliment != null) {
					categorieService.ajouterAlimentDansCategorie(aliment, categorie);
					continue;
				} else {
					aliment = new Aliment();
					aliment.setNom(nom);
				}

				Document ficheAlimentDoc = getDoc("http://www.guide-des-aliments.com/dietetique/" + alimentElement.attr("href"));
				Elements linkNutrition = ficheAlimentDoc.select("a[href*=fiche_nutrition_]");

				// DECLINAISON
				Document declinaisonsAlimentDoc = getDoc("http://www.guide-des-aliments.com/dietetique/" + linkNutrition.first().attr("href"));
				Elements declinaisonsElements = declinaisonsAlimentDoc.select("a[href^=detail_nutrition_");

				if (declinaisonsElements.isEmpty()) {
					continue;
				}

				alimentService.sauvegarderAliment(aliment);
				categorieService.ajouterAlimentDansCategorie(aliment, categorie);

				for (Element declinaisonElement : declinaisonsElements) {
					String declinaisonName = declinaisonElement.html();
					String[] etats = declinaisonName.split("\\s*,\\s*");

					Declinaison declinaison = new Declinaison();
					declinaison.setAliment(aliment);

					for (int i = 1; i < etats.length; i++) {
						Etat etat = new Etat();
						etat.setNom(etats[i]);
						declinaison.addEtat(etat);
					}

					// NUTRIMENT
					Document nutritionDeclinaisonDoc = null;
					try {
						nutritionDeclinaisonDoc = getDoc("http://www.guide-des-aliments.com/dietetique/" + declinaisonElement.attr("href"));
					} catch (Exception e) { // Quelque fois Problème
											// d'encodage URL (%.())

					} finally {
						if (nutritionDeclinaisonDoc == null)
							continue;
					}

					Elements dataElements = nutritionDeclinaisonDoc.select("td:nth-child(3)").select(".cellule-vert4");

					// = new ( getValue(dataElements, 0));
					// if (.getCalories() == 0) {
					// urlCalAZero.add(getCurrentURL()); }

					Calorie calorie = new Calorie();
					initNurtriment(calorie, dataElements, 0);
					// calorie.setApport(getApportValue(dataElements,
					// 0));
					declinaison.addApportNutriment(calorie);

					Lipide lipide = new Lipide();
					// lipide.setApport(getApportValue(dataElements,
					// 1));
					initNurtriment(lipide, dataElements, 1);
					lipide.setGrasSature(getApportValue(dataElements, 2));
					lipide.setGrasMonoInsature(getApportValue(dataElements, 3));
					lipide.setGrasPolyInsature(getApportValue(dataElements, 4));
					declinaison.addApportNutriment(lipide);

					Glucide glucide = new Glucide();
					// glucide.setApport(getApportValue(dataElements,
					// 5));
					initNurtriment(glucide, dataElements, 5);
					glucide.setFibreAlimentaire(getApportValue(dataElements, 6));
					declinaison.addApportNutriment(glucide);

					Proteine proteine = new Proteine();
					initNurtriment(proteine, dataElements, 7);
					// proteine.setApport(getApportValue(dataElements,
					// 7));
					declinaison.addApportNutriment(proteine);

					Eau eau = new Eau();
					initNurtriment(eau, dataElements, 8);
					// eau.setApport(getApportValue(dataElements, 8));
					declinaison.addApportNutriment(eau);

					Cholesterol cholesterol = new Cholesterol();
					initNurtriment(cholesterol, dataElements, 9);
					// cholesterol.setApport(getApportValue(dataElements,
					// 9));
					declinaison.addApportNutriment(cholesterol);

					VIT_TYPE vType = getVitamineType(dataElements, 10);

					int n = 10;
					while (vType != null) {
						AbstractNutriment vitamine = new Vitamine();
						vitamine.setTypeVitamine(vType);
						// vitamine.setApport(getApportValue(dataElements,
						// n));
						initNurtriment(vitamine, dataElements, n);
						vType = getVitamineType(dataElements, n++);
						declinaison.addApportNutriment(vitamine);
					}
					n--;

					MIN_TYPE mType = getMineralType(dataElements, n);
					OEL_TYPE oeType = getOEType(dataElements, n);

					while (oeType != null || mType != null) {
						if (mType != null) {
							Mineral mineral = new Mineral();
							mineral.setTypeMineral(mType);
							// mineral.setApport(getApportValue(
							// dataElements, n));
							initNurtriment(mineral, dataElements, n);
							declinaison.addApportNutriment(mineral);
						} else if (oeType != null) {
							OligoElement oligoElement = new OligoElement();
							oligoElement.setTypeOligoElement(oeType);
							// oligoElement.setApport(getApportValue(
							// dataElements, n));
							initNurtriment(oligoElement, dataElements, n);
							declinaison.addApportNutriment(oligoElement);
						}
						n++;
						mType = getMineralType(dataElements, n);
						oeType = getOEType(dataElements, n);
					}

					declinaisonService.sauvegarderDeclinaison(declinaison);
				}
			}

			System.out.println(categorie.getNom());

		}

		System.out.println("FINISH : " + (System.currentTimeMillis() - start));

	}

	public AbstractNutriment initNurtriment(AbstractNutriment nutriment, Elements dataElements, int pos) {
		nutriment.setDetails(getDetailsValue(dataElements, pos));
		nutriment.setApport(getApportValue(dataElements, pos));
		return nutriment;
	}

	public String getDetailsValue(Elements dataElements, int pos) {
		if (pos >= dataElements.size()) {
			return "";
		}
		try {
			return dataElements.get(pos).parent().child(2).select("div").html();
		} catch (Exception e) {
			return "";
		}
	}

	public double getApportValue(Elements dataElements, int pos) {
		if (pos >= dataElements.size()) {
			return 0;
		}
		try {
			return Double.parseDouble(dataElements.get(pos).parent().child(3).select("div").html().split(" ")[0]);
		} catch (Exception e) {
			return 0;
		}
	}

	public VIT_TYPE getVitamineType(Elements dataElements, int pos) {
		if (pos >= dataElements.size()) {
			return null;
		}
		String txt = dataElements.get(pos).parent().child(1).select("div").html().replaceAll("\\s*:", "");
		try {
			Matcher matcher = Pattern.compile("Vitamine ([A-Z0-9]+)").matcher(txt);
			if (matcher.matches()) {
				return VIT_TYPE.valueOf(matcher.group(1));
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println("Vitamine Inconnue : #" + txt + "#");
			return null;
		}
	}

	public OEL_TYPE getOEType(Elements dataElements, int pos) {
		if (pos >= dataElements.size()) {
			return null;
		}
		String txt = dataElements.get(pos).parent().child(1).select("div").html().replaceAll("\\s*:", "");
		try {
			return OEL_TYPE.getType(txt.toLowerCase());
		} catch (Exception e) {
			System.err.println("OE inconnue #" + txt + "#");
			return null;
		}
	}

	public MIN_TYPE getMineralType(Elements dataElements, int pos) {
		if (pos >= dataElements.size()) {
			return null;
		}
		String txt = dataElements.get(pos).parent().child(1).select("div").html().replaceAll("\\s*:", "");
		try {
			return Mineral.MIN_TYPE.getType(txt.toLowerCase());
		} catch (Exception e) {
			System.err.println("Mineral inconnu : #" + txt + "#");
			return null;
		}
	}

}
