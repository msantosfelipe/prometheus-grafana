package com.fms.repository

import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import java.util.Optional
import com.fms.model.Package

@MongoRepository
interface PackagesRepository : CrudRepository<Package, String> {
    fun findByName(name: String): Optional<Package>
}
