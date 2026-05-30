package tech.nullexdev.cinemood

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import tech.nullexdev.cinemood.core.navigation.Screen
import tech.nullexdev.cinemood.feature.favorite.FavoriteScreen
import tech.nullexdev.cinemood.feature.home.HomeScreen
import tech.nullexdev.cinemood.feature.search.SearchScreen
import tech.nullexdev.cinemood.feature.settings.SettingsScreen
import tech.nullexdev.cinemood.theme.MyKMPAppTheme
import tech.nullexdev.cinemood.theme.ThemeState
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val navSerializationConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Screen.Home::class)
            subclass(Screen.Search::class)
            subclass(Screen.Favorite::class)
            subclass(Screen.Settings::class)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview
fun App() {
    val themeState = remember { ThemeState() }
    val backStack = rememberNavBackStack(
        configuration = navSerializationConfig,
        Screen.Home,
    )

    MyKMPAppTheme(themeState = themeState) {
        Scaffold(
            topBar = { CMTopAppBar() },
            bottomBar = {
                CMNavigationBar(
                    currentScreen = backStack.last(),
                    onNavigate = { screen ->
                        if (backStack.last() != screen) {
                            // Clear backstack and push the new screen for bottom nav
                            backStack.clear()
                            backStack.add(screen)
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                NavDisplay(
                    backStack = backStack,
                    onBack = {
                        if (backStack.size > 1) {
                            backStack.removeLast()
                        }
                    },
                    entryProvider = entryProvider {
                        entry<Screen.Home> { HomeScreen() }
                        entry<Screen.Search> { SearchScreen() }
                        entry<Screen.Favorite> { FavoriteScreen() }
                        entry<Screen.Settings> { SettingsScreen() }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CMTopAppBar() {
    TopAppBar(
        title = { Text("CineMood") },
        actions = {
            Row {
                IconButton(
                    onClick = {

                    },
                    shapes = IconButtonShapes(shape = CircleShape),
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon"
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {

                    },
                    shapes = IconButtonShapes(shape = CircleShape),
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite Icon"
                            )
                        }
                    }
                )
            }
        }
    )
}

@Composable
fun CMNavigationBar(
    currentScreen: NavKey,
    onNavigate: (Screen) -> Unit,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = Color.Red,
        tonalElevation = 0.dp
    ) {
        NavigationBarItem(
            selected = currentScreen == Screen.Home,
            onClick = { onNavigate(Screen.Home) },
            label = { Text("Home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Navigation Home Icon") },
            colors = NavigationBarItemColors(
                selectedTextColor = Color.Red,
                selectedIconColor = Color.Red,
                selectedIndicatorColor = Color.Red.copy(alpha = 0.15f),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                disabledIconColor = Color.Gray.copy(alpha = 0.2f),
                disabledTextColor = Color.Gray.copy(alpha = 0.2f)
            )
        )
        NavigationBarItem(
            selected = currentScreen == Screen.Search,
            onClick = { onNavigate(Screen.Search) },
            label = { Text("Search") },
            icon = { Icon(Icons.Default.Search, contentDescription = "Navigation Search Icon") },
            colors = NavigationBarItemColors(
                selectedTextColor = Color.Red,
                selectedIconColor = Color.Red,
                selectedIndicatorColor = Color.Red.copy(alpha = 0.15f),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                disabledIconColor = Color.Gray.copy(alpha = 0.2f),
                disabledTextColor = Color.Gray.copy(alpha = 0.2f)
            )
        )
        NavigationBarItem(
            selected = currentScreen == Screen.Favorite,
            onClick = { onNavigate(Screen.Favorite) },
            label = { Text("Favorite") },
            icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = "Navigation Favorite Icon") },
            colors = NavigationBarItemColors(
                selectedTextColor = Color.Red,
                selectedIconColor = Color.Red,
                selectedIndicatorColor = Color.Red.copy(alpha = 0.15f),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                disabledIconColor = Color.Gray.copy(alpha = 0.2f),
                disabledTextColor = Color.Gray.copy(alpha = 0.2f)
            )
        )
        NavigationBarItem(
            selected = currentScreen == Screen.Settings,
            onClick = { onNavigate(Screen.Settings) },
            label = { Text("Settings") },
            icon = { Icon(Icons.Default.Settings, contentDescription = "Navigation Settings Icon") },
            colors = NavigationBarItemColors(
                selectedTextColor = Color.Red,
                selectedIconColor = Color.Red,
                selectedIndicatorColor = Color.Red.copy(alpha = 0.15f),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                disabledIconColor = Color.Gray.copy(alpha = 0.2f),
                disabledTextColor = Color.Gray.copy(alpha = 0.2f)
            )
        )
    }
}
