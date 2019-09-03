function findMax(arr){
	if (arr.length<1){
		console.log("Array doesn't have any elements");
	}
	var max=arr[0];

	for (var i=0; i < arr.length; i++){
		if (arr[i]>max){
			max=arr[i];
		}
	}
	console.log("The maximum number in the array is " + max);
}