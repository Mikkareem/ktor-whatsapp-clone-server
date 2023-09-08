package dev.techullurgy.data.init.schema

import dev.techullurgy.data.utils.ForeignKey

internal interface TableStructure {
    val typeMap: Map<String, String>
    val foreignKeys: List<ForeignKey>?
}