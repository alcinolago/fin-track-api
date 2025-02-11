package com.lagotech.fintrack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FintrackApplication

fun main(args: Array<String>) {
	runApplication<FintrackApplication>(*args)
}
