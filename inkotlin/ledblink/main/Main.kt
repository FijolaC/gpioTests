package inkotlin.ledblink.main

fun main(args: Array<String>) {


    val ledPin = GpioPin()
    ledPin.setup(107)
    ledPin.setPinDirection("out")

    for (i  in 1..10){
      //  println("LED on")
        ledPin.setPinValue("1")
        Thread.sleep(1000)
     //   println("LED off")
        ledPin.setPinValue("0")
        Thread.sleep(1000)

    }


    ledPin.unregisterThisPin(107)

}