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
  SmartDevice(name: String, category: String) {
    
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
    }
//Define an increaseSpeakerVolume() method that increases the volume and prints a "Speaker volume increased to $speakerVolume." string:

    fun increaseSpeakerVolume()
      speakerVolume++
      println("Speaker volume is increased to $speakerVolume.")
  }



















