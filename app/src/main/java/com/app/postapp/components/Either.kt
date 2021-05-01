package com.app.postapp.components

/*
* [Left] é para indicar erro.
* [Right] é para indicar sucesso.
*
* */

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()
}