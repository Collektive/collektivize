package it.unibo.collektive.collektivize

import io.kotest.core.spec.style.FreeSpec
import it.unibo.collektive.compiler.CollektiveJVMCompiler
import kotlin.io.path.createTempDirectory

class FieldedMembersGeneratorTest :
    FreeSpec({
        "The generator must generate valid kotlin code" {
            val tmpDirectory = createTempDirectory("colletivize")
            FieldedMembersGenerator
                .generateFieldFunctionsForTypes(FieldedMembersGenerator.baseTargetTypes)
                .map { it.writeTo(tmpDirectory).toFile() }
                .forEach { CollektiveJVMCompiler.compile(it) }
        }
    })