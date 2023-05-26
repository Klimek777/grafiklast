const calendar = document.querySelector(".calendar"),
        date = document.querySelector(".date"),
        daysContainer = document.querySelector(".days"),
        prev = document.querySelector(".prev"),
        next = document.querySelector(".next"),
        todayBtn = document.querySelector(".today-btn"),
        gotoBtn = document.querySelector(".goto-btn"),
        dateInput = document.querySelector(".date-input"),
        eventDate = document.querySelector(".event-date"),
        eventsContainer = document.querySelector(".events");


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
let dispositionArr = [];
//then call get
getDisposition();

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

        //check if event present on current day
        let event = false;
        eventsArr.forEach((eventObj) => {
            if(
                eventObj.day == i &&
                eventObj.month == month + 1 &&
                eventObj.year == year
            ){
                //if event found
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
                updateDisposition(i);
                //if event found also add event class
                //add active on today at startup
                if (dispositionArr.indexOf(year+"-"+month+"-"+i)!=-1) {
                    days += `<div class ="day today active-disposition">${i}</div>`;
                } else {
                    days += `<div class ="day today active">${i}</div>`;
                }
            }
        //add remaining as it is  
        else{
            if (dispositionArr.indexOf(year+"-"+month+"-"+i)!=-1) {
                days += `<div class ="day active-disposition">${i}</div>`;
            }else {
                days += `<div class ="day">${i}</div>`;
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

//allow only 50 chars in title


//function to add listner on days after rendered

function addListner() {
    const days = document.querySelectorAll(".day");
    days.forEach((day)=> {
        day.addEventListener("click", (e) => {
            //set current day as active day
            // activeDay = Number(e.target.innerHTML);

            // //call active day after click
            // getActiveDay(e.target.innerHTML);
            // updateDisposition(Number(e.target.innerHTML));

            // //remove active from already active day 

            // days.forEach((day)=>{
            //     day.classList.remove("active");
            // });

            //if prev month day clicked goto pev month and add "active"
            
            if (e.target.classList.contains("prev-date")){
                prevMonth();
            } else if (e.target.classList.contains("next-date")){
                nextMonth();
            }
            else {
                //if remaing current month days 
                e.target.classList.toggle("active-disposition");
                //dispositionArr.find();

                var indexOfDisposition = dispositionArr.indexOf(year+"-"+month+"-"+e.target.innerHTML);

                if (indexOfDisposition === -1) {
                    dispositionArr.push(year+"-"+month+"-"+e.target.innerHTML);
                } else {
                    dispositionArr.splice(indexOfDisposition, 1);
                }

                dispositionArr.sort();
                saveDisposition();
            }
        });
    });
}

//show active day event and date at top

function getActiveDay(date) {
    const day = new Date(year, month, date);
    const dayName = day.toString().split(" ")[0];
}

//function to show events of that day 

function updateDisposition(date){
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
    //save events when update event called
    if (document.readyState === "complete" || document.readyState === "loaded") {
        saveDisposition();
   }
}

//function to create events 



function convertTime(time) {
    let timeArr = time.split(":");
    let timeHour = timeArr[0];
    let timeMin = timeArr[1];
    let timeFormat = timeHour >= 12 ? "PM" : "AM";
    timeHour = timeHour % 12 || 12;
    time = timeHour + ":" + timeMin + " " + timeFormat;
    return time;
}


//store events in local storage

function saveDisposition(){

    var currentURL = window.location.href;

    console.log(JSON.stringify(dispositionArr));
    
    $.ajax({
    url: currentURL+'/save',
    type: 'POST',
    data: { data: JSON.stringify(dispositionArr) },
    });

}

function getDisposition(){
    
    var currentURL = window.location.href;

    $.ajax({
        url: currentURL + '/read',
        type: 'POST',
        data: "User events data requesting...",
        success: function(response) {
          dispositionArr = JSON.parse(response);
          console.log(dispositionArr);
          initCalendar();
        },
        error: function(error) {
          console.log('Error:', error); // Log any errors to the console
        }
      });

}