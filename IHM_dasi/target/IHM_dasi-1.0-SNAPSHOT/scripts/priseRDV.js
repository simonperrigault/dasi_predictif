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
                <div class="imgPopUp">
                    <p>Mr. M</p>
                    <img src="${element.medium.image}" alt="Medium">
                </div>
                <div class="txtPopUp">
                    <p class ="titrePopUp">Confirmer la réservation</p>
                    <p>${element.medium.presentation} ?</p>
                    <div class = "containsBoutons" >
                        <button class="btn btn-primary" id="confirm-btn">Confirmer</button>
                        <button class="btn btn-secondary" id="cancel-btn">Annuler</button>
                    </div>
                </div>
            </div>
                `;
                $('body').css('background-color', 'rgb(137, 137, 137)');

                // Create a custom modal popup
                const popupContainer = $('<div class="modal-popup"></div>');
                popupContainer.html(confirmPopup);
                $('body').append(popupContainer);


               // Create a jQuery UI dialog for the popup
                    const dialog = $(confirmPopup).dialog({
                    modal: true,
                    title: 'Confirmation',
                    buttons: {
                        confirm: function() {
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
                            $(this).dialog('close');
                        },
                        cancel: function() {
                            $(this).dialog('close');
                        }
                    }
                });
            });
        }
    });
});