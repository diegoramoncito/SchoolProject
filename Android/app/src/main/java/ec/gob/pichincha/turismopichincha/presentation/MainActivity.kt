package ec.gob.pichincha.turismopichincha.presentation

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ec.gob.pichincha.turismopichincha.R
import ec.gob.pichincha.turismopichincha.navigation.Navigation
import ec.gob.pichincha.turismopichincha.navigation.Screen
import ec.gob.pichincha.turismopichincha.navigation.Screen.*
import ec.gob.pichincha.turismopichincha.presentation.Screens.ScreenHome
import ec.gob.pichincha.turismopichincha.presentation.Screens.ScreenOnboarding
import ec.gob.pichincha.turismopichincha.presentation.components.BottomNavigationBarCustom
import ec.gob.pichincha.turismopichincha.ui.theme.TurismoPichinchaTheme
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurismoPichinchaTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navigationItems = listOf(
        ScreenBusqueda,
        ScreenFavoritos,
        ScreenHome,
        ScreenItinerarios,
        ScreenContacto
    )
//
//    //Variable para saber cuando se debe mostrar el Onboarding
//    var shouldShowOnboarding by remember {
//        mutableStateOf(true)
//    }
//
//    //Logica para solo ejecutar al inicio de la app el onboarding
//    if (shouldShowOnboarding) {
//        ScreenOnboarding(actionClicked = { shouldShowOnboarding = false })
//    } else {
        //Plantilla que provee diferentes elementos como bottom navigation, float action buttom
        Scaffold(
            //Plantilla de bottomBar
            bottomBar = {
                BottomNavigationBarCustom(
                    navController = navController,
                    items = navigationItems
                )
            }
        )
        {
            Navigation(navController = navController)
        }
//    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TurismoPichinchaTheme {
        MainScreen()
    }
}