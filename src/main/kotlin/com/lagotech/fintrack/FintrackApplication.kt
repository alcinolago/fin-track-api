package com.lagotech.fintrack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("com.lagotech.fintrack.domain.model")
class FintrackApplication

fun main(args: Array<String>) {
	runApplication<FintrackApplication>(*args)
}
