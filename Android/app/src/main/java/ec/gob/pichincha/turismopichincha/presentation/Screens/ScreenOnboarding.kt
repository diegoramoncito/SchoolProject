package ec.gob.pichincha.turismopichincha.presentation.Screens


import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
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
import ec.gob.pichincha.turismopichincha.R
import ec.gob.pichincha.turismopichincha.navigation.Screen
import ec.gob.pichincha.turismopichincha.ui.theme.TurismoPichinchaTheme
import kotlinx.coroutines.delay


@Composable
fun ScreenOnboarding(actionClicked: () -> Unit) {
    val scale = remember {
        //comienza scala en cero
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        //Comienza la animación
        scale.animateTo(
            //Escala final
            targetValue = 1f,
            //duracion de la animación
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        actionClicked()
    }
    Image(
        painter = painterResource(id = R.drawable.splash),
        contentDescription = "Imagen de presentación",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .scale(scale.value)
            .fillMaxSize()
            .clickable(
                enabled = true,
                onClickLabel = "Se dio click en la imagen",
                onClick = actionClicked
            )
    )
}



