$(document).ready(function() {
    $.ajax({
        url: './ActionServlet?todo=getAllMediums',
        method: 'GET',
        dataType: 'json'
    })
    .done((res) => {
        var i = 0;
        for (let element of res) {
            if (i >= 3)
            {
                break;
            }
            const mediumElement = $(`
                <div class = "medium">
                    <img class = "imgMedium" src="${element.medium.image}" alt="${element.medium.denomination}">
                    <p id="nom1">${element.medium.denomination}</p>
                    <p id="description1">${element.medium.presentation}</p>
                </div>
            `);
            $('#containerMedium').append(mediumElement);
            i++;
        }
        console.log("je suis vant");
        $.ajax({
            url: './ActionServlet?todo=getAllConsul',
            method: 'GET',
            dataType: 'json'
        })
        .done((res2) => {
            console.log("je suis apres");
            var i = 0;
            for (let element of res2) {
                if (i >= 2)
                {
                    break;
                }
                const avisElement = $(`
                <div class = "avis">
                <div class = "user">
                    <p id="nomClient">${res2[i].client.nom} ${res2[i].client.prenom}   </p>
                    <img class ="imgUser" src="img/user.jpg" alt="Image client 1">
                </div>
                <div class="comm">
                    
                    <img style = "display: block; margin-right: auto;" src="etoiles.jpg" alt="etoiles" id = "etoiles">
                    <p id="commentaire">Voici l'avis du client enfait l'af</p>
                </div>
            </div>
                `);
                $('#containerMedium').append(avisElement);
                i++;
            }
        })
        .fail((xhr, status, error) => {
            console.error(`Error fetching mediums: ${error}`);
        });
    })
    .fail((xhr, status, error) => {
        console.error(`Error fetching mediums: ${error}`);
    });
    
});