
$(document).ready(() => {
    $.ajax({
        url: './ActionServlet?todo=getConsultMedium',
        method: 'GET',
        dataType: 'json'
    })
    .done((res) => {
        console.log(res);

            // Petite popup Google Maps
        var infowindow = makeInfoWindow('');

        var position = {lat: 46.527840,  lng: 2.416970};

        for (var i = 0; i < 2; i++) {

            
            if (i % 2 === 0) {
                // image pour marker personnalisé
                
            }

            var marker = new google.maps.Marker({
                map: googleMapInstance,
                position: {lat: position.lat + (Math.random() - 0.5) / 10.0, lng: position.lng + 2 * (Math.random() - 0.5) / 10.0},
                title: 'Endroit #' + i
            });

            attachInfoWindow(
                    marker, infowindow,
                    '<div><strong><a href="./endroit.html?' + i + '">Endroit #' + i + '</a></strong><br/>Ceci est l\'endroit charmant numéro ' + i + '<br/>' + 'Incroyable !' + '</div>'
                    );
        }
        
    });

    


    $("#btnStats").on("click", () => {
        $.ajax({
            url: './ActionServlet?todo=getConsultMedium',
            method: 'GET',
            dataType: 'json'
        })
        .done((res) => {
            console.log(res);
            const confirmPopup = `
                <div class="popup">
                    <p class ="titrePopUp">Graphiques</p>
                    <div id="container-1" class="highcharts-container"></div>
                    <div id="container-2" class="highcharts-container"></div>
                    <button id="closeBtn" class="btn">Fermer</button>
                </div>
                
                `;
                // Create a custom modal popup
                const popupContainer = $('<div class="modal-popup"></div>');
                popupContainer.html(confirmPopup);
                $('body').append(popupContainer);
                const data = res; // votre tableau de tableau reçu

                const formattedData = data.map(([name, y]) => ({ name, y }));
                var finalList; 
                
                console.log(formattedData);
                console.log("printed");
                var oldProportionChartData = {
                    label: 'RDV',
                    data: [
                        {
                            name: 'Lucie',
                            y: 20
                        },
                        {
                            name: 'Marcel',
                            y: 5
                        },
                        {
                            name: 'Noémie',
                            y: 7
                        },
                        {
                            name: 'Oscar',
                            y: 13
                        }]
                };

                var proportionChartData = {
                    label: 'RDV',
                    data: formattedData
                };

                console.log("avant")
                console.log(oldProportionChartData);
                console.log("après")
                console.log(proportionChartData);


                function buildPieChart(container, graphData) {

                    Highcharts.chart(container, {
                
                        chart: {
                            type: 'pie'
                        },
                        title: {
                            text: 'Répartition des médiums sélectionnés'
                        },
                        subtitle: {
                            text: ''
                        },
                        credits: {
                            enabled: false
                        },
                        series: [{name: graphData.label, data: graphData.data}]
                    });
                }

                buildPieChart('container-2', proportionChartData);

                $("#closeBtn").on("click",()=> {
                    popupContainer.remove();
                });
                // $("#closeBtn2").on("click",()=> {
                //     popupContainer.empty();
                // });
                // $("#closeBtn3").on("click",()=> {
                //     popupContainer.detach();
                // });


        }).fail((res) => {
            console.log("Erreur");
            console.loge(res);
        });

    
    
    
    
    
    });
});



//getConsultMedium
function buildBarChart(container, graphData) {

                Highcharts.chart(container, {

                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Température en Amphi'
                    },
                    subtitle: {
                        text: 'Source: a priori'
                    },
                    xAxis: {
                        categories: graphData.labels
                    },
                    yAxis: {
                        title: {
                            text: 'Température (°C)'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    credits: {
                        enabled: false
                    },
                    series: [{name: 'Données', data: graphData.data}]
                });
            }

var googleMapInstance = null;

function makeInfoWindow(title) {
    return new google.maps.InfoWindow({
        content: '<div>Information: ' + title + '</div>'
    });
}

function attachInfoWindow(marker, infowindow, htmlDescription) {
    marker.addListener('click', function () {

        infowindow.setContent(htmlDescription);
        infowindow.open(googleMapInstance, this);
    });
}

function initMap() {

    googleMapInstance = new google.maps.Map(document.getElementById('map'), {
        center:{lat: 47.027840,  lng: 1.816970},
        zoom: 6
    });

    var infowindow = makeInfoWindow('');

    // var marker = new google.maps.Marker({
    //     map: googleMapInstance,
    //     position: {lat: 45.782122, lng: 4.872735},
    //     title: "Département IF, INSA de Lyon"
    
    // });

    // marker.addListener('click', function () {

    //     infowindow.setContent('<div>Information: ' + marker.title + '</div>');
    //     infowindow.open(googleMapInstance, marker);
    // });

    // var marker2 = new google.maps.Marker({
    //     map: googleMapInstance,
    //     position: {lat: 45.782592, lng: 4.878238},
    //     title: "Entrée principale, INSA de Lyon"
        
    // });

    // marker2.addListener('click', function () {

    //     infowindow.setContent('<div>Information: ' + marker2.title + '</div>');
    //     infowindow.open(googleMapInstance, marker2);
    // });

    // // Simuler un appel AJAX (attente 5s)
    // setTimeout(
    //         generateMarkers,
    //         5000
    //         );
}

function generateMarkers() {

    // Petite popup Google Maps
    var infowindow = makeInfoWindow('');

    var position = {lat: 46.527840,  lng: 2.416970};

    for (var i = 0; i < 2; i++) {

        
        if (i % 2 === 0) {
            // image pour marker personnalisé
            
        }

        var marker = new google.maps.Marker({
            map: googleMapInstance,
            position: {lat: position.lat + (Math.random() - 0.5) / 10.0, lng: position.lng + 2 * (Math.random() - 0.5) / 10.0},
            title: 'Endroit #' + i
        });

        attachInfoWindow(
                marker, infowindow,
                '<div><strong><a href="./endroit.html?' + i + '">Endroit #' + i + '</a></strong><br/>Ceci est l\'endroit charmant numéro ' + i + '<br/>' + 'Incroyable !' + '</div>'
                );
    }

}
