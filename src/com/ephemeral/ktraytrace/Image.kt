package com.ephemeral.ktraytrace

import java.io.File
import kotlin.math.pow

fun clamp(input: Double, min : Double, max : Double) : Double = when{
    input < min -> min
    input > max -> max
    else -> input
}
fun clamp(input: Int, min : Int, max : Int) : Int = when{
    input < min -> min
    input > max -> max
    else -> input
}
fun tonemap(f : Double) : Int = clamp((f.pow(1.0/2.2) * 255.0).toInt(), 0, 255)

class Color(var red : Double, var green : Double, var blue : Double) {
    companion object {
        val BLACK = Color(0.0, 0.0, 0.0)
        val WHITE = Color(1.0, 1.0, 1.0)
        val GRAY = Color(0.5,0.5,0.5)
    }

    constructor() : this(BLACK)
    constructor(c : Color) : this(c.red, c.green, c.blue)

    operator fun plus(other : Color) : Color = Color(this.red + other.red, this.green + other.green, this.blue + other.blue)
    operator fun unaryMinus() : Color = Color(-this.red, -this.green, -this.blue)
    operator fun minus(other : Color) : Color = Color(this.red - other.red, this.green - other.green, this.blue - other.blue)
    operator fun times(other : Double) : Color = Color(this.red * other, this.green * other, this.blue * other)
    operator fun div(other : Double) : Color = Color(this.red / other, this.green / other, this.blue / other)
    operator fun div(other : Int) : Color = Color(this.red / other, this.green / other, this.blue / other)
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