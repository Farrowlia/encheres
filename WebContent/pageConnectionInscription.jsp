<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- SEO Meta Tags -->
    <meta name="description" content="Redecouvrez les ench�res avec une pointe d'�thique."> <!-- Description du site -->
    <meta name="author" content="ENIGroupeC">
    <meta name="robots" content="index,follow"/> <!-- Autorise les moteurs de recherche � indexer la page -->
    <link rel="canonical" href="http://www.enchereeni.fr/index.html"/> <!-- Adresse canonique du site -->

    <!-- OpenGraph Meta Tags - Personnalise la vignette de partage sur les sites tels que LinkedIn, Facebook, Google+,... -->
	<meta property="og:site_name" content="EnchereENI"/>
    <meta property="og:site" content="http://www.enchereeni.fr/index.html"/>
    <meta property="og:locale" content="fr_FR">
	<meta property="og:title" content="Enchere ENI"/>
	<meta property="og:description" content="Redecouvrez les ench�res avec une pointe d'�thique."/>
	<meta property="og:image" content="images/logo-vert.png"/>
	<meta property="og:url" content="http://www.enchereeni.fr/index.html"/>
	<meta property="og:type" content="website"/>

    <!-- Titre du site -->
    <title>Enchere ENI - Connexion</title>
    
    <!-- Styles import�s -->
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
    
    <!--====== FORM ======-->

	<section class="divers-area">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div class="nav nav-tabs">
						<a class="nav-item nav-link active" href="#p1" data-toggle="tab">Se
							connecter</a> <a class="nav-item nav-link" href="#p2"
							data-toggle="tab">S'inscrire</a>
					</div>
					<div class="tab-content">
						<div class="tab-pane active" id="p1">
							<div class="container">
								<div class="row justify-content-center">
									<div class="col-lg-8 register-form">
										<form id="connection-form" class="form" action="ServletConnectionInscription?action=connectionUtilisateur" method="post">
											<div class="form-group">
												<input id="login" name="login" placeholder="pseudo/email" class="form-control" type="text">
												<span class="erreur">${form.erreurs['login']}</span>
											</div>
											<div class="form-group">
												<input id="password" name="motdepasse" placeholder="Mot de passe" class="form-control" type="password">
												<span class="erreur">${form.erreurs['password']}</span>
											</div>
											<div class="form-group">
												<input name="btnSubmit" class="btn btn-lg btn-primary btn-block" value="Se connecter" type="submit">
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="p2">
							<div class="container">
								<div class="row justify-content-center">
									<div class="col-lg-8 register-form">
										<form id="connection-form" class="form" action="ServletConnectionInscription?action=connectionUtilisateur" method="post">
											<div class="form-group">
												<input id="pseudo" name="pseudo" placeholder="pseudo" class="form-control" type="text" required>
											</div>
											<div class="form-group">
												<input id="nom" name="nom" placeholder="nom" class="form-control" type="text" required>
											</div>
											<div class="form-group">
												<input id="prenom" name="prenom" placeholder="prenom" class="form-control" type="text" required>
											</div>
											<div class="form-group">
												<input id="email" name="email" placeholder="email" class="form-control" type="email" required>
											</div>
											<div class="form-group">
												<input id="telephone" name="telephone" placeholder="telephone" class="form-control" type="tel" required>
											</div>
											<div class="form-group">
												<input id=rue name="rue" placeholder="rue" class="form-control" type="text" required>
											</div>
											<div class="form-group">
												<input id="codePostal" name="codePostal" placeholder="code postal" class="form-control" type="text" required>
											</div>
											<div class="form-group">
												<input id="ville" name="ville" placeholder="ville" class="form-control" type="text" required>
											</div>
											<div class="form-group">
												<input id="password" name="password" placeholder="Mot de passe" class="form-control" type="password" required>
											</div>
											<div class="form-group">
												<input id="confirmationPassword" name="confirmationPassword" placeholder="Confirmer Mot de Passe" class="form-control" type="password" required>
											</div>
											<div class="form-group">
												<input name="btnSubmit" class="btn btn-lg btn-primary btn-block" value="S'inscrire" type="submit">
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
                        <a class="mt-30" href="index.html"><img src="images/logo-blanc.png" alt="Logo"></a>
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
    

    <!-- Javascript import�s -->
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
