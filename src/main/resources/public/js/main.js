$(document).ready(function() {

    // $(window).load(function() {
        $.ajax({
            data : JSON.stringify({update : "update"}),
            type : 'POST',
            contentType: "application/json",
            dataType: 'json',
            url : '/basket/update',
            success: function (data) {
                if (data.error) {
                    $('#info').text(data.error).show();
                }
                else if (data.items.length < 1) {
                    $("#basketTable").find("tbody").html("<tr><td colspan='5'><h3>Empty basket</h3></td></tr>").show();
                }
                else {
                    $("#basketTable").find("tbody").html("");
                    var sum = 0;
                    $.each(data.items, function (index, element) {
                        sum=sum+(element.product.defaultPrice * element.quantity);
                        $("#basketTable").find("tbody").append(
                            "<tr><td>"+(index+1)+"</td>" +
                            "<td>"+element.product.name+"</td>" +
                            "<td>"+element.quantity+"</td>" +
                            "<td>"+element.product.defaultPrice+"</td>" +
                            "<td><a id='basketItem"+element.id+"' class='glyphicon glyphicon-remove' style='cursor: pointer;'></a></td></tr>").show();
                    });
                    $("#basketTable").find("tbody").append("<tr><td colspan='3'>Sum</td><td colspan='2'>"+sum+"</td></tr>" +
                        "<tr><td colspan='5'><a href='' class='label label-primary col-md-12' style='font-size: 120%;'>Pay</a></td></tr>").show();
                }

            },
            error: function (result) {
                alert("Connection error");
            }
        });
    // });

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
                    $('#info').text("Product was added").show();
                }
                $('#info').delay(3000).fadeOut('slow');
            },
            error: function (result) {
                alert("Connection error");
            }
        })
    });

    $(document).on('click', '[id^=prod_id_]', function (event) {
        event.preventDefault();
        var prod_id = $(this).attr('id');
        var idFromName = prod_id.replace("prod_id_", "");
        $.ajax({
            data : JSON.stringify({prodId : idFromName}),
            type : 'POST',
            contentType: "application/json",
            dataType: 'json',
            url : '/basket/add',
            success: function (data) {
                if (data.error) {
                    $('#info').text(data.error).show();
                }
                else if (data.items.length < 1) {
                    $("#basketTable").find("tbody").html("<tr><td colspan='5'><h3>Empty basket</h3></td></tr>").show();
                }
                else {
                    $("#basketTable").find("tbody").html("");
                    var sum = 0;
                    $.each(data.items, function (index, element) {
                        sum=sum+(element.product.defaultPrice * element.quantity);
                        $("#basketTable").find("tbody").append(
                            "<tr><td>"+(index+1)+"</td>" +
                            "<td>"+element.product.name+"</td>" +
                            "<td>"+element.quantity+"</td>" +
                            "<td>"+element.product.defaultPrice+"</td>" +
                            "<td><a id='basketItem"+element.id+"' class='glyphicon glyphicon-remove' style='cursor: pointer;'></a></td></tr>").show();
                    });
                    $("#basketTable").find("tbody").append("<tr><td colspan='3'>Sum</td><td colspan='2'>"+sum+"</td></tr>" +
                        "<tr><td colspan='5'><a href='' class='label label-primary col-md-12' style='font-size: 120%;'>Pay</a></td></tr>").show();
                }

            },
            error: function (result) {
                alert("Connection error");
            }
        })
    });

    $(document).on('click', '[id^=basketItem]', function (event) {
        event.preventDefault();
        var prod_id = $(this).attr('id');
        var idFromBasketItem = prod_id.replace("basketItem", "");
        $.ajax({
            data : JSON.stringify({basketItemId : idFromBasketItem}),
            type : 'POST',
            contentType: "application/json",
            dataType: 'json',
            url : '/basket/remove',
            success: function (data) {
                if (data.error) {
                    $('#info').text(data.error).show();
                }
                else if (data.items.length < 1) {
                    $("#basketTable").find("tbody").html("<tr><td colspan='5'><h3>Empty basket</h3></td></tr>").show();
                }
                else {
                    $("#basketTable").find("tbody").html("");
                    var sum = 0;
                    $.each(data.items, function (index, element) {
                        sum=sum+(element.product.defaultPrice * element.quantity);
                        $("#basketTable").find("tbody").append(
                            "<tr><td>"+(index+1)+"</td>" +
                            "<td>"+element.product.name+"</td>" +
                            "<td>"+element.quantity+"</td>" +
                            "<td>"+element.product.defaultPrice+"</td>" +
                            "<td><a id='basketItem"+element.id+"' class='glyphicon glyphicon-remove' style='cursor: pointer;'></a></td></tr>").show();
                    });
                    $("#basketTable").find("tbody").append("<tr><td colspan='3'>Sum</td><td colspan='2'>"+sum+"</td></tr>" +
                        "<tr><td colspan='5'><a href='' class='label label-primary col-md-12' style='font-size: 120%;'>Pay</a></td></tr>").show();
                }

            },
            error: function (result) {
                alert("Connection error");
            }
        })
    });
});


