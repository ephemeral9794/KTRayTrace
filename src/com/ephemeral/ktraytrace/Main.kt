package com.ephemeral.ktraytrace

fun usage() {
    println("KTRayTrace [output]")
}

fun main(args: Array<String>) {
    // file path from arguments[0]
    val filename : String
    if (!args.isEmpty()) {
        filename = args[0]
    } else {
        usage()
        return
    }
    println("file : $filename")

    // image
    val image = Image(1280, 640, Color.GRAY)
    val scene = Scene(image, Color.GRAY)
    val near = 0.0f
    val far = 10000.0f
    scene.render(near, far)
    image.export(filename)
}