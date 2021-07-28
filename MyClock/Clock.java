package com.YELUO.Test;
class Clock {
     int year = 0, month = 0, day = 0,hour= 0,minute = 0,second = 0 ;
    public Clock(int year,int month,int day,int hour, int minute, int second){
        this.year=year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;

    }
    public Clock(){
        this.year = 0;
        this.month= 0;
        this.day = 0;
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }
    public void tick(){
        if (this.second<59){
            this.second+=1;
        }else if (this.minute<59){
            this.minute+=1;
            this.second = 0;
        }else if (this.hour<23){
            this.hour+=1;
            this.minute = 0;
        }
        else if(this.day<29){
            this.day++;
            this.hour= 0;
        }
        else if(this.month<11){
            this.month++;
            this.day = 1;
        }
        else if(this.year<10000){
            this.year++;
            this.month = 1;
        }
        else {
            this.day = 0;
            this.year = 0;
            this.month = 0;
            this.hour = 0;
            this.minute = 0;
            this.second = 0;
        }
    }

    public String toString() {

        String s=String.format("%04d:%02d:%02d:%02d:%02d:%02d",year,month,day,hour,minute,second);

        return  s;
    }
}

