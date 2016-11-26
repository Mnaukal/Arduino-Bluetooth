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

int pinR = 13;
int pinG = 5;
int pinB = 6;
static int v = 0;
 
void setup() 
{
  pinMode(13, OUTPUT);   // set digital pin to control as an output
  BT.begin(9600);        // set the data rate for the SoftwareSerial port
  BT.println("Hello from Arduino");  // Send test message to other device
  Serial.begin(9600);
}

void loop()
{  
  while (BT.available() || Serial.available()) // if text arrived in from BT serial... (or normal Serial)
  {
    char a;
    if (BT.available())
      a = (BT.read());
    else
      a = Serial.read();
    
    if (a=='1')
    {
      digitalWrite(13, HIGH);
      BT.println("LED on"); // send confirmation message
    }
    if (a=='0')
    {
      digitalWrite(13, LOW);
      BT.println("LED off"); // send confirmation message
    }  
  }
}                 
