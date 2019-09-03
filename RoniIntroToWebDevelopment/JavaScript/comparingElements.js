var testArray = [0,1,1,"1",3,"311"];

for (var i=0;i<testArray.length-1;i++){
	var curr = testArray[i];
	var next = testArray[i+1];

	console.log("Testing " + curr + " and " + next + " greater than " + (curr>next));
	console.log("Testing " + curr + " and " + next + " less than " + (curr<next));

	if (curr===next){
		console.log("Testing " + curr + " and " + next + " strictly equal to: " + (curr==next));
	}

	if (curr!== next){
		console.log(curr + " is " + typeof(curr));
		console.log(next + " is " + typeof(next));
	}
	else{
		console.log("Testing " + curr + " and " + next + "(equal to): false");
	}
	
}