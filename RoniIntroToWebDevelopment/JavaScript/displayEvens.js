function getEvens(inputNums){
	var start = inputNums[0];
	var end = inputNums[1];
	var step = inputNums[2];
	var arr= new Array();
    var curr =start;

	while (curr<=end){
		if (curr%2==0){
			arr.push(curr);
		}
		curr +=step;
		
	}

	console.log(arr);


	return arr;
}

function getStartEndAndStep(){
	var start = Number(document.getElementById("startingNum").value);
	var end = Number(document.getElementById("endingNum").value);
	var step = Number(document.getElementById("stepNum").value);
	return [start,end, step];
}

function displayResults(inputNums){
	var start = Number(document.getElementById("startingNum").value);
	var end = Number(document.getElementById("endingNum").value);
	var step = Number(document.getElementById("stepNum").value);

	if (start==""||isNaN(start)){
		alert("Starting Number needs to be a number");
		return false;
	}
	if (end==""||isNaN(end)){
		alert("Ending Number needs to be a number");
		return false;
	}
	if (step==""||isNaN(step)){
		alert("Step needs to be a number");
		return false;
	}

	if (!(step >0 && Number.isInteger(step))){
		alert("Step needs to be a positive integer");
		return false;
	}

	if (end<=start){
		alert("Ending number is less than or equal to the starting number");
		return false;
	}

	document.getElementById("result").style="block";
	document.getElementById("resultsCaption").innerText = "Here are the even numbers between " + inputNums[0] +" and "+ inputNums[1] + " by "+inputNums[2]+ "'s:";
	document.getElementById("resultingNums").innerHTML = getEvens(inputNums).join('<br>');


}

