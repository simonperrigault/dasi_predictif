var openAide = false;
$(document).ready(() => {
    
        
    $.ajax({
        url: './ActionServlet?todo=getConsultationCourante',
        method: 'GET',
        dataType: 'json'
    })
    .done((res) => {
        console.log(res);
        if (res.trouve === false) {
            $("#appelEnCours").html("<h2>Aucun appel en cours</h2>");
        }
        else {
            // $("#appelEnCours").html("");
            // $("#appelEnCours").append("<h2>Appel à venir :</h2>");
            // $("#appelEnCours").append(`<p>${res.client.nom}</p>`);
            // $("#appelEnCours").append(`<p>${res.client.prenom}</p>`);
            // $("#appelEnCours").append(`<p>Né le : ${res.client.dateNaissance}</p>`);
            // $("#appelEnCours").append("<button id='boutonProfil'>Profil astral</button>");
            // $("#appelEnCours").append("<h3>Commentaires</h3>");
            // $("#appelEnCours").append("<button id='boutonValider'>Démarrer la consultation</button>");

            const tab = $(`
            <h2>Appel à venir :</h2>
            <p class = "infoClient">${res.client.nom}</p>
            <p class = "infoClient">${res.client.prenom}</p>
            <p class = "infoClient">Né le : ${res.client.dateNaissance}</p>
            <button id='boutonProfil' class = "btn profil">Profil astral</button>
            <h3>Commentaires : </h3>
            <div class ="headOfTab">
                <div class = "title emp">Employé</div>
                <div class = "title Com">Commentaire</div>
            </div>
            <div class = "tableauCom" id="addCom">
                <div class = "com">
                    <p class="auteur">Joussot Gabin</p> <p class="txt">Un service renvoyant tous les commentaires sur un client aurait été pratique</p>
                </div>
            </div>
            <button id='boutonValider' class = "btn demarrer">Démarrer la consultation</button>
            `);
            $("#appelEnCours").append(tab);


            // // dans l'idée si un service correcte avait été fait
            // for(let element of res.consultation)
            // {
            //     $("#addComm").append(`
            //     <div class = "com">
            //         <p class="auteur">${element.employe.denomination} : </p> <p class="txt">${element.commentaire}</p>
            //     </div>         
            //     `);
            // }


            
            $("#boutonProfil").on("click", () => {
                alert(`Profil astral : \n
                        Couleur : ${res.client.profil.couleur}\n
                        Signe zodiaque : ${res.client.profil.signeZodiaque}\n
                        Signe chinois : ${res.client.profil.signeChinois}\n
                        Totem : ${res.client.profil.totem}`);
            });
            
            $("#boutonValider").on("click", () => {
                $.ajax({
                    url: './ActionServlet?todo=validerConsultation',
                    method: 'GET',
                    dataType: 'json'
                })
                .done((res) => {
                    
                    if (res.res === false) {
                        alert("Erreur pendant la validation de la consultation");

                    }
                    else {
                        $("#boutonValider").remove();
                        $("#appelEnCours").append("<button id='boutonTerminer' class = 'btn Terminer'>Terminer la consultation</button>");
                        $("#appelEnCours").append("<button id='boutonAide' class = 'btn Aide' >Avoir de l'aide</button>");

                        
                        $("#boutonTerminer").on("click", () => {
                            $("#popupCommentaire").modal("show");
                            $('#hider').addClass('show');
                            $('#popupCommentaire').addClass('show');
                        });
                        $("#boutonQuitterCommentaire").on("click", () => {
                            $("#popupCommentaire").modal("hide");
                            $('#popupCommentaire').removeClass('show');
                            $('#hider').removeClass('show');
                        });
                        
                        $("#boutonAide").on("click", () => {
                            if (!openAide)
                            {
                                $("#popupAide").modal("show");
                                $('#popupAide').addClass('show');
                                $('#hider').addClass('show');
                                openAide = true; 
                            }
                            else{
                                $("#popupAide").modal("hide");
                                $('#hider').removeClass('show');
                                $('#popupAide').removeClass('show');
                                openAide = false; 
                            }
                            
                        });
                        $("#boutonQuitterAide").on("click", () => {
                            $("#popupAide").modal("hide");
                            $('#hider').removeClass('show');
                            $('#popupAide').removeClass('show');
                            openAide = false; 
                        });
                        
                        $("#formCommentaire").on("submit", (event) => {
                            event.preventDefault();
                            $.ajax({
                                url: './ActionServlet?todo=terminerConsultation',
                                method: 'POST',
                                data: {
                                    commentaire: $("#commentaire").val()
                                },
                                dataType: 'json'
                            }).done((res) => {
                                if (res.res === false) {
                                    console.log("Erreur lors de l'enregistrement du commentaire");
                                }
                                else {
                                    console.log("Commentaire enregistré");
                                    location.reload();
                                }
                            }).fail((res) => {
                                console.log("Erreur");
                                console.log(res);
                            });
                        });
                        
                        $("#formAide").on("submit", (event) => {
                            event.preventDefault();
                            $.ajax({
                                url: './ActionServlet?todo=avoirAide',
                                method: 'POST',
                                data: {
                                    amour: $("#amour").val(),
                                    sante: $("#sante").val(),
                                    travail: $("#travail").val()
                                },
                                dataType: 'json'
                            }).done((res) => {
                                if (res.res === false) {
                                    console.log("Erreur lors de l'aide");
                                }
                                else {
                                    console.log("Aide reçue");
                                    $("#paraAmour").html(`Amour : ${res.amour}`);
                                    $("#paraSante").html(`Santé : ${res.santé}`);
                                    $("#paraTravail").html(`Travail : ${res.travail}`);
                                }
                            }).fail((res) => {
                                console.log("Erreur");
                                console.log(res);
                            });
                        });
                    }
                });
            });
        }
        

    });
});