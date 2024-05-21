$(document).ready(function() {
    console.log("La page est chargée !");
    $.ajax({
        url: './ActionServlet?todo=getAllMediums',
        method: 'GET',
        dataType: 'json'
    })
    .done((res) => {
        for (let medium of res.mediums) {
            const mediumElement = $(`
                <div class="medium">
                    <img src="${medium.image}" alt="${medium.nom}">
                    <p>${medium.nom}</p>
                    <button class="btn btn-primary" data-id="${medium.id}">Choisir</button>
                </div>
            `);
            $('#mediums').append(mediumElement);

            // Add event listener to the button
            mediumElement.find('button').on('click', function() {
                const mediumId = $(this).data('id');
                const confirmPopup = `
                    <div class="popup">
                        <h2>Confirmer la réservation</h2>
                        <p>Voulez-vous réellement réserver un rendez-vous avec ${medium.nom} ?</p>
                        <button class="btn btn-primary" id="confirm-btn">Confirmer</button>
                        <button class="btn btn-secondary" id="cancel-btn">Annuler</button>
                    </div>
                `;

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
    })
    .fail((xhr, status, error) => {
        console.error(`Error fetching mediums: ${error}`);
    });
});