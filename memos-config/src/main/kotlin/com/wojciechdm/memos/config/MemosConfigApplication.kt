package com.wojciechdm.memos.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigServer
class MemosConfigApplication

fun main(args: Array<String>) {
	runApplication<MemosConfigApplication>(*args)
}
