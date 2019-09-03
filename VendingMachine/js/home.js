var totalDepositInDollars=0.00;
var itemId=0;
var message;
var change;


$(document).ready(function(){
    clearUserForms();
    loadItems();
    
    $('#add-dollar-button').click(function(){
        addMoney("dollar");
    });
    $('#add-quarter-button').click(function(){
        addMoney("quarter");
    });
    $('#add-dime-button').click(function(){
        addMoney("dime");
    });
    $('#add-nickel-button').click(function(){
        addMoney("nickel");
    });

    $('#make-purchase-button').click(function(){
        vendItem(totalDepositInDollars.toFixed(2), $('#item-id').val());
    });

    $('#return-change-button').click(function(){
        clearUserForms();
        loadItems();
    });
    
});


function loadItems(){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        dataType: 'json',
        cache: false,
        success: function(response){
            $('#item-list').empty();

            var items="";

            for (var i=0; i< response.length;i++){
                 items +="<div class=\"col-lg-4 border border-dark\" style=\"padding:5%; \">\r\n";
                 items += "<div style=\"width:100%;height:0;padding-bottom:100%;border-style:solid; background-color: lightblue;\">\r\n\r\n";
                 items += "<p style=\"text-align:left\">&nbsp;"+response[i].id+"<\/p>\r\n\r\n";
                 items += "<h4>"+response[i].name+"<\/h4>\r\n";
                 items += "<p>$"+response[i].price+"<\/p>\r\n<br>\r\n<br>\r\n";
                 items += "<p>Quantity Left: "+response[i].quantity+"<\/p>\r\n<\/div>\r\n<\/div>";
            }
            $('#item-list').append(items);
        },
        error: function(response){
           alert("Some error was encountered while trying to retrieve items. Please try again later.");
        }

    });
}

function addMoney(typeOfCoin){
    
    switch(typeOfCoin){
        case "dollar":
           totalDepositInDollars += 1.00;
           break;
        case "quarter":
           totalDepositInDollars += 0.25;
           break;
        case "dime":
           totalDepositInDollars += 0.10;
           break;
        case "nickel":
           totalDepositInDollars += 0.05;    
    }

    $('#deposit').val(totalDepositInDollars.toFixed(2));
}

function vendItem(amount, id){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/money/'+amount+'/item/'+id,
        dataType:'json',
        cache: false,
        success: function(response){
            $('#message').val("Thank You!!!");

            var changeInfo = "";
        

            if (response.quarters>1){

                changeInfo += response.quarters + " Quarters ";
            }
            if (response.quarters==1){

                changeInfo += response.quarters + " Quarter ";
            }
            if (response.dimes>1){
                changeInfo += response.dimes + " Dimes ";
            }
            if (response.dimes==1){
                changeInfo += response.dimes + " Dime ";
            }
            if (response.nickels>1){
                changeInfo += response.nickels + " Nickels ";
            }
            if (response.nickels==1){
                changeInfo += response.nickels + " Nickel ";
            }
            if (response.pennies>1){
                changeInfo += response.pennies + " Pennies";
            }
            if (response.pennies==1){
                changeInfo += response.pennies + " Penny";
            }
            
            $('#change').val(changeInfo);
        },
        error:function(response){
            
            $('#message').val(response.responseJSON.message);
        }
    });
}

function clearUserForms(){
    $('#deposit').val('');
    $('#message').val('');
    $('#item-id').val('');
    $('#change').val('');
    totalDepositInDollars=0.00;
}
