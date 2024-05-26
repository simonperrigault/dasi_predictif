
$(document).ready(() => {
    $.ajax({
        url: './ActionServlet?todo=getAllConsul',
        method: 'GET',
        dataType: 'json'
    })
    .done((res) => {
        console.log("pos GPS");
        console.log(res);

            // Petite popup Google Maps
        var infowindow = makeInfoWindow('');
        // console.log("client : "+res[0].client.id);
        // console.log(JSON.stringify(res.client, null, 2));
        // console.log("lat : "+res.client.latitude+" long : "+res.client.longitude);
        // var newpos = {lat : res.client.latitude, lng : res.client.longitude};
        // console.log("new pose: " + newpos);


        var position = {lat: 46.527840,  lng: 2.416970};
        var titre = "";

        for (var i = 0; i < res.length; i++) {

            
            position = {lat : res[i].client.latitude, lng: res[i].client.longitude};
            titre = res[i].client.nom;

            var marker = new google.maps.Marker({
                map: googleMapInstance,
                position: {lat: position.lat, lng: position.lng },
                title: titre
            });

            attachInfoWindow(
                    marker, infowindow,
                    '<div style = "display:flex; flex-direction: row; "> <div class = "infos">'+
                    '<p>Client : '+res[i].client.nom+' '+res[i].client.prenom+' </p>'+
                    '<p>Employé : '+res[i].employe.nom+' '+res[i].employe.prenom+' </p>'+
                    '<p>Date : '+res[i].consultation.date+' </p>'+
                    '<p>Durée : '+res[i].consultation.duree+' </p>'+
                   
                    ' </div>   <div class = "commentaire" style = " margin-left : 3px;padding : 5px;border: solid 2px black;">Commentaire :<br/>'+res[i].consultation.commentaire+'  </div> </div>'
                    //<strong><a href="./endroit.html?' + i + '">Endroit #' + i + '</a></strong><br/>Ceci est l\'endroit charmant numéro ' + i + '<br/>' + 'Incroyable !' + '
                    );
        }
        
    });

    


    $("#btnStats").on("click", () => {
        $.ajax({
            url: './ActionServlet?todo=getConsultMedium',
            method: 'GET',
            dataType: 'json'
        })
        .done((res1) => {
            $.ajax(
                {
                    url: './ActionServlet?todo=getRepartitionEmployeClient',
                    method: 'GET',
                    dataType: 'json'
                }
            ).done((res2)=> {
                console.log(res1);
                console.log(res2);
            const confirmPopup = `
                <div class="popup">
                    <p class ="titrePopUp">Graphiques</p>
                    <div style = "display:flex; flex-direction:row;">
                        <div style = "width: 50%;"  id="container-1" class="highcharts-container"></div>
                        <div style = "width: 50%;"  id="container-2" class="highcharts-container"></div>
                    </div>
                    <button id="closeBtn" class="btn">Fermer</button>
                </div>
                
                `;
                // Create a custom modal popup
                const popupContainer = $('<div class="modal-popup"></div>');
                popupContainer.html(confirmPopup);
                $('body').append(popupContainer);
                const data1 = res1; // votre tableau de tableau reçu
                const data2 = res2; // votre tableau de tableau reçu

                const formattedData1 = data1.map(([name, y]) => ({ name, y }));
                const formattedData2 = data2.map(([name,client, y]) => ({ name, y }));
                
                console.log(formattedData1);
                console.log(formattedData2);
                console.log("printed");
               

                var proportionChartData1 = {
                    label: 'RDV',
                    data: formattedData1
                };

                var proportionChartData2 = {
                    label: 'RDV',
                    data: formattedData2
                };



                function buildPieChart(container, graphData, text) {

                    Highcharts.chart(container, {
                
                        chart: {
                            type: 'pie'
                        },
                        title: {
                            text: text
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

                buildPieChart('container-2', proportionChartData1,"Répartition des médiums sélectionnés");
                buildPieChart('container-1', proportionChartData2, "Répartition des appels employées");

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
            console.log("Erreur2");
            console.loge(res2);
        });


            }).fail((res) => {
                console.log("Erreur1");
                console.loge(res1);
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

   
}

