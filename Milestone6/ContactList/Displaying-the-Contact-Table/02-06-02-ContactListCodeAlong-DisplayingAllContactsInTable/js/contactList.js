$(document).ready(function () {

    loadContacts();

    $('#add-button').click(function(event){
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/contact',
            data: JSON.stringify({
                firstName: $('#add-first-name').val();
                lastName: $('#add-last-name').val();
                company: $('#add-company').val();
                phone: $('#add-phone').val();
                email: $('#add-email').val()
            }),
            headers: {
                'Accept': 'application/json';
                'Content-Type': 'application/json';
            },
            'dataType' : 'json',
            success: function(){
                $('#errorMessages').empty();
                $('#add-first-name').val('');
                $('#add-last-name').val('');
                $('#add-company-name').val('');
                $('#add-phone-name').val('');
                $('#add-email').val('');
                loadContacts();
            },
            error: function(){
                 $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
            }
        });
    });

});

function loadContacts() {
    // we need to clear the previous content so we don't append to it
    clearContactTable();

    // grab the the tbody element that will hold the rows of contact information
    var contentRows = $('#contentRows');

    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8080/contacts',
        success: function (data, status) {
            $.each(data, function (index, contact) {
                var name = contact.firstName + ' ' + contact.lastName;
                var company = contact.company;
                var id = contact.contactId;

                var row = '<tr>';
                    row += '<td>' + name + '</td>';
                    row += '<td>' + company + '</td>';
                    row += '<td><a>Edit</a></td>';
                    row += '<td><a>Delete</a></td>';
                    row += '</tr>';
                contentRows.append(row);
            });
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
        }
    });
}

function clearContactTable() {
    $('#contentRows').empty();
}
