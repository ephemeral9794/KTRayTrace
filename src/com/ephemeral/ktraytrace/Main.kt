package com.ephemeral.ktraytrace

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
    val image = Image(10, 10, Color.BLUE)
    image[5, 5] = Color.WHITE
    image[2, 3] = Color.RED
    image.export(filename)
}

fun usage() {
    println("KTRayTrace [output]")
}