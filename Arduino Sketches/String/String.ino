/* 
 *  Arduino Bluetooth Control for Android
 *  ON/OFF
 *  Author - Michal Töpfer
 *  Website - https://github.com/Mnaukal/android-arduino-bluetooth
 *  This program lets you to control a LED on pin 13 of Arduino using a Bluetooth module and Android app
 *    Send "1" to turn it ON and "0" to turn it OFF
 */
 
#include <SoftwareSerial.h>

SoftwareSerial BT(10, 11);
// creates a "virtual" serial port/UART
// connect BT module TX to D10
// connect BT module RX to D11
// connect BT Vcc to 5V, GND to GND
 
void setup() 
{
  pinMode(13, OUTPUT);   // set digital pin to control as an output
  BT.begin(9600);        // set the data rate for the SoftwareSerial port
  BT.println("Hello from Arduino");  // Send test message to other device
  Serial.begin(9600);
}

void loop()
{    
  if(BT.available()) {
    String input = "";
    
    while(BT.available()) {
      char ch = BT.read();      
      Serial.println((int)ch);
      
      if((int)ch == 10) //newline character
        break;
        
      input += String(ch);
    }
    
    input.toLowerCase();
    input.replace("\n","");

    if(input == "u") {
      digitalWrite(13, HIGH);
      BT.println("On");
    }
    else if(input == "d") {
      digitalWrite(13, LOW);
      BT.println("Off");
    }

    input = "";
  } 
}                 
