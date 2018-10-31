package com.ephemeral.ktraytrace

import kotlin.math.*

// Mathematical function
fun dot(vec1 : Vector, vec2 : Vector) : Double = (vec1.x * vec2.x + vec1.y * vec2.y + vec1.z * vec2.z)
fun cross(vec1 : Vector, vec2 : Vector) : Vector = Vector(
    vec1.y * vec2.z - vec1.z * vec2.y,
    vec1.z * vec2.x - vec1.x * vec2.z,
    vec1.x * vec2.y - vec1.y * vec2.x
)
// operator overload
operator fun Double.times(other : Vector) : Vector = Vector(this * other.x, this * other.y, this * other.z)
fun abs(v : Vector) : Vector = Vector(abs(v.x), abs(v.y), abs(v.z))
fun radian(deg : Double) : Double = (deg * PI / 180.0)
fun degree(rad : Double) : Double = (rad * 180.0 / PI)

class Vector(var x : Double, var y : Double, var z : Double) {
    companion object {
        val ZERO = Vector(0.0, 0.0, 0.0)
        val ONE = Vector(1.0, 1.0, 1.0)
        val XAXIS = Vector(1.0, 0.0, 0.0)
        val YAXIS = Vector(0.0, 1.0, 0.0)
        val ZAXIS = Vector(0.0, 0.0, 1.0)
    }

    constructor() : this(ZERO)
    constructor(i : Double) : this(i, i, i)
    constructor(v : Vector) : this(v.x, v.y, v.z)

    // operator overload
    operator fun plus(other : Vector) : Vector = Vector(this.x + other.x, this.y + other.y, this.z + other.z)
    operator fun unaryMinus() : Vector = Vector(-this.x, -this.y, -this.z)
    operator fun minus(other : Vector) : Vector = Vector(this.x - other.x, this.y - other.y, this.z - other.z)
    operator fun times(other : Double) : Vector = Vector(this.x * other, this.y * other, this.z * other)
    operator fun div(other : Double) : Vector = Vector(this.x / other, this.y / other, this.z / other)

    fun normalize() : Vector = (this / sqrt(dot(this, this)))

    override fun toString(): String = "Vector[$x, $y, $z]"
}