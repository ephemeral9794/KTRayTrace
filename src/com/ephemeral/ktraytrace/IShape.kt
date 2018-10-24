package com.ephemeral.ktraytrace

interface IShape {
    fun intersect(ray: Ray, near: Float, far: Float) : Hit?
}