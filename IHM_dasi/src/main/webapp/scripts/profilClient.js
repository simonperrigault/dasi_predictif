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
        $('#nom').val(res.nom);
        $('#prenom').val(res.prenom);
        $('#motdepasse').val(res.mdp);
        $('#email').val(res.email);
        //$('#adresse').val(res.adresse);
        $('#telephone').val(res.telephone);

        $('#zodiaque').val(res.signeZodiaque);
        $('#chinois').val(res.signeChinois);
        $('#couleur').val(res.couleur);
        $('#animal').val(res.totem);

        

    })

    // Ajoutez votre code ici
});
