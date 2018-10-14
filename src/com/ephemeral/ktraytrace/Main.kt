package com.ephemeral.ktraytrace

fun main(args: Array<String>) {
    println("Hello World")
    val filename = if (!args.isEmpty()) {
        args[0]
    } else {
        ""
    }
    println("file : ${filename}")
    if (filename == "") {
        usage()
        return
    }

    var image = Image(10, 10, Color.BLACK)
    image[5, 5] = Color.WHITE
}

fun usage() {
    println("KTRayTrace [output]")
}