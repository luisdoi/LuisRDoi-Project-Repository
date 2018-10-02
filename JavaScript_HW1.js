

/*
JS HW, part 1: 

Fill in the functions and submit them to your branch in a file called JSHWPart1.js
*/


var homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function(n){
    var x = 0; 
    var y = 1;
    var z = 0;
    
    for(i=0; i<n; i++) {
        z = x + y;
        x = y;
        y = z;
    }
    return z;
};

/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/
homework.sort = function(array) {
    var check;
    do {
        check = false;
        for (var i=0; i < array.length-1; i++) {
                var temp = null;
            if (array[i] > array[i+1]) {
                temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
                check = true;
            }
        }
    } while (check);
    return array;
};

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function(n){

    var x = 1;
    for( i=1; i <= n; i++) {
        x *= i;
    }
    return x;
};

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function(array, n) {

    // loop that makes sure the shifts are done n times
    for( i = 0; i < n; i++) {

        var x = null;
        // loop that does the shifting
        for(j = 0; j < array.length; j++) {
            if(j == 0) {
                x = array[j];
            }
            if(j == array.length - 1 )
                array[j] = x;
            else {
                array[j] = array[j+1];
            }
        }
    }
    return array;
};

/*
 5. Balanced Brackets

 A bracket is any one of the following: (, ), {, }, [, or ]

 The following are balanced brackets:
    ()
    ()()
    (())
    ({[]})

 The following are NOT balanced brackets:
 (
 )
 (()
 ([)]

 Return true if balanced
 Return false if not balanced
*/
homework.balancedBrackets = function(bracketsString){


    if (bracketsString.length <= 1)
        return false;

    var matchingOpeningBracket;
    var ch;
    var stack = [];

    var openingBrackets = ['[', '{', '('];
    var closingBrackets = [']', '}', ')'];

    for (let i = 0; i < bracketsString.length; i++) {
        ch = bracketsString[i]

        if (closingBrackets.indexOf(ch) > -1) {
            matchingOpeningBracket = openingBrackets[closingBrackets.indexOf(ch)]
            if (stack.length == 0 || (stack.pop() != matchingOpeningBracket)) {
                return false
            }
        } else {
        stack.push(ch)
        }
    }

  return (stack.length == 0)

};


//YOUR SOLUTIONS, NOT STACKOVERFLOW’S  ;)
//(you dont understand matt. I am stackoverflow)


