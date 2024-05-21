$(document).ready(function() {
    console.log("La page est chargée !");
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
    })
    .fail((xhr, status, error) => {
        console.error(`Error fetching mediums: ${error}`);
    });
});