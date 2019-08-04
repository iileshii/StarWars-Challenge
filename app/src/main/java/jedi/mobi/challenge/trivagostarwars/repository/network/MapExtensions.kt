package jedi.mobi.challenge.trivagostarwars.repository.network

import androidx.core.net.toUri

fun String.getIdFromUrl(): Long {
    return toUri().lastPathSegment?.toLong() ?: throw IllegalArgumentException("Can't parse this url to id")
}
