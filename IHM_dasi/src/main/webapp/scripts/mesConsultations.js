$(document).ready(() => {
    $.ajax({
        url: './ActionServlet?todo=getAllConsul',
        method: 'GET',
        dataType: 'json'
    })
    .done((res) => {
    console.log(res);
    for (let elem of res) {
        const consElement = $(`
        <div class="consul">
                    <div class = "medium">
                        <img class="imgMedium" src="${elem.medium.image}" alt="${elem.medium.denomination}">
                        <p>${elem.medium.denomination}</p>
                    </div>
                    <div class = "infoConso">
                        <p> Durée : ${elem.consultation.duree}</p>
                    </div>
        </div>
    `);
    $('#containerConsultations').append(consElement);
    }



    });


});