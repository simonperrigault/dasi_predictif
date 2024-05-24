$(document).ready(() => {
    $("#deco").on("click", () => {

        $.ajax({
            url: './ActionServlet?todo=deconnexion',
            method: 'GET',
            dataType: 'json'
        }).done((res)=>{

            window.location.href = "index.html";



        });




    });

});
