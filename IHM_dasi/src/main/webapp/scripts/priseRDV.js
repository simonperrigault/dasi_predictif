$(document).ready(function() {
    console.log("La page est chargée !");
    $.ajax({
        url: './ActionServlet?todo=getAllMediums',
        method: 'GET',
        dataType: 'json'
    })
    .done((res) => {
        for (let element of res) {
            const mediumElement = $(`
                <div class="medium">
                    <img class="imgMedium" src="${element.medium.image}" alt="${element.medium.denomination}">
                    <p>${element.medium.denomination}</p>
                    <button class="btn btn-primary" data-id="${element.medium.id}">Choisir</button>
                </div>
            `);
            $('#listeMedium').append(mediumElement);

            // Add event listener to the button
            mediumElement.find('button').on('click', function() {
                const mediumId = $(this).data('id');
                const confirmPopup = `
                    <div class="popup">
                        <h2>Confirmer la réservation</h2>
                        <p>Voulez-vous réellement réserver un rendez-vous avec ${element.medium.denomination} ?</p>
                        <button class="btn btn-primary" id="confirm-btn">Confirmer</button>
                        <button class="btn btn-secondary" id="cancel-btn">Annuler</button>
                    </div>
                `;

                // Create a custom modal popup
                const popupContainer = $('<div class="modal-popup"></div>');
                popupContainer.html(confirmPopup);
                $('body').append(popupContainer);

                // Add event listeners to the buttons
                popupContainer.find('#confirm-btn').on('click', function() {
                    $.ajax({
                        url: './ActionServlet?todo=creerConsultation',
                        method: 'POST',
                        data: { mediumId: mediumId },
                        dataType: 'json'
                    })
                    .done((res) => {
                        console.log(`Appointment booked with medium ${mediumId}!`);
                    })
                    .fail((xhr, status, error) => {
                        console.error(`Error booking appointment: ${error}`);
                    });
                    popupContainer.remove();
                });

                popupContainer.find('#cancel-btn').on('click', function() {
                    popupContainer.remove();
                });
            });
        }
    })
    .fail((xhr, status, error) => {
        console.error(`Error fetching mediums: ${error}`);
    });
});