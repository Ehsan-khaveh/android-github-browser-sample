package com.kuba.example.projects.api.navigation

import android.os.Bundle
import com.kuba.example.navigation.api.ControllerDestination

class RepositorySearchScreen : ControllerDestination {
    override val route: String = "repos/search"
    override val args: Bundle? = null
}