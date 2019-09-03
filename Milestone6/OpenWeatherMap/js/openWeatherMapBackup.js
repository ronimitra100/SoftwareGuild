$(document).ready(function(){

    $('#get-weather-button').click(function(event){
        var zipcode = $('#zipcode').val();
        var units = $('#units').val();
        var appid='400f25cc536cd3118246e53febe0dcaa';
        var currentWeatherHeading = $('#current-weather-heading');

        $.ajax({
            type:'GET',
            url: 'http://api.openweathermap.org/data/2.5/weather?zip='+zipcode+'&appid='+appid +'&units='+units,
            
           
            cache: false,
            
            'dataType' : 'json',

            success: function(data){
                
               showCurrentWeatherData(data, units);
            },
            error: function(){
                 $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
            }
      


      $.ajax({
            type:'GET',
            url: 'https://api.openweathermap.org/data/2.5/weather?zip='+zipcode+'&appid='+appid +'&units='+units,
            
           
            cache: false,
            
            'dataType' : 'json',

            success: function(data){
                
               showCurrentWeatherData(data, units);
            },
            error: function(){
                 $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
            }
      });





})

})


function showCurrentWeatherData(data, units){
    var result;

    return ("<h1id=\"current-weather-heading\">Current Weather Conditions in " + data.city.name +"<\/h1>"
           + "<divclass=\"container\"><divclass=\"row\"><divclass=\"col-md-3\">" + data.weather.main + "<\/div>"
           + "<divclass=\"col-md-3\"><ulstyle=\"list-style:none\"><li>Temperature:" + data.main.temp + " " + getTempScalesAbbrv(units)+"<\/li><li>Humidity:" 
           + data. main.humidity + "%<\/li><li> Wind:" + data.wind.speed + " " +getSpeedScale(units) +"<\/li><\/ul><\/div><\/div>");

    
}



function getTempScalesAbbrv(units){
    if (units =='Imperial'){
        return 'F';
    }
    else{
        return 'C';
    }
}

function getSpeedScale(units){
         if (units =='Imperial'){
        return 'miles/hour';
    }
    else{
        return 'kilometers/hour';
    }
    }

