package com.ephemeral.ktraytrace

import kotlin.io.*
import java.io.*

// utility
fun clamp(input: Float, min: Float, max: Float): Float = when{
        (input > max) -> max
        (input < min) -> min
        else -> input
}
fun floatToByte(inValue:Float):Byte = when {
        (inValue <= 0.0f) -> 0.toByte()
        (inValue > 1.0f - 0.5f / 255.0f) -> 255.toByte()
        else -> (255.0f * inValue + 0.5f).toByte()
}

class Color(var red : Float = 0.0f, var green : Float = 0.0f, var blue : Float = 0.0f) {
    // default colors
    companion object {
        val WHITE = Color(1.0f, 1.0f, 1.0f)
        val BLACK = Color(0.0f, 0.0f, 0.0f)
        val GRAY = Color(0.5f, 0.5f, 0.5f)
        val RED = Color(1.0f, 0.0f, 0.0f)
        val GREEN = Color(0.0f, 1.0f, 0.0f)
        val BLUE = Color(0.0f, 0.0f, 1.0f)
    }

    constructor(color : Color) : this(color.red, color.green, color.blue)
    constructor(intensity : Float) : this(intensity, intensity, intensity)

    fun clamp(max : Float = 1.0f, min : Float = 0.0f) : Color {
        red = clamp(red, min, max)
        green = clamp(green, min, max)
        blue = clamp(blue, min, max)
        return this
    }
    fun lerp(t: Float, c1: Color, c2: Color): Color {
        return (1.0f - t) * c1 + t * c2
    }

    // operator-
    operator fun unaryMinus() : Color = Color(-this.red, -this.green, -this.blue)
    // operator+
    operator fun plus(other : Color) : Color = Color(this.red + other.red, this.green + other.green, this.blue + other.blue)
    // operator-
    operator fun minus(other : Color) : Color = Color(this.red - other.red, this.green - other.green, this.blue - other.blue)
    // operator*
    operator fun times(other : Color) : Color = Color(this.red * other.red, this.green * other.green, this.blue * other.blue)
    operator fun times(other : Float) : Color = Color(this.red * other, this.green * other, this.blue * other)
    operator fun Float.times(other: Color) : Color = (other * this)
    // operator/
    operator fun div(other : Color) : Color = Color(this.red / other.red, this.green / other.green, this.blue / other.blue)
    operator fun div(other : Float) : Color {
        val inv = 1.0f / other
        return Color(this.red / inv, this.green / inv, this.blue / inv)
    }
    // equals
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other === null) return false
        if (this.javaClass != other.javaClass) return false
        other as Color
        if (this.red != other.red || this.green != other.green || this.blue != other.blue) return false
        return true
    }
    override fun hashCode(): Int {
        return red.hashCode() xor green.hashCode() xor blue.hashCode()
    }
    override fun toString(): String {
        return "[$red,$green,$blue]"
    }
}

class Image(width : Int, height : Int, back : Color = Color.BLACK) {
    val width: Int
    val height : Int
    val pixels : Array<Array<Color>>
    init {
        this.width = width
        this.height = height
        pixels = Array(width, { Array(height, { back }) })
    }

    // operator overload
    operator fun get(x : Int, y : Int) : Color {
        return pixels[x][y]
    }
    operator fun set(x : Int, y : Int, element : Color) {
        pixels[x][y] = element
    }

    fun export(path : String) : Boolean {
        try {
            val file = File(path)
            if (!file.exists()) {
                file.createNewFile()
            }
            val stream = file.outputStream()
            stream.write("P6\n".toByteArray())
            stream.write("$width $height\n".toByteArray())
            stream.write("255\n".toByteArray())
            for (y in 0 until height) {
                for (x in 0 until width) {
                    val color = pixels[x][y]
                    color.clamp()
                    val r = floatToByte(color.red)
                    val g = floatToByte(color.green)
                    val b = floatToByte(color.blue)
                    stream.write(byteArrayOf(r, g, b))
                }
            }
            stream.close()
            return true
        } catch (e : Exception) {
            e.printStackTrace()
            return false
        }
    }
}