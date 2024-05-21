$(document).ready(function() {
    // Code à exécuter lors de l'ouverture de la page
    console.log("La page est chargée !");
    $.ajax({
        url: './ActionServlet?todo=getAllMediums',
        method: 'GET',
        dataType: 'json'
    })
    .done((res) => {
        //crée autant de div avec img que de mediums retournés
        for (let medium of res.mediums) {
            $('#mediums').append(`
                <div class="medium">
                    <img src="${medium.image}" alt="${medium.nom}">
                    <p>${medium.nom}</p>
                </div>
            `);//<button class="btn btn-primary" data-id="${medium.id}">Choisir</button>
        }

    })

    // Ajoutez votre code ici
});
