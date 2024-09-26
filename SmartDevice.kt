import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

//SmartDevice is the original class
open class SmartDevice(val name: String, val category: String){
    
    var deviceStatus = "online"
    
    protected set 	//Protected – only the SmartDevice class or its 
    				//subclasses can modify the device Status
    
    open val deviceType = "unknown"
    
    open fun turnOn(){
        deviceStatus = "on" //call the device status on for turn on
    }
    
    open fun turnOff(){
        deviceStatus = "off" //call the device status off for turn off
    }
    
}
//Class for the smart TV, not being shared so don't need the "open" but needs to use SmartDevice
//class because the Smart TV is a SmartDevice that will turn on and off
class SmartTvDevice(deviceName: String, deviceCategory: String) :
	SmartDevice(name = deviceName, category = deviceCategory){
        
        override val deviceType = "Smart TV" //override the value of deviceType from unknown to Smart TV
        
        private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100) 
        
        private val channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)
        
        //RangeRegulator 
        //is a class that regulates the range of the variable, if the result is outside that range then the result 
        //will continue with the same value	
        
        fun increasedSpeakerVolume(){
            speakerVolume++
            println("Speaker volume increased to $speakerVolume")
        }
        
        fun nextChannel(){
            channelNumber++
            println("Channel number increased to $channelNumber")
        }
      
        override fun turnOn(){
            super.turnOn()
            println("$name is turned ON. Speaker volume is set to $speakerVolume and channel is" +
                   "set to $channelNumber")
        }
        
        override fun turnOff(){
            super.turnOff()
            println("$name turned off"")
        }
        
        
        
        
        
        
        
        
        
    }
