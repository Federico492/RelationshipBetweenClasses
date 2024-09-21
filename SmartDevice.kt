//This is the superclass

class SmartDevice(val name: String, val category: String){
    var deviceStatus = "online"
    
    constructor(name: String, category: String, statusCode: Int): this(name, category){
        deviceStatus = when(statusCode){
            0 -> "Offline"
            1 -> "Online"
            else -> "unknown"
        }
    }
}

//In the SmartDevice superclass, add an "open" keyword before the class keyword to make it extendable

open class SmartDevice(val name: String, val category: String){
    var deviceStatus = "online"
    
    constructor(name: String, category: String, statusCode: Int): this(name, category){
        deviceStatus = when(statusCode){
            0 -> "Offline"
            1 -> "Online"
            else -> "unknown"
        }
    }
}

//Create a SmartTvDevice subclass that extends the SmartDevice superclass:

class SmartTvDevice(deviceName: String, deviceCategory: String) : 
  SmartDevice(name = deviceName, category = deviceCategory) {
    
    var speakerVolume = 2
    set(value) {
        if (value in 0..100) {
            field = value
        }
    }
//Define a channelNumber property assigned to a 1 value with a setter function that specifies a 0..200 range
    var channelNumber = 1
    set(value) {
      if(value in 0..200){
        field = value
      }
   

    //Define an increaseSpeakerVolume() method that increases the volume and prints a "Speaker volume increased to $speakerVolume." string:

    fun increaseSpeakerVolume(){
      speakerVolume++
      println("Speaker volume is increased to $speakerVolume.")
       }
    }

//Define an increaseBrightness() method that increases the brightness of the light and prints a "Brightness increased to $brightnessLevel." string:
    class SmartLightDevice(deviceName: String, deviceCategory: String) :
        SmartDevice(name = deviceName, category = deviceCategory) {
    
        var brightnessLevel = 0
            set(value) {
                if (value in 0..100) {
                    field = value
                }
            }
    
        fun increaseBrightness() {
            brightnessLevel++
            println("Brightness increased to $brightnessLevel.")
        }

        override fun turnOn() {
            super.turnOn()
            brightnessLevel = 2
            println("$name turned on. The brightness level is $brightnessLevel.")
        }
    
        override fun turnOff() {
            super.turnOff()
            brightnessLevel = 0
            println("Smart Light turned off")
        }
    }
      

  }
//In between the SmartLightDevice class and main() function, define a SmartHome class
  class SmartHome(
      val smartTvDevice: SmartTvDevice
      val smartLightDevice: SmartLightDevice
  ){
      fun turnOnTv() {
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }
    //////////////////////////////////

    fun turnOnLight() {
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
  }

  fun main(){
      
  }



















