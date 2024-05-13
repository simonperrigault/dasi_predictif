
var API_URL = 'http://localhost:8080/IHM_dasi/ActionServlet';

<<<<<<< Updated upstream
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
=======
function ConnectionClient() {
    // Get the email and password from the form using jQuery
    const email = $('#email').val();
    const password = $('#mdp').val();
    // Make a POST request to the API URL
    $.post(API_URL, { todo : 'connecterClient', email: email, mdp: mdp })
        .done(function(response) {
            // Handle the successful response
            console.log(response);
            // Redirect to the desired page
            //window.location.href = 'dashboard.html';
>>>>>>> Stashed changes
        })
                .done((res) => {
                    console.log(res);
                    window.location.href = "profilEmploye.html";
                })
                .fail((res) => {
                    console.error(res);
                });
    });

<<<<<<< Updated upstream
}
);
=======

}
>>>>>>> Stashed changes
