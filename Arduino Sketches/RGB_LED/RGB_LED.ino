/* 
 *  Arduino Bluetooth Control for Android
 *  RGB LED
 *  Author - Michal Töpfer
 *  Website - https://github.com/Mnaukal/android-arduino-bluetooth
 *  This program lets you to control a RGB LED using a Bluetooth module and Android app
 *    Send number followed by "R", "G" od "B" to set red, green and blue values of the color
 *    "255R" -> sets red to maximum; "0G" -> sets green to minimum
 */
 
#include <SoftwareSerial.h>

SoftwareSerial BT(10, 11);
// creates a "virtual" serial port/UART
// connect BT module TX to D10
// connect BT module RX to D11
// connect BT Vcc to 5V, GND to GND

// connect RGB LED to Arduino and set the pins here
int pinR = 13;
int pinG = 5;
int pinB = 6;

// variable for getting numeric input
static int v = 0;

// current color
int R = 0, G = 0, B = 0;
 
void setup() 
{
  // set digital pin to control as an output
  pinMode(pinR, OUTPUT);
  pinMode(pinG, OUTPUT);
  pinMode(pinB, OUTPUT);
  BT.begin(9600);        // set the data rate for the SoftwareSerial port
  BT.println("Hello from Arduino");  // Send test message to other device
  Serial.begin(9600);
}

void loop()
{  
  while (BT.available() || Serial.available()) // if text arrived in from BT serial...
  {
    char a;
    if (BT.available())
      a = (BT.read());
    else
      a = Serial.read();
      
    Serial.println((int)a);
    
    switch(a) { // swtich by the received character
      case '0'...'9':           // numbers -> store them in variable v
        v = v * 10 + a - '0';
        break;
      case 'R':                 // set red value (using PWM)
        analogWrite(pinR, v);
        R = v;
        SendBackColor();
        v = 0;
        break;
      case 'G':                  // set green value (using PWM)
        analogWrite(pinG, v);
        G = v;
        SendBackColor();
        v = 0;
        break;
      case 'B':                   // set blue value (using PWM)
        analogWrite(pinB, v);
        B = v;
        SendBackColor();
        v = 0;
        break;
    }
    delay(1);
  }
}   

void SendBackColor() // send current color to the controller
{
  BT.print("Red: ");
  BT.print(R);
  BT.print(", Green: ");
  BT.print(G);
  BT.print(", Blue: ");
  BT.println(B);
  
  Serial.print("Red: ");
  Serial.print(R);
  Serial.print(", Green: ");
  Serial.print(G);
  Serial.print(", Blue: ");
  Serial.println(B);
}
