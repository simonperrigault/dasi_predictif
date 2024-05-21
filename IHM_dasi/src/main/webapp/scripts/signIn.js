$(document).ready(() => {
    $("form").on("submit", (event) => {
        event.preventDefault();
        if ($("#motdepasse").val() != $("#confirmation").val()) {
            alert("Les mots de passe ne correspondent pas");
            return;
        }
        data = {
            todo : "inscrireClient",
            nom : $('#nom').val(),
            prenom : $('#prenom').val(),
            email : $('#email').val(),
            mdp : $('#motdepasse').val(),
            tel : $('#telephone').val(),
            genre : $('#genre').val(),
            adresse: $("#adresse").val(),
            date : $('#dateNaissance').val()
        }
        console.log(data.date);
        $.ajax({
            url: './ActionServlet',
            method: 'POST',
            data: data,
            success: function (data) {
                console.log('Form submitted successfully:', data);
                alert("Vous avez bien été inscrit");
                
            },
            error: function (xhr, status, error) {
                console.error('There was a problem with the form submission:', error);
                alert("Erreur lors de l'inscription")
                // Handle error
            }
        });
    });
});