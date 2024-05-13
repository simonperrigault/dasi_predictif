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
        $('#email').val(res.client.email);
        //$('#adresse').val(res.client.adresse);
        $('#telephone').val(res.client.telephone);

        $('#zodiaque').val(res.client.signeZodiaque);
        $('#chinois').val(res.client.signeChinois);
        $('#couleur').val(res.client.couleur);
        $('#animal').val(res.client.totem);

    })

    // Ajoutez votre code ici
});
