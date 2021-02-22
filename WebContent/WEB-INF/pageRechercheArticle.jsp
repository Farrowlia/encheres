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
    <title>Enchere ENI - Recherche</title>
    
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
		<div class="container search-form">
			<div class="row justify-content-center">
				<div class="panel-body col-lg-10">
					<form id="search-form" class="form"
						action="ServletRechercheArticle" method="post">
						<!--====== Voir les attribut ======-->
						<div class="row">
							<div class="form-group col-lg-4 col-md-6">
								<div class="input-group">
									<input id="keyword" name="keyword"
										placeholder="Que recherchez-vous ?" class="form-control"
										type="text">
								</div>
							</div>
							<div class="form-group col-lg-4  col-md-6">
								<div class="input-group">
									<select id="categorie" name="noCategorie" class="form-control">
										<!--====== Champ obligatoire ======-->
										<option value="0" selected="true">Cat�gories</option>
										<option value="1">Meuble</option>
										<option value="2">Electrom�nager</option>
										<option value="3">Vetements</option>
									</select>
								</div>
							</div>
							<div class="form-group col-lg-4">
								<input name="btnSubmit" class="btn btn-lg btn-primary btn-block"
									value="Rechercher" type="submit">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	
	<!--====== SELECTION ARTICLES ======-->

    <section class="divers-area">
        <div class="container">
            <div class="row justify-content-center">

                <div class="col-sm-12 col-md-6 col-lg-4"> <!-- article -->
                    <div class="row justify-content-center">
                        <a href="ServletGestionArticle?noArticle=1">
                            <div class="article">
                                <figure class="img-responsive">
                                    <img src="images/article-1.jpg">
                                    <figcaption>
                                        <span class="article-titre">Jouet table</span>
                                        <span class="article-prix"><strong>$18</strong></span>
                                        <span class="article-ville">Marseille</span>
                                        <div class="d-flex align-items-center justify-content-between rounded-pill bg-light px-3 py-2 mt-4">
                                            <p class="small mb-0"><i class="fa fa-picture-o mr-2"></i><span class="font-weight-bold">Termine dans</span></p>
                                            <div class="badge badge-danger px-3 rounded-pill font-weight-normal">17min</div>
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

                <div class="col-sm-12 col-md-6 col-lg-4"> <!-- article -->
                    <div class="row justify-content-center">
                        <a href="ServletGestionArticle?noArticle=2">
                            <div class="article">
                                <figure class="img-responsive">
                                    <img src="images/article-1.jpg">
                                    <figcaption>
                                        <span class="article-titre">Jouet table</span>
                                        <span class="article-prix"><strong>$18</strong></span>
                                        <span class="article-ville">Marseille</span>
                                        <div class="d-flex align-items-center justify-content-between rounded-pill bg-light px-3 py-2 mt-4">
                                            <p class="small mb-0"><i class="fa fa-picture-o mr-2"></i><span class="font-weight-bold">Termine dans</span></p>
                                            <div class="badge badge-danger px-3 rounded-pill font-weight-normal">17min
                                            </div>
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

                <div class="col-sm-12 col-md-6 col-lg-4"> <!-- article -->
                    <div class="row justify-content-center">
                        <a href="ServletGestionArticle?noArticle=3">
                            <div class="article">
                                <figure class="img-responsive">
                                    <img src="images/article-1.jpg">
                                    <figcaption>
                                        <span class="article-titre">Jouet table</span>
                                        <span class="article-prix"><strong>$18</strong></span>
                                        <span class="article-ville">Marseille</span>
                                        <div class="d-flex align-items-center justify-content-between rounded-pill bg-light px-3 py-2 mt-4">
                                            <p class="small mb-0"><i class="fa fa-picture-o mr-2"></i><span class="font-weight-bold">Termine dans</span></p>
                                            <div class="badge badge-danger px-3 rounded-pill font-weight-normal">17min
                                            </div>
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
                
                <div class="col-sm-12 col-md-6 col-lg-4"> <!-- article -->
                    <div class="row justify-content-center">
                        <a href="ServletGestionArticle?noArticle=4">
                            <div class="article">
                                <figure class="img-responsive">
                                    <img src="images/article-1.jpg">
                                    <figcaption>
                                        <span class="article-titre">Jouet table</span>
                                        <span class="article-prix"><strong>$18</strong></span>
                                        <span class="article-ville">Marseille</span>
                                        <div class="d-flex align-items-center justify-content-between rounded-pill bg-light px-3 py-2 mt-4">
                                            <p class="small mb-0"><i class="fa fa-picture-o mr-2"></i><span class="font-weight-bold">Termine dans</span></p>
                                            <div class="badge badge-danger px-3 rounded-pill font-weight-normal">17min
                                            </div>
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
