/* 
 *  Bluetooh Basic: LED ON OFF - Avishkar
 *  Coder - Mayoogh Girish
 *  Website - http://bit.do/Avishkar
 *  Download the App : 
 *  This program lets you to control a LED on pin 13 of arduino using a bluetooth module
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
  pinMode(pinR, OUTPUT);
  pinMode(pinG, OUTPUT);
  pinMode(pinB, OUTPUT);
  BT.begin(9600);        // set the data rate for the SoftwareSerial port
  BT.println("Hello from Arduino");  // Send test message to other device
  Serial.begin(9600);
}

void loop()
{  
  /*for (int i = 0; i < 255; i++)
  {
    analogWrite(pinR, i);
    delay(10);  
  }
  analogWrite(pinR, 0);
  for (int i = 0; i < 255; i++)
  {
    analogWrite(pinG, i);
    delay(10);  
  }
  analogWrite(pinG, 0);
  for (int i = 0; i < 255; i++)
  {
    analogWrite(pinB, i);
    delay(10);  
  }
  analogWrite(pinB, 0);*/
  
  
  while (BT.available() || Serial.available()) // if text arrived in from BT serial...
  {
    char a;
    if (BT.available())
      a = (BT.read());
    else
      a = Serial.read();
    
    /*switch(a) {
      case '0'...'9':
        v = v * 10 + a - '0';
        break;
      case 'R':
        analogWrite(pinR, v);
        Serial.print(v);
        Serial.println("R");
        v = 0;
        break;
      case 'G':
        analogWrite(pinG, v);
        Serial.print(v);
        Serial.println("G");
        v = 0;
        break;
      case 'B':
        analogWrite(pinB, v);
        Serial.print(v);
        Serial.println("B");
        v = 0;
        break;
    }*/
    
    if (a=='1')
    {
      digitalWrite(13, HIGH);
      BT.println("LED on");
    }
    if (a=='0')
    {
      digitalWrite(13, LOW);
      BT.println("LED off");
    }
    if (a=='?')
    {
      BT.println("Send '1' to turn LED on");
      BT.println("Send '0' to turn LED off");
    }   
    // you can add more "if" statements with other characters to add more commands*/
  }
}                 
