$(document).ready(function() {
    // Code à exécuter lors de l'ouverture de la page
    console.log("La page est chargée !");
    $.ajax({
        url: './ActionServlet?toto=getInfosClient',
        method: 'GET',
        dataType: 'json'
    })
    .done((res) => {

    })

    // Ajoutez votre code ici
});
