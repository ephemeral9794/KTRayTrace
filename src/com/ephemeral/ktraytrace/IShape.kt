package com.ephemeral.ktraytrace

interface IShape {
    fun intersect(ray : Ray, near : Double, far : Double) : Hit?
}