<%@page import="org.eni.encheres.erreur.LecteurMessage"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.temporal.ChronoUnit"%>
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
    <title>Enchere ENI - Résultat recherche</title>
    
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
                                <li class="nav-item"><a class="button-lien-perso"
									href="NouvelleVente"><button type="button"
											class="btn btn-outline-warning">Créer une vente</button></a></li>
								<li class="nav-item">
									<form id="search-form" class="form" action="RechercheArticles"
										method="post">
										<div class="input-group">
											<input type="text" class="form-control" name="keyword"
												placeholder="Que recherchez-vous ?">
											<div class="input-group-append">
												<select class="form-control" name="categorie">
													<option value="0" selected="true">Catégories</option>
													<c:forEach items="${listeCategorie}" var="categorie"
														varStatus="status">
														<option value="${categorie.noCategorie}">${categorie.libelle}</option>
													</c:forEach>
												</select>
											</div>
											<div class="input-group-append">
												<input class="btn btn-outline-light" name="btnSubmit"
													value="&#128269;" type="submit">
											</div>
										</div>
									</form>
								</li>
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
												<a class="dropdown-item text-dark border-0" href="ModifierProfil">Mon profil</a>
												<a class="dropdown-item text-dark border-0" href="MesArticles">Mes articles</a>
												<a class="dropdown-item text-dark border-0" href="Deconnexion">Se déconnecter</a>
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
    
	<!--====== SELECTION ARTICLES ======-->

    <section class="divers-area">
        <div class="container">
            <div class="row justify-content-center">

				<c:forEach items="${listeArticleVendu}" var="articleVendu" varStatus="status">
				<div class="col-sm-12 col-md-6 col-lg-4"> <!-- article -->
                    <div class="row justify-content-center">
                        <a href="AfficherArticle?noArticle=${articleVendu.noArticle}">
                            <div class="article">
                                <figure class="img-responsive">
                                    <img src="images/article-1.jpg">
                                    <figcaption>
                                        <span class="article-titre">${articleVendu.nomArticle}</span>
                                        <span class="article-prix"><strong>${articleVendu.prixVente} $</strong></span>
                                        <!-- <span class="article-ville">${articleVendu.etatVente}</span> -->
                                        <div class="d-flex align-items-center justify-content-between rounded-pill bg-light px-3 py-2 mt-4">
                                            <p class="small mb-0"><i class="fa fa-picture-o mr-2"></i><span class="font-weight-bold">Termine dans</span></p>
                                            <div class="badge badge-danger px-3 rounded-pill font-weight-normal">${ChronoUnit.DAYS.between(LocalDate.now(), articleVendu.dateFinEncheres)} jour(s)</div>
                                        </div>
                                    </figcaption>
                                    <span class="actions">
                                        <button class="btn btn-light bnt-action" type="submit">Voir</button>
                                    </span>
                                </figure>
                            </div>
                        </a>
                    </div>
                </div>
                </c:forEach>

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
		function changeRadioBox() {
			if (document.getElementById("radioAchat").checked == true) {
				document.getElementById("checkbox1RadioVente").disabled = true;
				document.getElementById("checkbox2RadioVente").disabled = true;
				document.getElementById("checkbox3RadioVente").disabled = true;
				document.getElementById("checkbox1RadioVente").checked = false;
				document.getElementById("checkbox2RadioVente").checked = false;
				document.getElementById("checkbox3RadioVente").checked = false;
				
				document.getElementById("checkbox1RadioAchat").disabled = false;
				document.getElementById("checkbox2RadioAchat").disabled = false;
			}
			if (document.getElementById("radioVente").checked == true) {
				document.getElementById("checkbox1RadioAchat").disabled = true;
				document.getElementById("checkbox2RadioAchat").disabled = true;
				document.getElementById("checkbox1RadioAchat").checked = false;
				document.getElementById("checkbox2RadioAchat").checked = false;
				
				document.getElementById("checkbox1RadioVente").disabled = false;
				document.getElementById("checkbox2RadioVente").disabled = false;
				document.getElementById("checkbox3RadioVente").disabled = false;
			}
		}
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
