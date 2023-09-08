package dev.techullurgy.data.annotations

@Target(AnnotationTarget.TYPE)
annotation class RepositoryModel
@Target(AnnotationTarget.TYPE)
annotation class DomainModel

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
annotation class DatabaseModel
