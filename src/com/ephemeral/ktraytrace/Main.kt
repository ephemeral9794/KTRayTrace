package com.ephemeral.ktraytrace

import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
    val path = "C:\\Users\\Administrator\\Desktop\\result_aa.ppm"
    println("file : $path")

    // image data
    val image = Image(1280, 720)
    // environment parameter
    val env = Environment.CornellBox
    env.samples = 32
    val scene = Scene(env, image)

    for (shape in env.shapes) {
        println(shape)
    }
    val time = measureNanoTime { scene.render() }

    println("time : ${time.toDouble() / 1000000.0} ms")
    image.export(path)
}
