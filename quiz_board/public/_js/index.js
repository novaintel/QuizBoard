$(document).ready(function(){
$.ThingBroker({container:false,
          debug:true,
          url:"http://192.168.2.12:8080/thingbroker"
      }).postThing({thingId: "quiz_board_unique",name: "quizBoard"});	
	  
$.ThingBroker({container:false,
          debug:true,
          url:"http://192.168.2.12:8080/thingbroker"
      }).postMetadata(
       "quiz_board_unique", {
		   	     choiceOne: $("#choice1").html(),
				 choiceTwo: $("#choice2").html(),
				 choiceThree: $("#choice3").html(),
				 choiceFour: $("#choice4").html(),
				 queID: $("#que_id").val()                 
        });
		setTimeout("location.reload();", 30000);
});
