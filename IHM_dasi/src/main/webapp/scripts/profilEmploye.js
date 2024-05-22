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
    
    $("#formModifier").on("submit", (event) => {
        event.preventDefault();
    });
});

