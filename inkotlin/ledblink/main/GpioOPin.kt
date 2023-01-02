package inkotlin.ledblink.main

import java.io.FileWriter
import java.io.FileReader

import java.lang.ProcessBuilder


class GpioPin {
    private var pathToGpio: String = "/sys/class/gpio"
    private lateinit var pathToGpioPin : String;


    fun setup(pin : Int){
        registerThisPin(pin)
        pathToGpioPin = "$pathToGpio/gpio$pin"
        // listOfRegisteredPins.add(pin)
    }




    /*
    return path to GPIO Pin
     */
    fun printPathToPin(){
        println(pathToGpioPin)
    }

    /*
    unregister pin in gpio
     */
    fun unregisterThisPin(pin: Int) = ProcessBuilder("/bin/bash", "-c", "echo $pin > /sys/class/gpio/unexport").start().waitFor()

    /*
    register pin in gpio whe setup is running
     */
    private fun registerThisPin(pin: Int) = ProcessBuilder("/bin/bash", "-c", " echo $pin > /sys/class/gpio/export").start().waitFor()



    /*
    value for direction
        "in" for input
        "out" for output
     */
    fun setPinDirection(direction : String){
        try {
            val filewrite : FileWriter = FileWriter("$pathToGpioPin/direction")
            filewrite.write(direction)
            filewrite.close()
        } catch (e : Exception){
            println(e)
        }
    }

    /*
    values are
    "1" for on (example: LED light up)
    "0" for off (example: LED light off)
     */
    fun setPinValue(value : String){
        try {
            val filewrite : FileWriter = FileWriter("$pathToGpioPin/value")
            filewrite.write(value)
            filewrite.close()
        } catch (e : Exception){
            println(e)
        }
    }
    /*
    return direction of Pin
     */
    fun getPinDirection(){
        println( FileReader("$pathToGpioPin/direction").readLines())

    }
}