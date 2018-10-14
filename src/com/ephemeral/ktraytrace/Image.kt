package com.ephemeral.ktraytrace

import kotlin.io.*
import java.io.*;

fun clamp(input: Float, min: Float, max: Float): Float {
    return if (input > max) {
        max
    } else if (input < min) {
        min
    } else {
        input
    }
}
fun FloatToByte(inValue:Float):Byte {
    return if (inValue <= 0.0f)
        0.toByte()
    else if (inValue > 1.0f - 0.5f / 255.0f)
        255.toByte()
    else
        (255.0f * inValue + 0.5f).toByte()
}

class Color(var red : Float = 0.0f, var green : Float = 0.0f, var blue : Float = 0.0f) {
    companion object {
        val WHITE = Color(1.0f, 1.0f, 1.0f)
        val BLACK = Color(0.0f, 0.0f, 0.0f)
        val GRAY = Color(0.5f, 0.5f, 0.5f)
        val RED = Color(1.0f, 0.0f, 0.0f)
        val GREEN = Color(0.0f, 1.0f, 0.0f)
        val BLUE = Color(0.0f, 0.0f, 1.0f)
    }

    constructor(color : Color) : this(color.red, color.green, color.blue) {}
    constructor(intensity : Float) : this(intensity, intensity, intensity) {}

    fun clamp(max : Float = 1.0f, min : Float = 0.0f) : Color {
        red = clamp(red, min, max);
        green = clamp(green, min, max);
        blue = clamp(blue, min, max);
        return this;
    }
    fun lerp(t: Float, c1: Color, c2: Color): Color {
        return c1 * (1.0f - t) + c2 * t
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
        return "[$red,$green,$blue]";
    }
}

class Image(val width : Int, val height : Int) {
    val pixels : Array<Array<Color?>>
    init {
        pixels = Array(width, {arrayOfNulls<Color>(height)})
    }
    constructor(width : Int, height : Int, back : Color) : this(width, height) {
        for (y in 0..height) {
            for (x in 0..width) {
                pixels[x][y] = back.clamp()
            }
        }
    }

    operator fun get(x : Int, y : Int) : Color {
        return pixels[x][y] as Color
    }
    operator fun set(x : Int, y : Int, element : Color) {
        pixels[x][y] = element
    }
}