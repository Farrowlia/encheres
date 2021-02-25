<%@page import="org.eni.encheres.erreur.LecteurMessage"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- SEO Meta Tags -->
<meta name="description"
	content="Redecouvrez les enchères avec une pointe d'éthique.">
<!-- Description du site -->
<meta name="author" content="ENIGroupeC">
<meta name="robots" content="index,follow" />
<!-- Autorise les moteurs de recherche à indexer la page -->
<link rel="canonical" href="http://www.enchereeni.fr/index.html" />
<!-- Adresse canonique du site -->

<!-- OpenGraph Meta Tags - Personnalise la vignette de partage sur les sites tels que LinkedIn, Facebook, Google+,... -->
<meta property="og:site_name" content="EnchereENI" />
<meta property="og:site" content="http://www.enchereeni.fr/index.html" />
<meta property="og:locale" content="fr_FR">
<meta property="og:title" content="Enchere ENI" />
<meta property="og:description"
	content="Redecouvrez les enchères avec une pointe d'éthique." />
<meta property="og:image" content="images/logo-vert.png" />
<meta property="og:url" content="http://www.enchereeni.fr/index.html" />
<meta property="og:type" content="website" />

<!-- Titre du site -->
<title>Enchere ENI - Connexion</title>

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

	<section class="mini_header-area"></section>

	<!--====== FORM ======-->

	<section class="divers-area">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div class="nav nav-tabs">
						<a class="nav-item nav-link  ${isLogin ? 'active' : ''}"
							href="#p1" data-toggle="tab">Se connecter</a> <a
							class="nav-item nav-link ${!isLogin ? 'active' : ''}" href="#p2"
							data-toggle="tab">S'inscrire</a>
					</div>
					<div class="tab-content">
						<div class="tab-pane ${isLogin ? 'active' : ''}" id="p1">
							<!-- Onglet Se Connecter -->
							<div class="container">
								<div class="row justify-content-center">
									<div class="col-lg-8 register-form">
										<form id="connection-form" class="form"
											action="${pageContext.request.contextPath}/Authentification"
											method="post">
											<div class="form-group">
												<input name="login" placeholder="Pseudo/Email" class="form-control" type="text" required> 
												<span class="erreur">${form.erreurs['login']}</span>
											</div>
											<div class="form-group">
												<input name="motdepasse" placeholder="Mot de passe"
													class="form-control" type="password" required> <span
													class="erreur">${form.erreurs['motdepasse']}</span>
											</div>
											<div class="form-group">
												<input name="btnSubmit"
													class="btn btn-lg btn-primary btn-block"
													value="Se connecter" type="submit">
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane ${!isLogin ? 'active' : ''}" id="p2">
							<!-- Onglet S'inscrire -->
							<div class="container">
								<div class="row justify-content-center">
									<div class="col-lg-8 register-form">
										<form id="connection-form" class="form"
											action="${pageContext.request.contextPath}/Inscription"
											method="post">
											<div class="form-group">
												<input name="pseudo" placeholder="Pseudo"
													class="form-control ${form.erreurs['pseudo'] != null ? 'is-invalid' : '' }" type="text"
													value="<c:out value="${utilisateur.pseudo}"/>"
													maxlength="30" required> <small class="text-danger">${form.erreurs['pseudo']}</small>
											</div>
											<div class="form-group">
												<input name="nom" placeholder="Nom"
													value="<c:out value="${utilisateur.nom}"/>"
													class="form-control" type="text" maxlength="30" required>
											</div>
											<div class="form-group">
												<input name="prenom" placeholder="Prenom"
													value="<c:out value="${utilisateur.prenom}"/>"
													class="form-control" type="text" maxlength="30" required>
											</div>
											<div class="form-group">
												<input name="email" placeholder="Email"
													value="<c:out value="${utilisateur.email}"/>"
													class="form-control ${form.erreurs['email'] != null ? 'is-invalid' : '' }" type="email" maxlength="50" required>
												<small class="text-danger">${form.erreurs['email']}</small>
											</div>
											<div class="form-group">
												<input name="telephone" placeholder="Telephone"
													value="<c:out value="${utilisateur.telephone}"/>"
													class="form-control ${form.erreurs['telephone'] != null ? 'is-invalid' : '' }" type="tel" maxlength="15">
												<small class="text-danger">${form.erreurs['telephone']}</small>
											</div>
											<div class="form-group">
												<input name="rue" placeholder="Rue"
													value="<c:out value="${utilisateur.rue}"/>"
													class="form-control" type="text" maxlength="50" required>
											</div>
											<div class="form-group">
												<input name="codePostal" placeholder="Code postal"
													value="<c:out value="${utilisateur.codePostal}"/>"
													class="form-control ${form.erreurs['codePostal'] != null ? 'is-invalid' : '' }" type="text" maxlength="10" required>
												<small class="text-danger">${form.erreurs['codePostal']}</small>
											</div>
											<div class="form-group">
												<input name="ville" placeholder="Ville"
													value="<c:out value="${utilisateur.ville}"/>"
													class="form-control" type="text" maxlength="30" required>
											</div>
											<div class="form-group">
												<input name="motdepasse" placeholder="Mot de passe"
													class="form-control ${form.erreurs['motdepasse'] != null ? 'is-invalid' : '' }" type="password" maxlength="30"
													required> <small class="text-danger">${form.erreurs['motdepasse']}</small>
											</div>
											<div class="form-group">
												<input name="confirmation"
													placeholder="Confirmer Mot de Passe" class="form-control ${form.erreurs['confirmation'] != null ? 'is-invalid' : '' }"
													type="password" maxlength="30" required> <small class="text-danger">${form.erreurs['confirmation']}</small>
											</div>
											<div class="form-group">
												<input name="inscription"
													class="btn btn-lg btn-primary btn-block" value="S'inscrire"
													type="submit">
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
	</section>

	<!--====== FOOTER ======-->

	<section id="contact" class="footer-area footer-dark">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-6">
					<div class="footer-logo text-center">
						<a class="mt-30" href="index.jsp"><img
							src="images/logo-blanc.png" alt="Logo"></a>
					</div>
					<ul class="social text-center mt-60">
						<li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
						<li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
						<li><a href="#"><i class="lni lni-instagram-original"></i></a></li>
						<li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
					</ul>
					<div class="footer-support text-center">
						<span class="number">01 36 96 85 45</span> <span class="mail">support@enchereeni.com</span>
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
	<script>
		(function() {
			"use strict"
			window.addEventListener("load", function() {
				var form = document.getElementById("form")
				form.addEventListener("submit", function(event) {
					if (form.checkValidity() == false) {
						event.preventDefault()
						event.stopPropagation()
					}
					form.classList.add("was-validated")
				}, false)
			}, false)
		}())
	</script>

</body>

</html>
