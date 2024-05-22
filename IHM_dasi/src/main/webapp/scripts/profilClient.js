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
        $('#adresse').val(res.client.adresse);
        $('#telephone').val(res.client.telephone);

        $('#zodiaque').html(res.client.profil.signeZodiaque);
        $('#chinois').html(res.client.profil.signeChinois);
        $('#couleur').html(res.client.profil.couleur);
        $('#animal').html(res.client.profil.totem);

    })

    $('#enregistrer').click(function() {
        console.log("debut")
    
        var nom = $('#nom').val();
        var prenom = $('#prenom').val();
        var mdp = $('#motdepasse').val();
        var mail = $('#email').val();
        var adresse = $('#adresse').val();
        var telephone = $('#telephone').val();
    
        console.log("envoi de la modif")
        data = {
            todo : "setInfosClient",
            nom : $('#nom').val(),
            prenom : $('#prenom').val(),
            email : $('#email').val(),
            mdp : $('#motdepasse').val(),
            tel : $('#telephone').val(),
            adresse: $("#adresse").val(),
        }
        console.log(data);
        $.ajax({
            url: './ActionServlet',
            method: 'POST',
            data: data,
            dataType: 'json'
        })
        .done((res) => {
            console.log(res);
            // Traitement de la réponse de l'API
        })
        .fail((xhr, status, error) => {
            console.log(error);
            // Traitement d'erreur
        });
    });
});

