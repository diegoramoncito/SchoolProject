package ec.gob.pichincha.turismopichincha.navigation


import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ec.gob.pichincha.turismopichincha.presentation.Screens.*
import ec.gob.pichincha.turismopichincha.viewModel.DestinoViewModel
import okhttp3.Route

@Composable
fun Navigation(
    navController: NavHostController,
    destinoViewModel: DestinoViewModel = hiltViewModel()
) {
    //DEFINICIÓN DE RUTAS
    NavHost(navController = navController, startDestination = Screen.ScreenHome.Route) {

        composable(Screen.ScreenHome.Route) {
            ScreenHome(
                navegarDetalleSitio= { idx ->
                    navController.navigate(Screen.ScreenDetalleSitio.Route + "/${idx}")
                },
                viewModel = destinoViewModel
            )
        }

        //SCREEN DETALLE SITIO
        composable(
            Screen.ScreenDetalleSitio.Route + "/{idx}",
            //Para evitar problemas con datos null
            arguments = listOf(
                navArgument("idDataDetalleSitio") { defaultValue = "Id is empty" },
                navArgument("idx") {
                    type = NavType.IntType
                }
            )
        ) { NavBackStackEntry ->
            //preparación para rx un parámetro
            var rxIdDetalleSitio = NavBackStackEntry.arguments?.getString("idDataDetalleSitio")
            requireNotNull(rxIdDetalleSitio)
            ScreenDetalleSitio(
                idDetalleSitio = rxIdDetalleSitio,
                navController = navController,
                viewModel = destinoViewModel,
                idx = NavBackStackEntry.arguments?.getInt("idx") ?: 0
            )
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