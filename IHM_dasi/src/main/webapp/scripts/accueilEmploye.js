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
            $("#appelEnCours").html("");
            $("#appelEnCours").append("<h2>Appel à venir :</h2>");
            $("#appelEnCours").append(`<p>${res.client.nom}</p>`);
            $("#appelEnCours").append(`<p>${res.client.prenom}</p>`);
            $("#appelEnCours").append(`<p>Né le : ${res.client.dateNaissance}</p>`);
            $("#appelEnCours").append("<button id='boutonProfil'>Profil astral</button>");
            $("#appelEnCours").append("<h3>Commentaires</h3>");
            $("#appelEnCours").append("<button id='boutonValider'>Démarrer la consultation</button>");
            
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
                        $("#appelEnCours").append("<button id='boutonTerminer'>Terminer la consultation</button>");
                        $("#appelEnCours").append("<button id='boutonAide'>Avoir de l'aide</button>");

                        
                        $("#boutonTerminer").on("click", () => {
                            $("#popupCommentaire").modal("show");
                        });
                        $("#boutonQuitterCommentaire").on("click", () => {
                            $("#popupCommentaire").modal("hide");
                        });
                        
                        $("#boutonAide").on("click", () => {
                            $("#popupAide").modal("show");
                        });
                        $("#boutonQuitterAide").on("click", () => {
                            $("#popupAide").modal("hide");
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