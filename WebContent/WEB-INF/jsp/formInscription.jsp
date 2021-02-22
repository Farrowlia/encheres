<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
        <!-- <link type="text/css" rel="stylesheet" href="form.css" /> -->
<!-- créer le style des erreurs -->
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/ServletInscription">
            <fieldset>
                <legend>Inscription - Mon profil</legend>

                <label for="pseudo">Pseudo<span class="requis">*</span></label>
                <input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" size="20" maxlength="50" />
                <span class="erreur">${form.erreurs['pseudo']}</span>
   				<label for="nom">Nom<span class="requis">*</span></label>
                <input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.nom}"/>" size="20" maxlength="30" />
                <br />

                <label for="prenom">Prénom<span class="requis">*</span></label>
                <input type="text" id="prenom" name="prenom" value="<c:out value="${utilisateur.prenom}"/>" size="20" maxlength="30" />
                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="text" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
				<span class="erreur">${form.erreurs['email']}</span>
                <br />
                
                <label for="telephone">Téléphone</label>
                <input type="text" id="telephone" name="telephone" value="<c:out value="${utilisateur.telephone}"/>" size="15" maxlength="15" />
				<span class="erreur">${form.erreurs['telephone']}</span>
                <label for="rue">Rue<span class="requis">*</span></label>
                <input type="text" id="rue" name="rue" value="<c:out value="${utilisateur.rue}"/>" size="40" maxlength="50" />
                <br />

                <label for="codePostal">Code postal<span class="requis">*</span></label>
                <input type="text" id="codePostal" name="codePostal" value="<c:out value="${utilisateur.codePostal}"/>" size="15" maxlength="15" />
                <span class="erreur">${form.erreurs['codePostal']}</span>
                <label for="ville">Ville<span class="requis">*</span></label>
                <input type="text" id="ville" name="ville" value="<c:out value="${utilisateur.ville}"/>" size="30" maxlength="30" />
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="30" />
                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="30" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                <br />

                <input type="submit" value="Inscription" class="sansLabel" />
                <input type="submit" value="Cancel" name="cancel">
                <br />

            </fieldset>
        </form>


</body>
</html>