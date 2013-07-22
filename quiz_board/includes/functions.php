<?php

//runs and validates the resulst of a query
function run_query($sql,$resource){
	$query = mysql_query($sql,$resource);
	if(!$query){
		echo("<p>The action you performed just caused an error</p>");
	}
	return $query;
}

//Escape special characters in a string for user in an sql statement
function mysql_prep( $value ) {
	//strip white space frome the beginning and end of a string
	$value = trim($value);
	$magic_quotes_active = get_magic_quotes_gpc();
	$new_enough_php = function_exists( "mysql_real_escape_string" ); 
	if( $new_enough_php ) {
		if( $magic_quotes_active ) { $value = stripslashes( $value ); }
		$value = mysql_real_escape_string( $value );
	} else {
		if( !$magic_quotes_active ) { $value = addslashes( $value ); }
	}
	return $value;
}

//redirect from current page to another 
function redirect_to( $location = NULL ) {
	if ($location != NULL) {
		header("Location: {$location}");
		exit;
	}
}
?>