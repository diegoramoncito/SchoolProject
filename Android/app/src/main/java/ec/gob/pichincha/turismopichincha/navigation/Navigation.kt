package ec.gob.pichincha.turismopichincha.navigation


import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import ec.gob.pichincha.turismopichincha.presentation.Screens.*
import okhttp3.Route

@Composable
fun Navigation(navController: NavHostController) {
    //DEFINICIÓN DE RUTAS
    NavHost(navController = navController, startDestination = Screen.ScreenHome.Route) {

        composable(Screen.ScreenHome.Route) {
            ScreenHome(
                navegarDetalleSitio= {
                    navController.navigate(Screen.ScreenDetalleSitio.Route)
                }
            )
        }

        //SCREEN DETALLE SITIO
        composable(
            Screen.ScreenDetalleSitio.Route,
            //Para evitar problemas con datos null
            arguments = listOf(navArgument("idDataDetalleSitio") { defaultValue = "Id is empty" })
        ) { NavBackStackEntry ->
            //preparación para rx un parámetro
            var rxIdDetalleSitio = NavBackStackEntry.arguments?.getString("idDataDetalleSitio")
            requireNotNull(rxIdDetalleSitio)
            ScreenDetalleSitio(idDetalleSitio = rxIdDetalleSitio, navController = navController)
        }
        //TABS

        //SCREEN BUSQUEDA
        composable(Screen.ScreenBusqueda.Route) {
            ScreenBusqueda()
        }

        //SCREEN FAVORITOS
        composable(Screen.ScreenFavoritos.Route) {
            ScreenFavoritos(
                navegarDetalleSitio = {
                    navController.navigate(Screen.ScreenDetalleSitio.Route)
                }
            )
        }

        //SCREEN ITINERARIOS
        composable(Screen.ScreenItinerarios.Route) {
            ScreenItinerarios(
                navegarDetalleSitio = {
                    navController.navigate(Screen.ScreenDetalleSitio.Route)
                }
            )
        }

        //SCREEN CONTACTO
        composable(Screen.ScreenContacto.Route) {
            ScreenContacto()
        }


    }
}