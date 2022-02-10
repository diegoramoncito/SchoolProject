package ec.gob.pichincha.turismopichincha.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (
    val Route: String,
    val title: String,
    val icon: ImageVector
){
    object ScreenOnboarding : Screen("ruta_onboarding","Onboarding",Icons.Default.Home)
    object ScreenHome : Screen("ruta_home","Home", Icons.Default.Home)
    object ScreenDetalleSitio : Screen(
        "ruta_detallesitio/?idDataDetalleSitio={idDataDetalleSitio}",
        "Detalle del sitio",
        Icons.Default.Home
    )
    {
        fun createRoute (idDataDetalleSitio : String) = "ruta_detallesitio/?idDataDetalleSitio=$idDataDetalleSitio"
    }
    object ScreenBusqueda : Screen("ruta_busqueda","Búsqueda", Icons.Default.Search)
    object ScreenFavoritos : Screen("ruta_favoritos","Favoritos",Icons.Default.Favorite)
    object ScreenItinerarios : Screen("ruta_itinerarios","Itinerarios",Icons.Default.Place)
    object ScreenContacto : Screen("ruta_contacto","Contacto", Icons.Default.Settings) //getDrawable(getActivity(), R.drawable.ciclismo) as ImageVector )//  ResourcesCompat.getDrawable(getResources() , R.drawable.ciclismo, null) as ImageVector )//    Icons.Default.Settings)

    object ScreenTabFotos : Screen("ruta_tab_fotos","Fotos",Icons.Default.Home)
    object ScreenTabActividades : Screen("ruta_tab_actividades","Actividades",Icons.Default.Home)
    object ScreenTabServicio : Screen("ruta_tab_servicios","Servicios",Icons.Default.Home)
    object ScreenTabContacto : Screen("ruta_tab_contacto","Contacto",Icons.Default.Home)
}