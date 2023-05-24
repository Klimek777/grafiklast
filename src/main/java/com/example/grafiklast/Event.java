package com.example.grafiklast;

public class Event {

    private String title;
    private String time;
    private String date;

    public Event() {
    }

    public Event(String title, String time) {
                this.title = "";
                this.time = "";
            }

            public String getTitle() {
                return title;
            }
        
            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }
        
            public void setTime(String time) {
                this.time = time;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDate() {
                return date;
            }

            public String toString() {
                return "Event [ title: "+title+", time: "+time+" ]";
            }
}