/*
----------------------------------------------------------------------------------
PART II
Part II will focus on Javascript's ability to manipulate the DOM.
Use the provided HTML page.
Put your Javascript in the provided <script> element at the bottom of the page.
Please put the question itself as a comment above each answer.
-----------------------------------------------------------------------------------
*/
/*
1. USA
Define function getUSA()
Find the html element that contains "USA".
Print that element's contents.
*/
function getUSA(){
    
    for(let elem of document.getElementsByTagName("*")){
        if(elem.innerHTML == "USA") {
            //return elem.innerText;
            console.log(elem.innerText);
        }
    }
};

/*
2. Sales
Define function getPeopleInSales()
Print the names of all the people in the sales department.
*/
function getPeopleInSales() {
    // for(let elem of document.getElementsByClassName("empName")){
    //     console.log(elem.innerHTML);
    //     if(elem.innerHTML == "Sales") {
    //         console.log(elem.innerHTML);
    //     }
    // }
    var arr = document.getElementsByTagName("table");

    for(i = 0; i < arr.length; i++){

        var len = arr[i].getElementsByClassName("empName").length
        var array = arr[i].rows;

        for(j = 0; j <= len; j++) {
            if(array[j].firstElementChild.nextElementSibling.innerHTML == "Sales"){
                console.log(array[j].firstElementChild.innerHTML);
            }
        }
    }
};

/*
3. Click Here
Define function getAnchorChildren()
Find all anchor elements with a <span> child.
Print the contents of <span>
*/

function getAnchorChildren(){ 

    //console.log(document.getElementsByTagName('a'));
    var arrWithTagA = document.getElementsByTagName('a');

    var elem = null;
    for(i = 0; i < arrWithTagA.length; i++){
        elem = arrWithTagA[i];

        var sp = elem.getElementsByTagName('span');
        if(elem.getElementsByTagName('span').length > 0){
            console.log(sp.item(0).innerHTML);
        }
    }
}


/*  
4. Hobbies
Define function getSkills()
Find all checked options in the 'skills' select element.
Print the value and the contents.
*/

function getSkills() {
    var skills = document.getElementsByName("skills").item(0)
    for(i = 0; i < skills.childElementCount; i++) {
        var x = skills.getElementsByTagName("option")
        if(x.item(i).getAttribute('selected') == 'selected')
            console.log("value: " + x.item(i).value + " content: " + x.item(i).innerHTML)
    }
};

/*  
5. Custom Attribute
Define function getCustomAttribute()
Find all elements with "data-customAttr" attribute
Print the value of the attribute.
Print the element that has the attribute.
*/

function getCustomerAttribute() {
    var x = document.getElementsByTagName("*")
    for(i = 0; i < x.length; i++){

        if(x.item(i).attributes.getNamedItem("data-customAttr"))
            console.log(x.item(i).attributes.getNamedItem("data-customAttr").ownerElement);
    }
}

/*
6. Sum Event
NOTE: Write unobtrusive Javascript
Regarding these elements:	
<input id="num1" class="nums" type="text"/>	
<input id="num2" class="nums" type="text"/>	
<h3>Sum: span id="sum"></span></h3>
*/
/*
Define onchange event handler.
Add <input> element values.
Put the sum in the <span> element.
If values cannot be added, put "Cannot add" in the <span> element
*/



document.getElementById("num2").addEventListener("input",function(){
    var n1 = document.getElementById("num1").value;
    var n2 = document.getElementById("num2").value;
    n1 = parseFloat(n1);
    n2 = parseFloat(n2);
    var s = n1 + n2;
    console.log(s)
    if(isNaN(s))
        document.getElementById("sum").innerHTML = "Cannot add";
    else
        document.getElementById("sum").innerHTML = s;
});

document.getElementById("num1").addEventListener("input",function(){
    var n1 = document.getElementById("num1").value;
    var n2 = document.getElementById("num2").value;    
    n1 = parseFloat(n1);
    n2 = parseFloat(n2);
    var s = n1 + n2;
    console.log(s);
    if(isNaN(s))
        document.getElementById("sum").innerHTML = "Cannot add";
    else
        document.getElementById("sum").innerHTML = s;
});


/*
7. Skills Event
NOTE: Write unobtrusive Javascript
When user selects a skill, create an alert with a message similar to:	
"Are you sure CSS is one of your skills?"
NOTE: no alert should appear when user deselects a skill.
*/


/*
8. Favorite Color Event
NOTE: Write unobtrusive Javascript
NOTE: This is regarding the favoriteColor radio buttons.
When a user selects a color, create an alert with a message similar to:	
"So you like green more than blue now?"
In this example, green is the new value and blue is the old value.
Make the background color (of all favoriteColor radio buttons) 
the newly selected favoriteColor
*/


/*
9. Show/Hide Event
NOTE: Write unobtrusive Javascript
When user hovers over an employees name:
Hide the name if shown.
	Show the name if hidden.
*/


/*
10. Current Time
Regarding this element:
	<h5 id="currentTime"></h5>
Show the current time in this element in this format: 9:05:23 AM
The time should be accurate to the second without having to reload the page.
*/



/*
11. Delay
Regarding this element:	
<p id="helloWorld">Hello, World!</p>
Three seconds after a user clicks on this element, change the text to a random color.
*/


/*
12. Walk the DOM
Define function walkTheDOM(node, func)
This function should traverse every node in the DOM. 
Use recursion.
On each node, call func(node).
*/
