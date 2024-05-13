
var API_URL = 'http://localhost:8080/IHM_dasi/ActionServlet';

$(document).ready(() => {
    $("#boutonConnexion").on("click", () => {
        // Get the email and password from the form using jQuery
        const email = $('#email').val();
        const mdp = $('#mdp').val();
        $.ajax({
            url: './ActionServlet',
            method: 'POST',
            data: {
                todo: 'connecterClient',
                email: email,
                mdp: mdp
            },
            dataType: 'json'
        })
                .done((res) => {
                    console.log(res);
                    if (res.connexion == false) {
                        $.ajax({
                            url: './ActionServlet',
                            method: 'POST',
                            data: {
                                todo: 'connecterEmploye',
                                email: email,
                                mdp: mdp
                            },
                            dataType: 'json'
                        })
                        .done((res) => {
                            console.log(res);
                            if (res.connexion == false) {
                                alert("Erreur de connexion");
                                return;
                            }
                            window.location.href = "profilEmploye.html";
                        })
                    }
                    window.location.href = "profilClient.html";
                })
                .fail((res) => {
                    console.error(res);
                });
    });

}
);
