<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier Profil</title>
</head>
<body>
<form method="post" action="modifier">
            <fieldset>
                <legend>Modifier Profil</legend>
                
                <label for="pseudo">Pseudo</label>
                <input type="text" id="email" name="pseudo" value="" size="20" maxlength="60" />
                <br />
                
                <label for="prenom">Prénom</label>
                <input type="text" id="prenom" name="prenom" value="" size="20" maxlength="60" />
                <br />
                
                <label for="nom">Nom</label>
                <input type="text" id="nom" name="nom" value="" size="20" maxlength="60" />
                <br />
                
                <label for="email">Adresse email</label>
                <input type="text" id="email" name="email" value="" size="20" maxlength="60" />
                <br />
                
                <label for="telephone">Télèphone</label>
                <input type="text" id="telephone" name="telephone" value="" size="20" maxlength="60" />
                <br />
                
                <label for="rue">Rue</label>
                <input type="text" id="rue" name="rue" value="" size="20" maxlength="60" />
                <br />
                
                <label for="ville">Ville</label>
                <input type="text" id="ville" name="ville" value="" size="20" maxlength="60" />
                <br />
                
                  <label for="codePostal">Code Postal</label>
                <input type="text" id="codePostal" name="codePostal" value="" size="20" maxlength="60" />
                <br />

                <label for="motdepasse">Mot de passe actuel <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <br />
                
              
                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <br />

                 <label for="confirmation">Nouveau mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <br />
                
                <p> Credit   640</p>

                <input type="submit" value="Enregistrer" class="sansLabel" />
                <br />
                
                <input type="submit" value="supprimermoncompte" class="sansLabel" />
                <br />
            </fieldset>
        </form>
</body>
</html>