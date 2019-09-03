function countingCharacters(stringToCount){
	console.log(stringToCount + " has " + stringToCount.length + " characters.");
}

function countingCharacters2(stringToCount, characterToFind){
	var characterCount = 0;
	for (var characterPosition = 0; characterPosition < stringToCount.length; characterPosition++ ){
		if(stringToCount[characterPosition] == characterToFind ){
			characterCount++;
		}
	}

	console.log("String to search in: " + stringToCount);
	console.log("Character to find: " + characterToFind);
	console.log("Number of times the character appears: " + characterCount);
}


function countingCharacters3(str,search){
	var count=0;
	var numOfChars = search.length;
	var lastIndex = str.length - numOfChars;

	for (var index=0; index<=lastIndex; index++){
		if(search===str.substring(index,index+numOfChars)){
			count++;
		}
	}
	return count;
}

function rollDice(minimum, maximum){
return (Math.ceil(Math.random()*(maximum-minimum+1)));
}

function addTwoNumbers(firstNumber, secondNumber){
	var sum = firstNumber + secondNumber;
	return sum;
}

var friends = ["Aneki", "Quell", "Clarity", "Sleepy", "Roghar", "DM Crimson"];

var team1 = new Array();
var team2 = new Array();
var team3 = new Array();
var team4 = new Array;
var team5 = new Array();
var team6 = new Array();

for (var i=0; i<friends.length; i++){
	if(i%2==0){
	team1.push(friends[i]);
	}
	else{
	team2.push(friends[i]);	
	}
}

for (var i=0; i<friends.length; i++){
	if(i%2==0){
		team3.unshift(friends[i]);
	}
	else{
		team4.unshift(friends[i]);
	}
}

for (var i=0; i<friends.length; i++){
	if(i%2==0){
	team5[team5.length] = friends[i];
	}
	else{
	team6[team6.length] = friends[i];	
	}
}