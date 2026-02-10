package com.example.everydaysurvival.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.everydaysurvival.screens.AboutScreen
import com.example.everydaysurvival.screens.CategoriesScreen
import com.example.everydaysurvival.screens.HomeScreen
import com.example.everydaysurvival.screens.SavedPostsScreen
import androidx.compose.ui.graphics.Color
import com.example.everydaysurvival.PostViewModel
import com.example.everydaysurvival.ui.theme.cardColor

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    data object SavedPosts : BottomNavItem("savedPosts", "Saved", Icons.Default.Favorite)
    data object Categories : BottomNavItem("categories", "Search", Icons.Default.Search)
    data object About : BottomNavItem(
        "about", "About", Icons.Default.SupportAgent)
}

@Composable
fun Navigation(modifier: Modifier) {
    val navController = rememberNavController()
    val postViewModel: PostViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController, postViewModel = postViewModel)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(navController = navController, postViewModel = postViewModel)
            }
            composable(BottomNavItem.SavedPosts.route) {
                SavedPostsScreen(navController = navController, postViewModel = postViewModel)
            }
            composable(BottomNavItem.Categories.route) {
                CategoriesScreen()
            }
            composable(BottomNavItem.About.route) {
                AboutScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    postViewModel: PostViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = cardColor,
        contentColor = Color.White
    ) {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.SavedPosts,
            BottomNavItem.Categories,
            BottomNavItem.About,
        )

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black,
                    indicatorColor = Color.White.copy(alpha = 0.10f)
                )
            )
        }
    }
}