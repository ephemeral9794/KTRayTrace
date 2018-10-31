package com.ephemeral.ktraytrace

import kotlin.math.*

class Sphere(val center : Vector, val radius : Double, val reflect : Vector) : IShape {
    override fun intersect(ray : Ray, near : Double, far : Double) : Hit? {
        val op : Vector = center - ray.origin
        val b = dot(op, ray.direct)
        val det = b * b - dot(op, op) + radius * radius
        if (det < 0)
            return null

        var hit : Hit?
        val t1 = b - sqrt(det)
        if (near < t1 && t1 < far) {
            hit = Hit(t1, Vector(), Vector(), this)
            hit.point = ray.origin + ray.direct * t1
            hit.normal = (hit.point - center).normalize()// / radius
            return hit
        }
        val t2 = b + sqrt(det)
        if (near < t2 && t2 < far) {
            hit = Hit(t1, Vector(), Vector(), this)
            hit.point = ray.origin + ray.direct * t2
            hit.normal = (hit.point - center).normalize()// / radius
            return hit
        }
        return null
    }

    override fun toString(): String = "Sphere($center, $radius)"
}