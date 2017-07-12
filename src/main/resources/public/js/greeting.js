 $( function() {
  $("#datepicker").datepicker({
  onSelect: function(date) {
  } });
  });

  $( function() {
      $( "#participants" ).dialog({
	  autoOpen: false
      }
      );
  } );

function addSuggestion(food, resturant, numParticipants) {
  $("#suggestions > tbody:last-child").append('<tr><td>'+food+'</td> <td>'+resturant+'</td><td class="nostretch">'+numParticipants+'</td><td class="nostretch"> <button onClick=listParticipants()>Join/Leave</button></td></tr>');
  
}


function listParticipants() {
    $("#participants").dialog("open");
}

function addParticipant(name) {
    //TODO call backend
    $("#participantsTable > tbody:last-child").append('<tr><td>'+name+'</td><td class="nostretch"> <button >Remove</button></td></tr>');
}
