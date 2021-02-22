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
                        <a class="navbar-brand" href="index.html">
                            <img src="images/logo-blanc.png" alt="Logo">
                        </a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarAccueil" aria-controls="navbarAccueil" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="toggler-icon"></span>
                            <span class="toggler-icon"></span>
                            <span class="toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse sub-menu-bar" id="navbarAccueil">
                            <ul class="navbar-nav m-auto">
                                <li class="nav-item"><a href="index.html">Accueil</a></li>
                                <li class="nav-item"><a href="ServletRechercheArticle">Rechercher</a></li>
                            </ul>
                        </div>
                        <div class="navbar-btn">
                            <ul>
                                <li><a class="solid" href="ServletRedirection?redirection=pageConnectionInscription">Se connecter</a></li>
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
			<div class="row justify-content-center">
				<div class="col-lg-12 register-form">



					<form id="connection-form" class="form"
						action="Servlet????????"
						method="post">

						<div class="row justify-content-center">
							<div class="col-lg-4">
								<div class="form-group">
									<label class="control-label">Choisir image</label> <input
										id="image" name="image" type="file">
								</div>
							</div>



							<div class="col-lg-8">


								<div class="form-group row">
									<label for="nomArticle" class="col-2 col-form-label">Nom
										Article</label>
									<div class="col-10">
										<input id="nomArticle" name="nomArticle"
											placeholder="nomArticle" class="form-control" type="text"
											required>
									</div>
								</div>

								<div class="form-group row">
									<label for="description" class="col-2 col-form-label">Description</label>
									<div class="col-10">
										<input id="description" name="description"
											placeholder="description" class="form-control" type="text"
											required>
									</div>
								</div>

								<div class="form-group row">
									<label for="noCategorie" class="col-2 col-form-label">Categories</label>
									<div class="col-10">
										<select id="noCategorie" name="noCategorie"
											class="form-control">
											<option selected="true" disabled></option>
											<option value="1">Meuble</option>
											<option value="2">Electroménager</option>
											<option value="3">Vetements</option>
										</select>
									</div>
								</div>

								<div class="form-group row">
									<label for="prixInitial" class="col-2 col-form-label">Mise
										à prix</label>
									<div class="col-10">
										<input type="number" id="prixInitial" name="prixInitial"
											value="5" min="0" max="1000" step="5" />
									</div>
								</div>

								<div class="form-group row">
									<label for="dateDebutEncheres" class="col-2 col-form-label">Date
										début enchère</label>
									<div class="col-10">
										<input class="form-control" type="date" value="2011-08-19"
											id="dateDebutEncheres" name="dateDebutEncheres">
									</div>
								</div>

								<div class="form-group row">
									<label for="dateDebutEncheres" class="col-2 col-form-label">Date
										fin enchère</label>
									<div class="col-10">
										<input class="form-control" type="date" value="2011-08-19"
											id="dateDebutEncheres" name="dateDebutEncheres">
									</div>
								</div>

								<div class="form-group row">
									<label for="rue" class="col-2 col-form-label">Rue </label>
									<div class="col-10">
										<input id="rue" name="rue" placeholder="rue"
											class="form-control" type="text" required>
									</div>
								</div>

								<div class="form-group row">
									<label for="codePostal" class="col-2 col-form-label">Code
										Postal </label>
									<div class="col-10">
										<input id="codePostal" name="codePostal"
											placeholder="Code Postal" class="form-control" type="text"
											required>
									</div>
								</div>

								<div class="form-group row">
									<label for="ville" class="col-2 col-form-label">Ville </label>
									<div class="col-10">
										<input id="ville" name="ville" placeholder="ville"
											class="form-control" type="text" required>
									</div>
								</div>

									<div class="form-group row">
										<div class="col-4">
											<input name="btnSubmit"
												class="btn btn-lg btn-primary btn-block" value="Enregistrer"
												type="submit">
										</div>
										<div class="col-4">
											<input name="btnSubmit"
												class="btn btn-lg btn-primary btn-block" value="Annuler"
												type="submit">
										</div>
										<div class="col-4">
											<input name="btnSubmit"
												class="btn btn-lg btn-primary btn-block" value="Annuler la vente"
												type="submit">
										</div>
									</div>



							</div>
						</div>
					</form>



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
								<a class="mt-30" href="index.html"><img
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
</body>

</html>
