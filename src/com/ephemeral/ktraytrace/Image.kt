package com.ephemeral.ktraytrace

import java.io.File

class Color(var red : Double, var green : Double, var blue : Double) {
    companion object {
        val BLACK = Color(0.0, 0.0, 0.0)
        val WHITE = Color(1.0, 1.0, 1.0)
    }

    constructor() : this(BLACK)
    constructor(c : Color) : this(c.red, c.green, c.blue)
}

class Image(val width : Int, val height : Int) {
    val pixels : Array<Array<Color>>
    init {
        pixels = Array(width, { Array(height, { Color(0.0, 0.0, 0.0)}) })
    }

    operator fun get(x : Int, y : Int) : Color = pixels[x][y]
    operator fun set(x : Int, y : Int, element: Color) {
        pixels[x][y] = element
    }

    fun export(path : String) {
        val file = File(path)
        if (!file.exists()) {
            file.createNewFile()
        }
        file.outputStream().bufferedWriter(Charsets.US_ASCII).use {
            it.write("P3\n")
            it.write("$width $height\n")
            it.write("255\n")
            for (y in 0 until height) {
                for (x in 0 until width) {
                    val color = pixels[x][y]
                    it.write("${tonemap(color.red)} ${tonemap(color.green)} ${tonemap(color.blue)}\n")
                }
            }
        }
    }
}