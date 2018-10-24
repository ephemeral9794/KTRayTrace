package com.ephemeral.ktraytrace

import kotlin.math.*

fun dot(v1: Vector, v2: Vector): Float = (v1.x * v2.x + v1.y * v2.y + v1.z * v2.z)
fun cross(v1: Vector, v2: Vector): Vector = Vector(
        v1.y * v2.z - v1.z * v2.y,
        v1.z * v2.x - v1.x * v2.z,
        v1.x * v2.y - v1.y * v2.z)
fun lerp(t: Float, a: Vector, b: Vector): Vector = (a * (1 - t) + b * t)
fun unit(v: Vector): Vector {
    val inv = v.inverseLength
    return v * inv
}
fun radian(deg: Float): Float = (deg / 180.0f * Math.PI.toFloat())
fun degree(rad: Float): Float = (rad / PI.toFloat() * 180.0f)
operator fun Float.times(other: Vector) : Vector = (other * this)
fun abs(v : Vector) : Vector = Vector(abs(v.x), abs(v.y), abs(v.z))

class Vector(var x : Float, var y : Float, var z : Float) {
    companion object {
        val ZERO = Vector(0.0f, 0.0f, 0.0f)
        val ONE = Vector(1.0f, 1.0f, 1.0f)
        val XAXIS = Vector(1.0f, 0.0f, 0.0f)
        val YAXIS = Vector(0.0f, 1.0f, 0.0f)
        val ZAXIS = Vector(0.0f, 0.0f, 1.0f)
    }

    val length : Float
        get() = sqrt(dot(this, this))
    val inverseLength : Float
        get() = 1.0f / sqrt(dot(this, this))

    constructor() : this(0.0f, 0.0f, 0.0f)
    constructor(src : Vector) : this(src.x, src.y, src.z)

    fun initialize(x: Float, y: Float, z: Float) {
        this.x = x
        this.y = y
        this.z = z
    }
    fun normalInitialize(x: Float, y: Float, z: Float) {
        this.x = x * inverseLength
        this.y = y * inverseLength
        this.z = z * inverseLength
    }
    fun normalize(): Vector {
        x *= inverseLength
        y *= inverseLength
        z *= inverseLength
        return this
    }

    // operator-
    operator fun unaryMinus() : Vector = Vector(-this.x, -this.y, -this.z)
    // operator+
    operator fun plus(other : Vector) : Vector = Vector(this.x + other.x, this.y + other.y, this.z + other.z)
    // operator-
    operator fun minus(other : Vector) : Vector = Vector(this.x - other.x, this.y - other.y, this.z - other.z)
    // operator*
    operator fun times(other : Float) : Vector = Vector(this.x * other, this.y * other, this.z * other)
    // operator/
    operator fun div(other : Vector) : Vector = Vector(this.x / other.x, this.y / other.y, this.z / other.z)
    operator fun div(other : Float) : Vector {
        val inv = 1.0f / other
        return Vector(this.x / inv, this.y / inv, this.z / inv)
    }
    // equals
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other === null) return false
        if (this.javaClass != other.javaClass) return false
        other as Vector
        if (this.x != other.x || this.y != other.y || this.z != other.z) return false
        return true
    }
    override fun hashCode(): Int {
        return x.hashCode() xor y.hashCode() xor z.hashCode()
    }
    override fun toString(): String {
        return "[$x,$y,$z]"
    }
}