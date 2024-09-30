import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "online"
        protected set

    open val deviceType = "unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }
    
    open fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }
    
    fun decreaseSpeakerVolume() {
        speakerVolume--
        println("Speaker volume decreased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }
    
    fun previousChannel() {
        channelNumber--
        println("Channel number decreased to $channelNumber.")
    }
    
    override fun turnOn() {
        super.turnOn()
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }
    
    fun decreaseBrightness() {
        brightnessLevel--
        println("Brightness decreased to $brightnessLevel.")
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

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {

    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        smartTvDevice.turnOn()
        if(smartTvDevice.deviceStatus == "on"){
            deviceTurnOnCount++
        }
    }

    fun turnOffTv() {
        smartTvDevice.turnOff()
        if (smartTvDevice.deviceStatus == "off"){
            deviceTurnOnCount--
        }
    }

    fun increaseTvVolume() {
        if (smartTvDevice.deviceStatus == "on"){
            smartTvDevice.increaseSpeakerVolume()
        }else{
            println("Device is Off, cant increase the volume.")
        }
    }
    
    fun decreaseTvVolume(){
        if (smartTvDevice.deviceStatus == "on"){
            smartTvDevice.decreaseSpeakerVolume()
        }else{
            println("Device is Off, cant decrease the volume.")
        }
    }
    
    fun changeTvChannelToNext() {
        if (smartTvDevice.deviceStatus == "on"){
            smartTvDevice.nextChannel()
        }else{
            println("Device is Off, cant increase channel.")
        }
    }
    
    fun changeTvChanneltoPrevious() {
        if (smartTvDevice.deviceStatus == "on") {
            smartTvDevice.previousChannel()
        }else{
            println("Device is Off, cant go to previuos channel.")
        }
    }

    fun turnOnLight() {
        smartLightDevice.turnOn()
        if (smartLightDevice.deviceStatus == "on") {
            deviceTurnOnCount++
        }
    }

    fun turnOffLight() {
        smartLightDevice.turnOff()
        if (smartLightDevice.deviceStatus == "off") {
            deviceTurnOnCount--
        }
    }

    fun increaseLightBrightness() {
        if (smartLightDevice.deviceStatus == "on") {
            smartLightDevice.increaseBrightness()
        }else{
            println("Light is off. Cannot increase brightness.")
        }
    }
    
    fun decreaseLightBrightness() {
        if (smartLightDevice.deviceStatus == "on") {
            smartLightDevice.decreaseBrightness()
        }else{
            println("Light is off. Cannot decrease brightness.")
        }
    }
    
    fun printSmartTvInfo(){
        smartTvDevice.printDeviceInfo()
    }
    
    fun printSmartLightInfo(){
        smartLightDevice.printDeviceInfo()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

fun main() {
    val smartHome = SmartHome(
        smartTvDevice = SmartTvDevice("Android TV", "Entertainment"),
        smartLightDevice = SmartLightDevice("Google Light", "Utility")
    )

    smartHome.turnOnTv()
    smartHome.increaseTvVolume()
    smartHome.decreaseTvVolume()
    smartHome.changeTvChannelToNext()
    smartHome.changeTvChanneltoPrevious()
    smartHome.printSmartTvInfo()

    smartHome.turnOnLight()
    smartHome.increaseLightBrightness()
    smartHome.decreaseLightBrightness()
    smartHome.printSmartLightInfo()

    smartHome.turnOffAllDevices()
}
