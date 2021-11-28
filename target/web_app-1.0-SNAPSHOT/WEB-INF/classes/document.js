$(document).ready(function(){
    $('#fixture-use').change(function(){
        if($(this).val() === "7")
        {
            $('#fixture-use-txt').show();
        }
        else
        {
            $('#fixture-use-txt').hide();

        }

    })

})