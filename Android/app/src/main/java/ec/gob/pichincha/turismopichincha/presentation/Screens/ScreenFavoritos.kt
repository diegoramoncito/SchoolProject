package ec.gob.pichincha.turismopichincha.presentation.Screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.gob.pichincha.turismopichincha.R

@Composable
fun ScreenFavoritos(navegarDetalleSitio: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        subTitle(text = "Favoritos", 30)
        LazyColumn() {
            repeat(5) {
                item {
                    cardTopSites(
                        navegarDetalleSitio,
                            "Volcan COtopaxi",
                            "Mejia",
                            5,
                        Icons.Default.Favorite,
                        Icons.Default.FavoriteBorder,
                        imageRes = R.drawable.cotopaxi___mejia
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
        }

    }
}