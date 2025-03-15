package com.divar.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DivarApplication

fun main(args: Array<String>) {
	runApplication<DivarApplication>(*args)
}
