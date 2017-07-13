var prefix = "/result"

 $( function() {
  $("#datepicker").datepicker({
      dateFormat: "yymmdd",
      onSelect: function(date) {
	  $.ajax({
	      type: 'GET',
	      url: prefix + '?date=' + date,
	      dataType: 'json',
	      async: true,
	      success: function(result) {
		  alert(result);
	      },
	      error: function(jqXHR, textStatus, errorThrown) {
		  alert(jqXHR.status + ' ' + jqXHR.responseText);
	      }
	  });
      } });
 });


$( function() {
    $( "#suggestionDialog" ).dialog({
	autoOpen: false
    });
});
 $( function() {
     $( "#participants" ).dialog({
	 autoOpen: false
     });	 
 } );

$( function() {
    $( "#participantButton").button({
	label:"Join"}).click(function() {
	    $("#participantsTable > tbody:last-child").append('<tr><td>'+$("#participantName").val()+'</td><td class="nostretch"> <button onClick=removeParticipant() >Remove</button></td></tr>');
	});
});


$( function() {
    $( "#suggestionButton").button({
	label:"Add"
    }).click(function(){
	$("#suggestions > tbody:last-child").append('<tr><td>'+$("#Food").val()+'</td> <td>'+$("#Restaurant").val()+'</td><td class="nostretch">1</td><td class="nostretch"> <button onClick=listParticipants()>Join/Leave</button></td></tr>')
	$("#suggestionDialog").dialog("close")});
});

function listParticipants() {
    $("#participants").dialog("open");
}

function removeParticipant() {
    alert("Not implemented yet...");
}
