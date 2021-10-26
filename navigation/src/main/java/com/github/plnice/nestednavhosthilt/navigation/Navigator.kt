package com.github.plnice.nestednavhosthilt.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface Destination {
    val route: String
}

object DefaultDestination : Destination {
    override val route = ""
}

object Destinations {

    val default = DefaultDestination

    val authentication = object : Destination {
        override val route: String = "authentication"
    }

    val main = object : Destination {
        override val route: String = "main"
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavigator(impl: NavigatorImpl): Navigator
}

interface Navigator {
    val events: StateFlow<Destination>
    fun navigate(destination: Destination)
}

class NavigatorImpl @Inject constructor() : Navigator {
    private val _events = MutableStateFlow<Destination>(Destinations.default)
    override val events: StateFlow<Destination> = _events.asStateFlow()

    override fun navigate(destination: Destination) {
        _events.value = destination
    }
}
