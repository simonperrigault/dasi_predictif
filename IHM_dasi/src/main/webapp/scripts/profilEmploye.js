$(document).ready(function() {
    // Code à exécuter lors de l'ouverture de la page
    console.log("La page est chargée !");
    $.ajax({
        url: './ActionServlet?todo=getInfosEmploye',
        method: 'GET',
        dataType: 'json'
    })
    .done((res) => {
        console.log(res);
        if (res.connexion === false) {
            console.log("Erreur de connexion");
        }
        else {
            $('#nom').val(res.employe.nom);
            $('#prenom').val(res.employe.prenom);
            $('#motdepasse').val(res.employe.mdp);
            $('#email').val(res.employe.mail);
            $('#telephone').val(res.employe.telephone);

            $("#genre").val(res.employe.genre);
        }
        
    });

    $('#formModifier').click(function() {
        console.log("debut")
    
        var nom = $('#nom').val();
        var prenom = $('#prenom').val();
        var mdp = $('#motdepasse').val();
        var mail = $('#email').val();
        var telephone = $('#telephone').val();
    
        console.log("envoi de la modif")
        data = {
            todo : "setInfosEmploye",
            nom : $('#nom').val(),
            prenom : $('#prenom').val(),
            email : $('#email').val(),
            mdp : $('#motdepasse').val(),
            tel : $('#telephone').val(),
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
    
    $("#formModifier").on("submit", (event) => {
        event.preventDefault();
    });
});

