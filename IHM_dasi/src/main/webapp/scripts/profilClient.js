$(document).ready(function() {
    // Code à exécuter lors de l'ouverture de la page
    console.log("La page est chargée !");
    $.ajax({
        url: './ActionServlet?todo=getInfosClient',
        method: 'GET',
        dataType: 'json'
    })
    .done((res) => {
        console.log(res);
        $('#nom').val(res.client.nom);
        $('#prenom').val(res.client.prenom);
        $('#motdepasse').val(res.client.mdp);
        $('#email').val(res.client.mail);
        //$('#adresse').val(res.client.adresse);
        $('#telephone').val(res.client.telephone);

        $('#zodiaque').html(res.client.profil.signeZodiaque);
        $('#chinois').html(res.client.profil.signeChinois);
        $('#couleur').html(res.client.profil.couleur);
        $('#animal').html(res.client.profil.totem);

    })

    // Ajoutez votre code ici
});
