package com.example.justdoit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.justdoit.ui.viewmodel.NotesViewModel
import com.example.justdoit.ui.screen.DetailScreen
import com.example.justdoit.ui.screen.HomeScreen
import com.example.justdoit.ui.screen.ProfileScreen
import com.example.justdoit.ui.screen.SettingsScreen
import com.example.justdoit.ui.viewmodel.SessionViewModel

/**
 * Graph de navigation.
 * - route 'home' affiche HomeScreen
 * - route 'detail/{noteId}' affiche DetailScreen
 * - route 'settings' affiche SettingsScreen
 */
@Composable
fun NavGraph(viewModel: NotesViewModel, sessionVm: SessionViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.HOME) {
        composable(Destinations.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onOpenNote = { noteId ->
                    navController.navigate("${Destinations.DETAIL}/$noteId")
                },
                onOpenSettings = {
                    navController.navigate(Destinations.SETTINGS)
                }
            )
        }

        // detail/{noteId}
        composable(
            route = "${Destinations.DETAIL}/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            DetailScreen(
                noteId = noteId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Destinations.SETTINGS) {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                onProfileClick = { navController.navigate(Destinations.PROFILE) }
            )
        }

        composable(Destinations.PROFILE) {
            ProfileScreen(sessionVm)
        }
    }
}