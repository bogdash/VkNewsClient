package com.bogdash.vknewsclient.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bogdash.vknewsclient.ViewModel
import com.bogdash.vknewsclient.domain.NavigationItem
import com.bogdash.vknewsclient.navigation.AppNavGraph
import com.bogdash.vknewsclient.navigation.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    viewModel: ViewModel
) {
    val navHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            navHostController.navigate(item.screen.route) {
                                popUpTo(Screen.NewsFeed.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navHostController,
            homeScreenContent = {
                HomeScreen(
                    paddingValues = paddingValues,
                    viewModel = viewModel
                )
            },
            favoriteScreenContent = {
                TextCounter(modifier = Modifier.padding(paddingValues), name = "Favorite")
            },
            profileScreenContent = {
                TextCounter(modifier = Modifier.padding(paddingValues), name = "Profile")
            }
        )
    }
}

@Composable
private fun TextCounter(
    modifier: Modifier = Modifier,
    name: String
) {
    var count by rememberSaveable { mutableIntStateOf(0) }
    Text(
        modifier = modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black
    )
}