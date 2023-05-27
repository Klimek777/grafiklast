const calendar = document.querySelector(".calendar"),
        date = document.querySelector(".date"),
        daysContainer = document.querySelector(".days"),
        prev = document.querySelector(".prev"),
        next = document.querySelector(".next"),
        todayBtn = document.querySelector(".today-btn"),
        gotoBtn = document.querySelector(".goto-btn"),
        dateInput = document.querySelector(".date-input"),
        eventDay = document.querySelector(".event-day"),
        eventDate = document.querySelector(".event-date"),
        eventsContainer = document.querySelector(".events"),
        addEventSubmit = document.querySelector(".add-event-btn");

        const addEventBtn = document.querySelector('.add-event'),
        addEventContainer = document.querySelector('.add-event-wrapper'),
        addEventCloseBtn = document.querySelector('.close'),
        addEventTitle = document.querySelector('.event-name'),
        addEventFrom = document.querySelector('.event-time-from'),
        addEventTo = document.querySelector('.event-time-to');


let today = new Date();
let activeDay;
let month = today.getMonth();
let year = today.getFullYear();

const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
];

//default events array 
//const eventsArr = [
//   {
//        day: 30,
//       month: 4,
//        year: 2023,
//        events: [
//            {
//                title: "Event 1 lorem ipsum dolar sit genfa tersd dsad",
//                time: "10:00 AM",
//            },
//            {
//                title: "Event 2",
//                time: "11:00 AM",
//            },
//        ],
//    },
//   {
//       day: 18,
//        month: 4,
//        year: 2023,
//        events: [
//            {
//                title: "Event 1 lorem ipsum dolar sit genfa tersd dsad",
//                time: "10:00 AM",
//            },
//        ],
//    },
//
//];

//set an empty array
let eventsArr = [];
allWorkersMode=true;
$('#all_workers_mode_toggle').bootstrapToggle({ on: '', off: ''});
$('#all_workers_mode_toggle').bootstrapToggle('off');
//then call get
if(allWorkersMode) {
    getAllWorkersEvents();
}
else {
    getEvents();
}

function toggleAllWorkersMode() {
    eventsArr=[];
    allWorkersMode=!allWorkersMode;
    if(allWorkersMode) {
        getAllWorkersEvents();
    }
    else {
        getEvents();
    }
    initCalendar();
}

//add days function 

function initCalendar(){
    //to get prev month days and current month all days and rem next month days
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0 );
    const prevLastDay = new Date(year, month, 0);
    const prevDays = prevLastDay.getDate();
    const lastDate = lastDay.getDate();
    const day = (firstDay.getDay()-1)%7;
    const nextDays = 7 - lastDay.getDay();

        //update date -> top of calendar
    date.innerHTML = months[month] + " " + year;

    //adding days 

    let days = "";

    //previous month days
    for (let x = day; x > 0; x--){
        days += `<div class ="day prev-date">${prevDays - x + 1}</div>`;
    }

    //current month days
    for(let i = 1; i <= lastDate; i++ ) {
        eventsInfo=[];
        //check if event present on current day
        let event = false;
        eventsArr.forEach((eventObj) => {
            if(
                eventObj.day == i &&
                eventObj.month == month + 1 &&
                eventObj.year == year
            ){
                //if event found
                eventObj.events.forEach(element => {
                    eventsInfo.push({"title" : element.title, "time": element.time});
                });
                event = true;
            }
        });

        //if day is today -> add class "today"
        if(
            i == new Date().getDate() &&
            year == new Date().getFullYear() &&
            month == new Date().getMonth()
            ){
                activeDay = i;
                getActiveDay(i);
                updateEvents(i);
                //if event found also add event class
                //add active on today at startup
                if (event) {
                    days += `<div class ="day today active flex-column" data-day="${i}">${i}`;
                    eventsInfo.forEach(element => {
                        days += `<span style="${ element.title=='Paweł Jumper' ? 'color: green;' : ''} font-size: 8px;">${element.time}</span>`;
                    });
                    days+='</div>';
                }else {
                    days += `<div class ="day today active" data-day="${i}">${i}</div>`;
                }
            }
        //add remaining as it is  
        else{
            if (event) {
                days += `<div class="day flex-column" data-day="${i}">${i}`;
                eventsInfo.forEach(element => {
                    days += `<span style="${ element.title==document.querySelector("#username_span").innerHTML ? 'color: green;' : ''} font-size: 8px;">${element.time}</span>`;
                });
                days += `</div>`;
            }else {
                days += `<div class ="day" data-day="${i}">${i}</div>`;
            }          
        }            
    }

        //next month days

        for(let j = 1; j <= nextDays; j++){
            days += `<div class ="day next-date">${j}</div>`;
        }
        
        daysContainer.innerHTML = days;
        //add listner after calender initialized
        addListner();
}

initCalendar();

//prev month

function prevMonth(){
    month--;
    if(month < 0){
        month = 11;
        year--;
    }
    initCalendar();
}

//next month 

function nextMonth(){
    month++;
    if(month > 11){
        month = 0;
        year++;
    }
    initCalendar();
}

//add eventlistener on prev and next 

prev.addEventListener("click", prevMonth);
next.addEventListener("click", nextMonth);

//ready calendar up there
//below adding goto date and goto today funcionality 

todayBtn.addEventListener("click", () => {
    today = new Date();
    month = today.getMonth();
    year = today.getFullYear();
    initCalendar();
});

dateInput.addEventListener("input", (e) => {
    //allow only numbers !remove anything else!
    dateInput.value = dateInput.value.replace(/[^0-9/]/g, "");
    if (dateInput.value.length == 2) {
        //add a slash in case of entering two numbers
        dateInput.value += "/";
    }
    if (dateInput.value.length > 7) {
        // do not allow more than 7 characters
        dateInput.value = dateInput.value.slice(0, 7);
    }
    
    //if backspace pressed
    if (e.inputType == "deleteContentBackward"){
        if (dateInput.value.length == 3) {
            dateInput.value = dateInput.value.slice(0, 2);
        }
    }
});

gotoBtn.addEventListener("click", gotoDate);

//function to go to entered date

function gotoDate(){
    const dateArr = dateInput.value.split("/");
    console.log(dateArr);
    //date validation
    if (dateArr.length == 2) {
        if (dateArr[0] > 0 && dateArr[0] < 13 && dateArr[1].length == 4) {
            month = dateArr[0] - 1;
            year = dateArr[1];
            initCalendar();
            return;
        }
    }
    //in invalid date
    alert("Invalid Date");
}

addEventTitle.value=document.querySelector("#username_span").innerHTML;

addEventBtn.addEventListener('click', () => {
    addEventContainer.classList.toggle("active");
});
addEventCloseBtn.addEventListener('click', () => {
    addEventContainer.classList.remove("active");
});

document.addEventListener("click", (e) => {
    //if click outside
    if(e.target != addEventBtn && !addEventContainer.contains(e.target)){
        addEventContainer.classList.remove("active");
    }
});

//allow only 50 chars in title
addEventTitle.addEventListener("input", (e) => {
    addEventTitle.value = addEventTitle.value.slice(0, 50);
});

//time format in 'from' and 'to' time 

copyOfinputFromValue = "";
copyOfinputToValue = "";

addEventFrom.addEventListener("input", (e) => {
    if(event.target.value.indexOf(':')!=2) {
        event.target.value.replace(":", "");
    }
    if(event.target.value.length==2 && event.target.value.indexOf(':')==-1 && (copyOfinputFromValue.length!=3))
    {
        event.target.value+=":";
    }
    var inputValue = event.target.value;
    if(inputValue.length==0) {
        inputValue="00:00";
    }
    if(inputValue.length==1) {
        inputValue+="0:00";
    }
    if(inputValue.length==2) {
        inputValue+=":00";
    }
    else if(inputValue.length==3) {
        inputValue+="00";
    }
    else if(inputValue.length==4) {
        inputValue+="0";
    }
    var regex = /^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$/;
    
    if (!regex.test(inputValue)) {
      event.target.value = copyOfinputFromValue;
    }
    copyOfinputFromValue = event.target.value;
});
//exactly the same for "to" time format
addEventTo.addEventListener("input", (e) => {
    if(event.target.value.indexOf(':')!=2) {
        event.target.value.replace(":", "");
    }
    if(event.target.value.length==2 && event.target.value.indexOf(':')==-1 && (copyOfinputToValue.length!=3))
    {
        event.target.value+=":";
    }
    var inputValue = event.target.value;
    if(inputValue.length==0) {
        inputValue="00:00";
    }
    if(inputValue.length==1) {
        inputValue+="0:00";
    }
    if(inputValue.length==2) {
        inputValue+=":00";
    }
    else if(inputValue.length==3) {
        inputValue+="00";
    }
    else if(inputValue.length==4) {
        inputValue+="0";
    }
    var regex = /^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$/;
    
    if (!regex.test(inputValue)) {
      event.target.value = copyOfinputToValue;
    }
    copyOfinputToValue = event.target.value;
});

//function to add listner on days after rendered

function addListner() {
    const days = document.querySelectorAll(".day");
    days.forEach((day)=> {
        day.addEventListener("click", (e) => {
            //set current day as active day
            activeDay = Number(e.target.dataset.day);

            //call active day after click
            getActiveDay(e.target.dataset.day);
            updateEvents(Number(e.target.dataset.day));

            //remove active from already active day 

            days.forEach((day)=>{
                day.classList.remove("active");
            });

            //if prev month day clicked goto pev month and add "active"
            
            if (e.target.classList.contains("prev-date")){
                prevMonth();

                setTimeout(() => {
                    //select all days of that month 
                    const days = document.querySelectorAll(".day");

                    //after going to prev month add active to clicked
                    days.forEach((day)=>{
                        if (
                            !day.classList.contains("prev-date") && 
                            day.innerHTML == e.target.innerHTML 
                            ){
                            day.classList.add("active");
                        }
                    });
                }, 100);

                getActiveDay(e.target.innerHTML);
                // same with "next month days"
            } else if (e.target.classList.contains("next-date")){
                nextMonth();

                setTimeout(() => {
                    //select all days of that month 
                    const days = document.querySelectorAll(".day");

                    //after going to prev month add active to clicked
                    days.forEach((day)=>{
                        if (
                            !day.classList.contains("next-date") && 
                            day.innerHTML == e.target.innerHTML 
                            ){
                            day.classList.add("active");
                        }
                    });
                }, 100);

                getActiveDay(e.target.innerHTML);
            }
            else {
                //if remaing current month days
                if(e.target.classList.contains('day'))
                    e.target.classList.add("active");
                else if(e.target.parentNode.classList.contains('day'))
                {
                    e.target.parentNode.click();
                }
            }
        });
    });
}

//show active day event and date at top

function getActiveDay(date) {
    const day = new Date(year, month, date);
    const dayName = day.toString().split(" ")[0];
    eventDay.innerHTML = dayName;
    eventDate.innerHTML = date + " " + months[month] + " " + year;
}

//function to show events of that day 

function updateEvents(date){
    let events = "";
    eventsArr.forEach((event) => {
        //get events of active day only 
        if(
            date == event.day &&
            month + 1 == event.month &&
            year == event.year
        ) {
            //then show event on document
            event.events.forEach((event) => {
                events += `
                <div class="event">
                    <div class="title">
                        <i class="fas fa-circle"></i>
                        <h3 class="event-title">${event.title}</h3>
                    </div>
                    <div class="event-time">
                        <span class="event-time">${event.time}</span>
                    </div>
                </div>
                `;
            });
        }
    });

    //if nothing found

    if(events == ""){
        events = `<div class = "no-event">
                <h3>No Events</h3>
        </div>`;
    }
    
    eventsContainer.innerHTML = events;
    //save events when update event called
}

//function to create events 
addEventSubmit.addEventListener("click", () => {
    const eventTitle = addEventTitle.value;
    const eventTimeFrom = addEventFrom.value;
    const eventTimeTo = addEventTo.value;

    //validation
    if(
        eventTitle == "" ||
        eventTimeFrom == "" || 
        eventTimeTo == ""
        ){
            alert("Please fill all the fields!");
            return;
        }

        const timeFromArr = eventTimeFrom.split(":");
        const timeToArr = eventTimeTo.split(":");

    if (
        timeFromArr.length !== 2 || 
        timeToArr.length !== 2 || 
        timeFromArr[0] > 23 ||
        timeFromArr[1] > 59 ||
        timeToArr[0] > 23 ||
        timeToArr[1] > 59
        ){
            alert("Invalid Time Format");
        }

        const timeFrom = convertTime(eventTimeFrom);
        const timeTo = convertTime(eventTimeTo);

        const newEvent = {
            title: eventTitle,
            time: timeFrom + " - " + timeTo,
        };

        let eventAdded = false;

        //check if eventsArr is not empty
        if(eventsArr.length > 0 ){
            //check if current day has already any event, then add to that 
            eventsArr.forEach((item) => {
                if(
                    item.day == activeDay &&
                    item.month == month + 1 &&
                    item.year == year
                ){
                    item.events.push(newEvent);
                    eventAdded = true;
                }
            });
        }

        //if event array is empty or current day has no event then create new

        if(!eventAdded){
            eventsArr.push({
                day: activeDay,
                month: month + 1,
                year: year,
                events: [newEvent]
            });
        }

        //remove active from add event form 
        addEventContainer.classList.remove("active");
        //clear the fields
        addEventTitle.value = "";
        addEventFrom.value = "";
        addEventTo.value = "";

        //show current added event 

        updateEvents(activeDay);

        //also add event class to newly added day if not already 
        const  activeDayElem = document.querySelector(".day.active");
        if (!activeDayElem.classList.contains("event")){
            activeDayElem.classList.add("event");
        }
        addEventTitle.value=document.querySelector("#username_span").innerHTML;
        initCalendar();
    });


function convertTime(time) {
    let timeArr = time.split(":");
    let timeHour = timeArr[0];
    let timeMin = timeArr[1];
    let timeFormat = timeHour >= 12 ? "PM" : "AM";
    timeHour = timeHour % 12 || 12;
    time = timeHour + ":" + timeMin + " " + timeFormat;
    return time;
}


//function to remove events on click 
eventsContainer.addEventListener("click", (e) => {
    if(e.target.classList.contains("event")){
        const eventTitle = e.target.children[0].children[1].innerHTML;

        //get the title of event than search in array by title and delete 
        eventsArr.forEach((event) => {
            if (
                event.day == activeDay &&
                event.month == month + 1 &&
                event.year == year
            ){
              event.events.forEach((item, index) => {
                if(item.title == eventTitle){
                    event.events.splice(index, 1);
                }
              });

              //if no event on that date remove complete day

              if(event.events.length == 0){
                eventsArr.splice(eventsArr.indexOf(event), 1);
                //after remove complete day also remove active class of that day
                const activeDayElem = document.querySelector(".day.active");
                if(activeDayElem.classList.contains("event")){
                    activeDayElem.classList.remove("event");
                }
              }
            }
        });
        //after removing from array update event
        updateEvents(activeDay);
    }
});

//store events in local storage

function saveEvents(){
    //localStorage.setItem("events", JSON.stringify(eventsArr));

    var currentURL = window.location.href;

    //var dataFromLocalStorage = localStorage.getItem('events');
    
    $.ajax({
    url: currentURL+'/save',
    type: 'POST',
    data: { data: JSON.stringify(eventsArr) },
    });

}

function getEvents(){
    
    var currentURL = window.location.href;

    // $.ajax({
    //     url: currentURL + '/all/read',
    //     type: 'POST',
    //     data: "All users events data requesting...",
    //     success: function(response) {
    //       //console.log(response); // Log the response to the console
    //       eventsArr = JSON.parse(response);
    //       console.log(eventsArr);
    //       initCalendar();
    //     },
    //     error: function(error) {
    //       console.log('Error:', error); // Log any errors to the console
    //     }
    //   });

    $.ajax({
        url: currentURL + '/read',
        type: 'POST',
        data: "User events data requesting...",
        success: function(response) {
          //console.log(response); // Log the response to the console
          eventsArr = JSON.parse(response);
          //console.log(eventsArr);
          initCalendar();
        },
        error: function(error) {
          console.log('Error:', error); // Log any errors to the console
        }
      });

    // if(localStorage.getItem("events" != null)){
    //     eventsArr.push(...JSON.parse(localStorage.getItem("events")));
    // }
    // else {
    //     return;
    // }
}

function getAllWorkersEvents(){
    var currentURL = window.location.href;

    $.ajax({
        url: currentURL + '/all/read',
        type: 'POST',
        data: "All users events data requesting...",
        success: function(response) {
          //console.log(response); // Log the response to the console

          for (let i = 0; i < JSON.parse(response).length; i++) {
            eventsArr = eventsArr.concat(JSON.parse(response)[i]);
            
          }

          //eventsArr = JSON.parse(response)[0].concat(JSON.parse(response)[1]);
          console.log(eventsArr);
          initCalendar();
        },
        error: function(error) {
          console.log('Error:', error); // Log any errors to the console
        }
      });

    // if(localStorage.getItem("events" != null)){
    //     eventsArr.push(...JSON.parse(localStorage.getItem("events")));
    // }
    // else {
    //     return;
    // }
}