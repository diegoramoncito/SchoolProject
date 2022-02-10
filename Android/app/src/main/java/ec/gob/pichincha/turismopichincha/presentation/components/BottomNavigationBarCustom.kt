package ec.gob.pichincha.turismopichincha.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ec.gob.pichincha.turismopichincha.navigation.Screen
import ec.gob.pichincha.turismopichincha.ui.theme.GrayBackgroundComponents

@Composable
fun BottomNavigationBarCustom(
    navController: NavHostController,
    items: List<Screen>
) {
    val currentRoute = currentRoute(navController = navController)

    Card(
        shape = RoundedCornerShape(25.dp),
        elevation = 15.dp,
        modifier = Modifier
            .padding(
                start = 32.dp,
                end = 32.dp,
                bottom = 20.dp,
                top = 10.dp
            )
            .background(color = Color.Transparent)

    ) {

        BottomNavigation(
            //Diseño de la barra de navegación
            modifier = Modifier
                .background(GrayBackgroundComponents),
            backgroundColor = Color.Transparent,
        ) {
            items.forEach { screen ->
                BottomNavigationItem(
                    modifier = Modifier.background(GrayBackgroundComponents, shape = RectangleShape),
                    unselectedContentColor = Color.Gray,
                    selectedContentColor = MaterialTheme.colors.primary,
                    icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                    //Si la ruta actual es la seleccionada le marca como seleccionada
                    selected = currentRoute == screen.Route,
                    onClick = {
                        navController.navigate(screen.Route) {
                            //Elimina pila de navegación tal que si doy atrás salga del app
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            //Si se presiona el mismo elemento varias veces no se crea una pila de navegacion mas extensa
                            launchSingleTop = true
                        }
                    },
                    //muestra el label solo del icono selsccionado
                    alwaysShowLabel = false
                )
            }
        }
    }

}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}