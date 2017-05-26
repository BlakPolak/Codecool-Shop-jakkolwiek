$(document).ready(function() {
    $(document).on('click', '#addProductButton', function (event) {
        event.preventDefault();
        var name = $('#name').val();
        var defaultPrice = $('#defaultPrice').val();
        var description = $('#description').val();
        var categoryId = $('#categoryId').val();
        var supplierId = $('#supplierId').val();
        var data = JSON.stringify({name : name,
            defaultPrice : defaultPrice,
            description : description,
            categoryId : categoryId,
            supplierId : supplierId });

        $.ajax({
            data : data,
            type : 'POST',
            contentType: "application/json",
            dataType: 'json',
            url : '/products/add',
            success: function (data) {
                if (data.error) {
                    $('#info').text(data.error).show();
                } else {
                    $('#info').text("Product was added: "+data.name + " " +data.description).show();
                }
                $('#info').delay(3000).fadeOut('slow');
            },
            error: function (result) {
                alert("Connection error");
            }
        })
    });
});