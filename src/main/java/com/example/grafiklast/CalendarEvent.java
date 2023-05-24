package com.example.grafiklast;

import com.google.firebase.database.annotations.NotNull;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CalendarEvent {

    private String day;
    private String month;
    private String year;
    private Event events[];

    public CalendarEvent() {
    }

    public CalendarEvent(String day, String month, String year, Event[] events) {
                this.day = "";
                this.month = "";
                this.year = "";
                this.events = events;
            }
        
            public String getDay() {
                return day;
            }
        
            public void setDay(String day) {
                this.day = day;
            }

            public String getMonth() {
                return month;
            }
        
            public void setMonth(String month) {
                this.month = month;
            }

            public String getYear() {
                return year;
            }
        
            public void setEvents(Event[] events) {
                this.events = events;
            }

            public Event[] getEvents() {
                return events;
            }
        
            public void setYear(String year) {
                this.year = year;
            }

            public String toString() {
                return "CalendarEvent [date: "+year+"-"+month+"-"+day+" ]";
            }
}