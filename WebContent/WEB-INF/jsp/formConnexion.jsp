<div class="container forget-password">
		<div class="row">
			<div class="col-md-12 col-md-offset-4">

				<div class="panel-body">
					<div class="text-center">
						<form id="register-form" role="form" autocomplete="off" class="form" method="post" action="${pageContext.request.contextPath}/ServletAuthentification }">
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-envelope color-blue"></i></span> 
										<input id="login" name="login" placeholder="Mail ou pseudo" class="form-control" type="text" value="${sessionScope['user'].pseudo} }">
										<span class="erreur">${form.erreurs['login']}</span>
										<input id="pwd" name="pwd" placeholder="mot de passe" class="form-control" type="text">
										<span class="erreur">${form.erreurs['pwd']}</span>
								</div>
							</div>
							<div class="form-group">
								<input name="Connection" class="btn btn-lg btn-primary btn-block btnForget" value="Connexion" type="submit">
								<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
								
							</div>

						</form>
					</div>
				</div>

			</div>
		</div>
	</div>