// JavaScript Document
$(document).ready(function(){ 

  var quizRecord = $.ThingBroker({container:false,
			debug:true,
			url:"http://192.168.2.12:8080/thingbroker"
		}).getThing("quiz_board_unique");
		
  
  $("#choice1").prepend(quizRecord.metadata.choiceOne);
  $("#choice2").prepend(quizRecord.metadata.choiceTwo);
  $("#choice3").prepend(quizRecord.metadata.choiceThree);
  $("#choice4").prepend(quizRecord.metadata.choiceFour);
		
  setTimeout("location.reload();", 30000);
  
  $("#submit").click(function(){
	    queID = quizRecord.metadata.queID;
	  	checked = $("input:checked").val();
		  if(checked){
			  $.ajax({
			  url: "mb_submit.php",
			  data: {choice: checked, queId: queID},
			  dataType: 'json',
			  success: function(data){
				  if(data['answer'] == 'correct'){
					  $("#choice_id").replaceWith("Correct answer");
				  }else if(data['wrong'] == 'wrong'){
					  $("#choice_id").replaceWith("Wrong answer");
				  }
			  }
		  });
		}		
  });
});