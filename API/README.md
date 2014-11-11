#URL disponibles :
- "categories/lister" [GET];
     * Liste les catégories disponibles : http://iagl-server.cloudapp.net/api/categories/lister
- "categories/ajouter" [POST];
     * Ajoute une catégorie :
     * * $.ajax({
           type: "POST",
           url: "http://iagl-server.cloudapp.net/api/categories/ajouter",
           data: '{ "nom": "test", "description" : "description de la catégorie (max à 20 000 char)" }',
           contentType: 'application/json'
         });
     * Retourne la catégorie sauvegardée (avec son id)

- "aliment/total" [GET];
     * Retounre le nombre total d'aliment en base : http://iagl-server.cloudapp.net/api/aliment/total
- "aliment/lister" [GET];
     * Liste les aliments disponibles avec leurs declinaisons : http://iagl-server.cloudapp.net/api/aliment/lister
- "aliment/ajouter" [POST]
     * Sauvegarde le nom de l'aliment dans la/les categorie/s associée/s, si une catégorie n'existe pas elle sera sauvegardée
     * Retounr l'aliment sauvegarder (avec son id) et ses catégories (et leurs ids)
     * * $.ajax({
       type: "POST",
       url: "http://iagl-server.cloudapp.net/api/aliment/ajouter",
       data: JSON.stringify({ "nom" : "nom du nouvel aliment", "categories" : [ { "nom": "nouvelle catégorie" }, {id:12}] }),
       contentType: 'application/json'
     });

- "declinaison/ajouter" [POST];
- "declinaison/etat/ajouter" [POST];
- "declinaison/etat/lister" [GET];

- "nutriments/dec/{id}" [GET];
- "nutriments/types" [GET];
