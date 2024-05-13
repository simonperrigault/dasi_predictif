
var API_URL = 'https://localhost:8080/IHM_dasi/ActionServlet';

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
            window.location.href = 'dashboard.html';
        })
        .fail(function(error) {
            // Handle the error response
            console.error(error);
            // Display an error message to the user
            $('#error-message').text('Invalid email or password');
        });

    
}