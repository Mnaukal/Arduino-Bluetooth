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
 
char data = 0;                //Variable for storing received data
void setup() 
{
  pinMode(13, OUTPUT);   // set digital pin to control as an output
  BT.begin(9600);        // set the data rate for the SoftwareSerial port
  BT.println("Hello from Arduino");  // Send test message to other device
}
void loop()
{  
  if (BT.available()) // if text arrived in from BT serial...
  {
    a=(BT.read());
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
    // you can add more "if" statements with other characters to add more commands
  }
}                 
