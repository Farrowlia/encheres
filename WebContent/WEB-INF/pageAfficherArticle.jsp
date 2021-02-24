<%@page import="org.eni.encheres.erreur.LecteurMessage"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- SEO Meta Tags -->
    <meta name="description" content="Redecouvrez les enchères avec une pointe d'éthique."> <!-- Description du site -->
    <meta name="author" content="ENIGroupeC">
    <meta name="robots" content="index,follow"/> <!-- Autorise les moteurs de recherche à indexer la page -->
    <link rel="canonical" href="http://www.enchereeni.fr/index.html"/> <!-- Adresse canonique du site -->

    <!-- OpenGraph Meta Tags - Personnalise la vignette de partage sur les sites tels que LinkedIn, Facebook, Google+,... -->
	<meta property="og:site_name" content="EnchereENI"/>
    <meta property="og:site" content="http://www.enchereeni.fr/index.html"/>
    <meta property="og:locale" content="fr_FR">
	<meta property="og:title" content="Enchere ENI"/>
	<meta property="og:description" content="Redecouvrez les enchères avec une pointe d'éthique."/>
	<meta property="og:image" content="images/logo-vert.png"/>
	<meta property="og:url" content="http://www.enchereeni.fr/index.html"/>
	<meta property="og:type" content="website"/>

    <!-- Titre du site -->
    <title>Enchere ENI - Créer vente</title>
    
    <!-- Styles importés -->
    <link rel="stylesheet" href="css/lineicons.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	
	<!-- Favicon  -->
    <link rel="icon" href="images/logo-vert.png">
</head>

<body>
   
    <!--====== PRELOADER ======-->

    <div class="preloader">
        <div class="loader">
            <div class="ytp-spinner">
                <div class="ytp-spinner-container">
                    <div class="ytp-spinner-rotator">
                        <div class="ytp-spinner-left">
                            <div class="ytp-spinner-circle"></div>
                        </div>
                        <div class="ytp-spinner-right">
                            <div class="ytp-spinner-circle"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!--====== MENU ======-->

    <section class="navbar-area">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <nav class="navbar navbar-expand-lg">
                        <a class="navbar-brand" href="index.jsp">
                            <img src="images/logo-blanc.png" alt="Logo">
                        </a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarAccueil" aria-controls="navbarAccueil" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="toggler-icon"></span>
                            <span class="toggler-icon"></span>
                            <span class="toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse sub-menu-bar" id="navbarAccueil">
                            <ul class="navbar-nav m-auto">
                                <li class="nav-item"><a href="index.jsp">Accueil</a></li>
                                <li class="nav-item"><a href="#">Rechercher</a></li>
                            </ul>
                        </div>
                        <div class="navbar-btn">
                            <ul>
                                <li>
                                	<c:set var="sessionUtilisateur" value="${sessionUtilisateur}"/>
									<c:if test="${sessionUtilisateur != null}">
										<div class="dropdown">
											<a class="solid dropdown-toggle" href="#"
												role="button" id="dropdownMenuLink" data-toggle="dropdown"
												aria-haspopup="true" aria-expanded="false">${sessionUtilisateur.prenom}
												</a>

											<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
												<a class="dropdown-item text-dark" href="ModifierProfil">Mon profil</a>
												<a class="dropdown-item text-dark" href="MesArticles">Mes articles</a>
												<a class="dropdown-item text-dark" href="Deconnexion">Se déconnecter</a>
											</div>
										</div>
									</c:if>
									<c:if test="${sessionUtilisateur == null}">
										<a class="solid" href="Authentification">Se connecter</a>
									</c:if>
								</li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    
    <!--====== HEADER ======-->

    <section class="mini_header-area">
    </section>
    
    <!--====== ARTICLE ======-->

	<section class="divers-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-7 col-md-12">
					<div class="carousel slide" data-ride="carousel" id="carousel-1">
						<div class="carousel-inner" role="listbox">
							<c:forEach items="${listeImage}" var="image" varStatus="status">
								<div class="carousel-item active">
									<img class="img-thumbnail w-100 d-block"
										src="${image.cheminUrl}" alt="Slide Image" loading="lazy">
								</div>
							</c:forEach>
						</div>
						<div>
							<a class="carousel-control-prev" href="#carousel-1" role="button"
								data-slide="prev"><span class="carousel-control-prev-icon"></span><span
								class="sr-only">Précédente</span></a><a
								class="carousel-control-next" href="#carousel-1" role="button"
								data-slide="next"><span class="carousel-control-next-icon"></span><span
								class="sr-only">Suivante</span></a>
						</div>
						<ol class="carousel-indicators">
							<li data-target="#carousel-1" data-slide-to="0" class="active"></li>
							<c:forEach items="${listeImage}" var="image" varStatus="status">
								<c:set var="i" value="1"/>
								<li data-target="#carousel-1" data-slide-to="i"></li>
								<c:set var="i" value="i+1"/>
							</c:forEach>
						</ol>
					</div>
				</div>
				<div class="col-lg-5 col-md-12">
					<h4>${articleVendu.nomArticle}</h4>
					<div class="price">
						<span class="text-success">${articleVendu.prixVente} $</span>
					</div>
					<div class="d-flex align-items-center mt-4 offers mb-1">
						<span class="font-weight-bold">Description :</span><span
							class="ml-2"> ${articleVendu.description}</span>
					</div>
					<div class="d-flex align-items-center mt-4 offers mb-1">
						<span class="font-weight-bold">Catégorie :</span><span
							class="ml-2"> ${articleVendu.categorie.libelle}</span>
					</div>
					<div class="d-flex align-items-center mt-4 offers mb-1">
						<span class="font-weight-bold">Enchère actuelle :</span><span
							class="ml-2"> par Diego356</span>
					</div>
					<div class="d-flex align-items-center mt-4 offers mb-1">
						<span class="font-weight-bold">Mise à prix :</span><span
							class="ml-2"> ${articleVendu.prixInitial} $</span>
					</div>
					<div class="d-flex align-items-center mt-4 offers mb-1">
						<span class="font-weight-bold">Retrait :</span><span class="ml-2">
							${retrait.rue} ${retrait.codePostal} ${retrait.ville}</span>
					</div>
					<div class="d-flex align-items-center mt-4 offers mb-1">
						<span class="font-weight-bold">Termine dans :</span><span
							class="ml-2 badge-temp-restant bg-danger" id="temp-restant-fin-encheres"><br></span>
					</div>
					<hr>


					<div>
						<span class="font-weight-bold">Vendeur :</span>
								<button type="button" class="btn btn-light"
									data-toggle="modal" data-target="#lienModal">${articleVendu.utilisateur.pseudo}</button>
					</div>
					<div class="modal fade" id="lienModal" role="dialog">
						<div class="modal-dialog">
							<div class="card">
								<div class="card-body text-center">
									<h4>${articleVendu.utilisateur.prenom} ${articleVendu.utilisateur.nom}</h4>
									<hr>
									<p>${articleVendu.utilisateur.email}</p>
									<p>${articleVendu.utilisateur.telephone}</p>
									<p>${articleVendu.utilisateur.rue}</p>
									<p>${articleVendu.utilisateur.codePostal}</p>
									<p>${articleVendu.utilisateur.ville}</p>
								</div>
							</div>
						</div>
					</div>
					
					
					
					
					<div class="mt-3">
						<form id="search-form" class="form" action="NouvelleEnchere" method="post">
						<input name="montantEnchere" type="number" value="${articleVendu.prixVente}" min="0" max="1000" step="10"/>
						<input name="btnSubmit" class="btn btn-success" value="Enchérir" type="submit">
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!--====== FOOTER ======-->

    <section id="contact" class="footer-area footer-dark">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <div class="footer-logo text-center">
                        <a class="mt-30" href="index.jsp"><img src="images/logo-blanc.png" alt="Logo"></a>
                    </div>
                    <ul class="social text-center mt-60">
                        <li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
                        <li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
                        <li><a href="#"><i class="lni lni-instagram-original"></i></a></li>
                        <li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
                    </ul>
                    <div class="footer-support text-center">
                        <span class="number">01 36 96 85 45</span>
                        <span class="mail">support@enchereeni.com</span>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
	<script>
	function dateDiff(date1, date2) {
	    var diff = {}                           // Initialisation du retour
	    var tmp = date2 - date1;
	 
	    tmp = Math.floor(tmp/1000);             // Nombre de secondes entre les 2 dates
	    diff.sec = tmp % 60;                    // Extraction du nombre de secondes
	 
	    tmp = Math.floor((tmp-diff.sec)/60);    // Nombre de minutes (partie entière)
	    diff.min = tmp % 60;                    // Extraction du nombre de minutes
	 
	    tmp = Math.floor((tmp-diff.min)/60);    // Nombre d'heures (entières)
	    diff.hour = tmp % 24;                   // Extraction du nombre d'heures
	     
	    tmp = Math.floor((tmp-diff.hour)/24);   // Nombre de jours restants
	    diff.day = tmp;
	     
	    return diff;
	}
	
	   function horloge() {
		   var boite = document.getElementById("temp-restant-fin-encheres");
		   var heure = new Date();
		   diff = dateDiff(new Date(), new Date('${articleVendu.dateFinEncheres}'));
		   boite.textContent = diff.day + " jour(s) " + diff.hour + "h" + diff.min + "min" + diff.sec + "s";
	   }
	   setInterval("horloge()", 1000);
	</script>
    <!-- Javascript importés -->
    <!--====== Jquery js ======-->
    <script src="js/vendor/jquery-1.12.4.min.js"></script>
    <script src="js/vendor/modernizr-3.7.1.min.js"></script>
    
    <!--====== Bootstrap js ======-->
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
    <!--====== Scrolling Nav js ======-->
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/scrolling-nav.js"></script>

    <!--====== Rotating text js ======-->
    <script src="js/morphext.min.js"></script>

    <!--====== Main js ======-->
    <script src="js/main.js"></script>
    
</body>

</html>
