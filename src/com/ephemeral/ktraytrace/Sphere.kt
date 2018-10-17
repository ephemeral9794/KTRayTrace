package com.ephemeral.ktraytrace

import java.lang.Math.pow
import kotlin.math.*

class Sphere(var center : Vector, var radius : Float) : Shape {
    override fun intersect(ray : Ray, near : Float, far : Float) : Hit? {
        val oc = ray.origin - center
        val a = dot(ray.direct, ray.direct)
        val b = 2.0f * dot(ray.direct, oc)
        val c = dot(oc, oc) - radius * radius
        val D = b * b - 4 * a * c

        var hit : Hit? = null
        if (D > 0) {
            // y = (-b - √D) / 2a
            val root = sqrt(D)
            var temp = (-b - root) / (2.0f * a)
            if (temp < near && temp > far) {
                hit = Hit(temp, this)
                return hit
            }
            // y = (-b + √D) / 2a
            temp = (-b + root) / (2.0f * a)
            if (temp < far && temp > near) {
                hit = Hit(temp, this)
                return hit
            }
        }
        return hit
    }
}