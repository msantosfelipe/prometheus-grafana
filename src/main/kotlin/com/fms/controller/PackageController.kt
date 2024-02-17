package com.fms.controller

import com.fms.model.Package
import com.fms.repository.PackagesRepository
import io.micrometer.core.instrument.MeterRegistry
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.validation.constraints.NotBlank

@Validated
@Controller("/package")
class PackageController(private val repo: PackagesRepository) {
    private val logger: Logger = LoggerFactory.getLogger(PackageController::class.java)
    @Inject
    lateinit var meterRegistry: MeterRegistry

    @Get("/")
    fun hello(): String = "hello"

    @Get("/{name}")
    fun searchPackages(@NotBlank name: String): String {
        val packagesFound = (0..20).random().toLong()

        savePackageSearch(name, packagesFound)
        sendCounter(name, packagesFound)

        logger.info("{}", mapOf("name" to name))
        return "$packagesFound packages found from $name"
    }

    private fun sendCounter(name: String, packagesFound: Long) =
        meterRegistry
            .counter("packages.found.counter", "param", name)
            .increment(packagesFound.toDouble())

    private fun savePackageSearch(name: String, packagesFound: Long){
        val result = repo.findByName(name = name)
        if (result.isEmpty) {
            repo.save(
                Package(
                    name = name,
                    packagesFound = packagesFound
                )
            )
        } else {
            val ping = result.get()
            ping.packagesFound+= packagesFound
            repo.update(ping)
        }
    }
}
