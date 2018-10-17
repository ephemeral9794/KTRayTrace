package com.ephemeral.ktraytrace

interface Shape {
    fun intersect(ray: Ray, near: Float, far: Float) : Hit?
}