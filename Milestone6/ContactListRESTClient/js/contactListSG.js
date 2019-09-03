$(document).ready(function(){
	loadContacts();
});

function loadContacts(){
	var contentRows = $('#contentRows');

	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/contacts',
		success: function(contactArray){
			$.each(contactArray,function(index,contact){
				var name = contact.firstName + ' ' + contact.lastName;
				var company = contact.company;

				var row = '<tr>';
				row += '<td>' + name + '</td>';
				row += '<td><a>Edit</a></td>';
				row += '<td><a>Delete</a></td>';
				row +='</tr>';

				contentRows.append(row);
			});
		},
		error: function(){
			$('#errorMessages')
			.append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again.')
            );}
	});
}