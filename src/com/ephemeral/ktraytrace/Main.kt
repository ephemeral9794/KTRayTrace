package com.ephemeral.ktraytrace

fun main(args: Array<String>) {
    println("Hello World")
    val filename = if (!args.isEmpty()) {
        args[0]
    } else {
        ""
    }
    println("file : $filename")
    if (filename == "") {
        usage()
        return
    }

    val image = Image(10, 10, Color.BLUE)
    image[5, 5] = Color.WHITE
    image[2, 3] = Color.RED
    image.export(filename)
}

fun usage() {
    println("KTRayTrace [output]")
}