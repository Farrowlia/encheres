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
    <title>Enchere ENI</title>
    
    <!-- Styles importés -->
    <link rel="stylesheet" href="css/lineicons.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	
	<!-- Favicon  -->
    <link rel="icon" href="images/logo-vert.png">
</head>

<body>

	<!-- Pop-up erreur -->
	<div class="containerPerso">
	<c:forEach items="${listeCodesErreur}" var="codeErreur" varStatus="status">
		<div class="toastPerso">${LecteurMessage.getMessageErreur(codeErreur)} </div>
	</c:forEach>
	</div>
   
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
                                <li class="nav-item active"><a class="page-scroll" href="#accueil">Accueil</a></li>
                                <li class="nav-item"><a class="page-scroll" href="#concept">Le concept</a></li>
                                <li class="nav-item"><a class="page-scroll" href="#equipe">L'équipe</a></li>
                                <li class="nav-item"><a class="page-scroll" href="#contact">Contact</a></li>
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

    <section id="accueil" class="header_area">
        <header class="header">
            <div class="header-content">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="text-container">
                                <h1>POUR VOUS C'EST IMPORTANT DE <br><span id="js-rotating">REPARER, PARTAGER, RESTAURER, REUTILISER</span> LES OBJETS</h1>
                                <p class="p-heading p-large">Enchere.fr, premier site d'enchère d'objet basé d'abord sur le recyclage et l'entraide</p>
                                <div class="container search-form">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="panel-body">
                                                <div class="text-center">
                                                    <form id="search-form" class="form" action="Servlet" method="post">
                                                        <div class="form-group">
                                                        	<input class="form-control" name="keyword" placeholder="Que recherchez-vous ?" type="text">
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="input-group">
                                                                <select class="form-control" name="categorie" >
                                                                    <option value="0" selected="true">Catégories</option>
																	<c:forEach items="${listeCategorie}" var="categorie"
																		varStatus="status">
																		<option value="${categorie.noCategorie}">${categorie.libelle}</option>
																	</c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <input class="btn btn-lg btn-primary btn-block" name="btnSubmit" value="Rechercher" type="submit">
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </section>

    
    <!--====== A PROPOS ======-->

    <section id="concept" class="about-area">
        <div class="container">
            <div class="row">
                <div class="col-lg-5">
                    <div class="faq-content mt-45">
                        <div class="about-title">
                            <h6 class="sub-title">UN PEU PLUS SUR LE CONCEPT</h6>
                            <h4 class="title">Questions fréquemment posées</h4>
                        </div>
                        <div class="about-accordion">
                            <div class="accordion" id="accordionExample">
                                <div class="card"> <!-- Question -->
                                    <div class="card-header" id="headingOne">
                                        <a href="#" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">C'est quoi les "crédits" ?</a>
                                    </div>
                                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
                                        <div class="card-body">
                                            <p class="text">Ici par d'argent, de fric, de pez ou de flouz. L'objectif
                                                n'est pas de s'enrichir mais de reçycler. Tu n'as plus besoin d'un
                                                objet, met le aux enchères et obtient des "crédits" pour pouvoir
                                                enchérir à ton tour sur un objet que tu as besoin.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="card"> <!-- Question -->
                                    <div class="card-header" id="headingTwo">
                                        <a href="#" class="collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">Comment je fais pour acheter des "crédits"</a>
                                    </div>
                                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                                        <div class="card-body">
                                            <p class="text">Tu peux pas ^^. Pour obtenir des "crédits" tu peux mettre en
                                                enchère un objet dont tu ne te sers plus ou tu peux réaliser des actions
                                                d'entraides dans l'une des associations partenaires.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="card"> <!-- Question -->
                                    <div class="card-header" id="headingThree">
                                        <a href="#" class="collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">Question 3</a>
                                    </div>
                                    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                                        <div class="card-body">
                                            <p class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                                Donec et lacus risus. Proin elementum ut ligula non mollis. Fusce a nunc
                                                vel turpis pharetra.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-7">
                    <div class="about-image mt-50">
                        <img src="images/about.png" alt="about">
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <!--====== EQUIPE ======-->

    <section id="equipe" class="team-area">
        <div class="container">
            <div class="row justify-content-center head-section">
                <div class="col-lg-6 col-md-10">
                    <div class="section-title text-center pb-30">
                        <h3 class="title">L'EQUIPE DE CHOC</h3>
                        <p class="text">Une suggestion d'amélioration, une remarque. S'il vous plait partagez là avec nous.</p>
                        <p class="text">Parce que notre concept est plus qu'une simple idée, toute l'équipe se démène pour vous offrir le meilleur site internet.</p>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-sm-10 col-md-6 col-lg-4 col-xl-3"> <!-- equipier -->
                    <div class="team-style-eleven text-center mt-30 wow fadeIn" data-wow-duration="1s" data-wow-delay="0s">
                        <div class="team-image">
                            <img src="images/team-1.jpg" alt="Team">
                        </div>
                        <div class="team-content">
                            <div class="team-social">
                                <ul class="social">
                                    <li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
                                    <li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
                                    <li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
                                    <li><a href="#"><i class="lni lni-instagram"></i></a></li>
                                </ul>
                            </div>
                            <h4 class="team-name"><a href="#">Marie Laure</a></h4>
                            <span class="sub-title">Directrice</span>
                        </div>
                    </div>
                </div>
                <div class="col-sm-10 col-md-6 col-lg-4 col-xl-3"> <!-- equipier -->
                    <div class="team-style-eleven text-center mt-30 wow fadeIn" data-wow-duration="1s" data-wow-delay="0s">
                        <div class="team-image">
                            <img src="images/team-2.jpg" alt="Team">
                        </div>
                        <div class="team-content">
                            <div class="team-social">
                                <ul class="social">
                                    <li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
                                    <li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
                                    <li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
                                    <li><a href="#"><i class="lni lni-instagram"></i></a></li>
                                </ul>
                            </div>
                            <h4 class="team-name"><a href="#">Dimitri</a></h4>
                            <span class="sub-title">UX Designer</span>
                        </div>
                    </div>
                </div>
                <div class="col-sm-10 col-md-6 col-lg-4 col-xl-3"> <!-- equipier -->
                    <div class="team-style-eleven text-center mt-30 wow fadeIn" data-wow-duration="1s" data-wow-delay="0s">
                        <div class="team-image">
                            <img src="images/team-3.jpg" alt="Team">
                        </div>
                        <div class="team-content">
                            <div class="team-social">
                                <ul class="social">
                                    <li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
                                    <li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
                                    <li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
                                    <li><a href="#"><i class="lni lni-instagram"></i></a></li>
                                </ul>
                            </div>
                            <h4 class="team-name"><a href="#">Anne</a></h4>
                            <span class="sub-title">Web developer</span>
                        </div>
                    </div>
                </div>
                <div class="col-sm-10 col-md-6 col-lg-4 col-xl-3"> <!-- equipier -->
                    <div class="team-style-eleven text-center mt-30 wow fadeIn" data-wow-duration="1s" data-wow-delay="0s">
                        <div class="team-image">
                            <img src="images/team-4.jpg" alt="Team">
                        </div>
                        <div class="team-content">
                            <div class="team-social">
                                <ul class="social">
                                    <li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
                                    <li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
                                    <li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
                                    <li><a href="#"><i class="lni lni-instagram"></i></a></li>
                                </ul>
                            </div>
                            <h4 class="team-name"><a href="#">Bruno</a></h4>
                            <span class="sub-title">Concierge</span>
                        </div>
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
