$(document).ready(function () {
  $('#get-weather-button').click(function (event) {
    var zipcode = $('#zipcode').val();
    var units = $('#units').val();
    var appid = '400f25cc536cd3118246e53febe0dcaa';
    var currentWeatherHeading = $('#current-weather-heading');

    if (!isZipValid){
     $('#errorMessages')
     .append($('<li>')
     .attr({
        class: 'list-group-item list-group-item-danger'
      })
      .text('Zip code: please enter a 5-digit zip code'));
     return false;  
   }

   $.ajax({
    type: 'GET',
    url: 'http://api.openweathermap.org/data/2.5/weather?zip=' + zipcode + '&appid=' + appid + '&units=' + units,
    cache: false,
    success: function (data) {
      $('#current-weather-heading').append(data.name);
      $('#currentWeatherSummary').append('<img src=\"http:\/\/openweathermap.org\/img\/w\/"' + data.weather[0].icon + " " + data.weather[0].main + ":" + data.weather[0].description);
      $('#currentWeatherDetails').append(getCurrentWeatherDetails(data, units));
    },
    error: function () {
      $('#errorMessages')
      .append($('<li>')
        .attr({
          class: 'list-group-item list-group-item-danger'
        })
        .text('Error calling web service.  Please try again later.'));
    }
  });

   $.ajax({
    type: 'GET',
    url: 'http://api.openweathermap.org/data/2.5/forecast?zip=' + zipcode + '&appid=' + appid + '&units=' + units,
    cache: false,
    success: function (data) {
      $('#weatherDataForNext5Days').append(getWeatherForecast(data, units));
    },
    error: function () {
      $('#errorMessages')
      .append($('<li>')
        .attr({
          class: 'list-group-item list-group-item-danger'
        })
        .text('Error calling web service.  Please try again later.'));
    }
  });
   $('weatherInfo').show();
 });

})


function getCurrentWeatherDetails(data, units) {
  var result;

  return ("<h1id=\"current-weather-heading\">Current Weather Conditions in " + data.city.name + "<\/h1>" +
    "<divclass=\"container\"><divclass=\"row\"><divclass=\"col-md-3\">" + data.weather.main + "<\/div>" +
    "<divclass=\"col-md-3\"><ulstyle=\"list-style:none\"><li>Temperature:" + data.main.temp + " " + getTempScalesAbbrv(units) + "<\/li><li>Humidity:" +
    data.main.humidity + "%<\/li><li> Wind:" + data.wind.speed + " " + getSpeedScale(units) + "<\/li><\/ul><\/div><\/div>");


}


function getTempScalesAbbrv(units) {
  if (units == 'Imperial') {
    return 'F';
  } else {
    return 'C';
  }
}

function getSpeedScale(units) {
  if (units == 'Imperial') {
    return 'miles/hour';
  } else {
    return 'kilometers/hour';
  }
}

function getWeatherForecast(data, units) {
  for (i = 0; i < 5; i++) {
    var concernedDay = data.list[i].dt_tx;
    var icon = data.list[i].weather[0].icon;
    var highestTemp = "H " + data.list[i].main.temp_max + " " + getTempScalesAbbrv(units);
    var lowestTemp = "L " + data.list[i].main.temp_main + " " + getTempScalesAbbrv(units);

    return ("<divclass=\"col-xs-2\"><ulstyle=\"list-style:none;text-align:center\"><li>" + concernedDay + "<\/li>" +
      "<li><img src=\"http:\/\/openweathermap.org\/img\/w\/" + icon + ".png\">Clear<\/li><li>" + highestTemp + " " + lowestTemp + "<\/li><\/ul><\/div>");
  }
}

function getDateInMonthDayFormat(str) {
  var myDate = new Date(str);
  return (myDate.getDay() + " " + myDate.getMonth());
}

function isZipValid(str){
  var patt = new RegExp("\d{5}");
  return patt.test(str);
}