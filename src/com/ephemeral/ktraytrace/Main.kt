package com.ephemeral.ktraytrace

import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
    val path = "C:\\Users\\Administrator\\Desktop\\result2.ppm"
    println("file : $path")

    // image data
    val image = Image(1280, 720)
    // environment parameter
    val env = Environment(
            //Camera(
            Vector(50.0,52.0,295.6),
            Vector(50.0,51.957388,294.6),
            //Vector(5.0,5.0,5.0),
            //Vector(0.0,0.0,0.0),
            Vector.YAXIS,
            30.0,
            image.width.toDouble(),
            image.height.toDouble(),
            32,
            mutableListOf(
                /*Sphere(Vector( -0.5, 0.0, 0.0), 1.0, Vector(1.0, 0.0, 0.0)),
                Sphere(Vector(0.5, 0.0, 0.0), 1.0, Vector(0.0, 1.0, 0.0))*/
                Sphere(Vector(-1e5 + 1,40.8,81.6), 1e5, Vector(0.75,0.25,0.25)),
                Sphere(Vector(1e5 + 99,40.8,81.6), 1e5, Vector(0.25,0.25,0.75)),
                Sphere(Vector(50.0,40.8,-1e5), 1e5, Vector(0.75)),
                Sphere(Vector(50.0,-1e5,81.6), 1e5, Vector(0.75)),
                Sphere(Vector(50.0,1e5 + 81.6,81.6), 1e5, Vector(0.75)),
                Sphere(Vector(27.0,16.5,47.0), 16.5, Vector(0.999)),
                Sphere(Vector(73.0,16.5,78.0), 16.5, Vector(0.999)),
                Sphere(Vector(50.0,681.6 - 0.27,81.6), 600.0, Vector())
            )
    )
    val scene = Scene(env, image)

    for (shape in env.shapes) {
        println(shape)
    }
    val time = measureNanoTime { scene.render() }

    println("time : ${time.toDouble() / 1000000.0} ms")
    image.export(path)
}
