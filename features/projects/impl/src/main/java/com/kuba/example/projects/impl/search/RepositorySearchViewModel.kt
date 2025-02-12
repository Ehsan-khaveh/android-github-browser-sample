package com.kuba.example.projects.impl.search

import androidx.lifecycle.ViewModel
import com.kuba.example.service.api.GithubService
import com.kuba.example.service.api.ServiceResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RepositorySearchViewModel @Inject constructor(private val githubService: GithubService) :
    ViewModel() {

    private val _state: MutableStateFlow<RepositoriesUiModel> =
        MutableStateFlow(RepositoriesUiModel.Content(emptyList()))
    val state: StateFlow<RepositoriesUiModel> = _state

    /**
     * Search Github repositories
     */
    suspend fun search(query: String) {
        val uiModel = if (query.isBlank()) {
            RepositoriesUiModel.Error("Provide a search query")
        } else {
            val result = githubService.searchRepos(query)
            when (result) {
                is ServiceResult.Success -> {
                    if (result.value.isEmpty()) {
                        RepositoriesUiModel.NoResults
                    } else {
                        RepositoriesUiModel.Content(result.value)
                    }
                }

                is ServiceResult.Failure -> RepositoriesUiModel.Error("Error: ${result.reason ?: result.throwable?.message ?: "unknown"}")
            }
        }

        _state.value = uiModel
    }
}
