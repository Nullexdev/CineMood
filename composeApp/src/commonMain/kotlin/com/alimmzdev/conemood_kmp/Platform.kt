package com.alimmzdev.conemood_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform