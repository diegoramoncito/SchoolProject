package ec.gob.pichincha.turismopichincha.presentation.Screens


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import ec.gob.pichincha.turismopichincha.R
import ec.gob.pichincha.turismopichincha.navigation.Screen
import ec.gob.pichincha.turismopichincha.presentation.utilities.Utils
import ec.gob.pichincha.turismopichincha.ui.theme.TurismoPichinchaTheme
import ec.gob.pichincha.turismopichincha.ui.theme.GrayBackgroundComponents
import ec.gob.pichincha.turismopichincha.ui.theme.GrayLightText
import ec.gob.pichincha.turismopichincha.viewModel.DestinoViewModel


data class DataCard(
    val title: String,
    val canton: String,
    val painter: Painter,
    val rateNum: Int = 0
)






//val listEvento = mutableListOf<DataCard>()

@ExperimentalCoilApi
@Composable
fun ScreenHome(
    viewModel:DestinoViewModel = hiltViewModel(),
    navegarDetalleSitio: (Int) -> Unit,
) {
    LazyColumn(modifier = Modifier.padding(horizontal = Utils.horizontalPaddingGlobal.dp)) {
        item {
            headerHome(text = "Vive, explora, descubre", text2 = "Pichincha")

            //Sección de TextField de busqueda
            textFieldSeccion()

            //Sección de cards de noticias
            LazyRow() {
                repeat(5) {
                    item {
                        cardNoticias(
                            navegarDetalleSitio,
                            "Turismo Comunitario",
                            painterResource(id = R.drawable.monumentomitaddelmundo)
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            //Seccion de tarjeta de eventos
            subTitle(text = "EVENTOS", 10)
            LazyRow() {
                items(viewModel.evantos) { evanto ->
                    // Todo: Here you can use this evanto to show data accordingly
                    cardEventos(
                        navegarDetalleSitio,
                        data = DataCard(
                            "Pichincha al aire libre",
                            "Mejia",
                            painterResource(id = R.drawable.parque_manantial_de_los_volcanes),
                            2
                        )
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            //Sección lugares recomenados
            subTitle(text = "LUGARES RECOMENDADOS", 10)
            LazyRow() {
                    itemsIndexed(viewModel.recomendados) { idx,recomendado ->
                        cardTopSites(
                            navegarDetalleSitio = {
                                              navegarDetalleSitio(idx)
                            },
                            title = recomendado.titulo,
                            subtitle = recomendado.subtitulo,
                            imageUrl = recomendado.fotos.getOrNull(0) ?: "",
                            rateNum = recomendado.calificacion,
                            iconSelected = Icons.Default.Favorite,
                            iconUnSelected = Icons.Default.FavoriteBorder,
                            imageRes = R.drawable.cotopaxi___mejia
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                    }

            }
            Spacer(modifier = Modifier.height(10.dp))

            //Sección últimas búsquedas
            subTitle(text = "ÚLTIMAS BUSQUEDAS", 10)
            LazyRow() {
                    items(viewModel.buscados) { buscado->
                        cardLastSearch(
                                buscado.titulo,
                                buscado.subtitulo,
                                buscado.fotos.getOrNull(0),
                                R.drawable.laguna_de_san_marcos
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                    }


            }

            spacerBottomGlobal()
        }
    }
}

@Composable
private fun headerHome(text: String, text2: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logoonlyprefectura),
            contentDescription = "Logo prefectura de Pichincha",
            modifier = Modifier.size(45.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = text,
        )
        Text(
            text = text2,
            color = MaterialTheme.colors.secondary,
        )
    }
}

@Composable
fun textFieldSeccion() {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            shape = RoundedCornerShape(10.dp),
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    text = "¿A donde vas?",
                    style = TextStyle(color = Color.LightGray)
                )
            },
            singleLine = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "TextField"
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = GrayBackgroundComponents
            ),
            modifier = Modifier.padding(vertical = 10.dp)
        )

//        Image(
//            imageVector = Icons.Default.AccountBox,
//            contentDescription = "Icono",
//            modifier = Modifier.size(50.dp)
//        )
    }
}

@Composable
fun subTitle(text: String, verticalPadding: Int) {

    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primaryVariant,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(vertical = verticalPadding.dp)

    )
}

@Composable
private fun cardNoticias(
    navegarDetalleSitio: (Int) -> Unit,
    data: String,
    painter: Painter
) {
    Card(
        shape = RoundedCornerShape(Utils.roundedCornerCardsGlobal.dp),
        elevation = Utils.elavationCard.dp,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .width(290.dp)
            .height(215.dp)
            .clickable(
                enabled = true,
                onClickLabel = "Detalle",
                onClick = { navegarDetalleSitio(0) }
            )
    ) {
        Image(
            painter = painter,
            contentDescription = "Imagen atracción",
            contentScale = ContentScale.Crop,
        )

        //Gradiente en la imagen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            //se define el color desde arriba a abajo
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                )
        ) {

        }

        Text(
            text = data,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .width(300.dp)
                .padding(top = 180.dp),
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
private fun cardEventos(navegarDetalleSitio: (Int) -> Unit, data: DataCard) {
    var checkFavoriteIcon by remember {
        mutableStateOf(false)
    }

    //Logica funcion para presentar estrellas

    Card(
        shape = RoundedCornerShape(Utils.roundedCornerCardsGlobal.dp),
        elevation = Utils.elavationCard.dp,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .width(250.dp)
            .clickable(
                enabled = true,
                onClickLabel = "Detalle",
                onClick = { navegarDetalleSitio(0) }
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(250.dp)
                    .height(185.dp)
            ) {
                Image(
                    painter = data.painter,
                    contentDescription = "Imagen atracción",
                    contentScale = ContentScale.Crop
                )
                Icon(
                    imageVector = if (checkFavoriteIcon) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Icono de favorito",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clcik favorito",
                            onClick = { checkFavoriteIcon = !checkFavoriteIcon }
                        )
                        .align(Alignment.TopEnd)
                        .padding(end = 20.dp, top = 20.dp)
                )
            }

            Text(
                text = data.title,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 5.dp),
            )
            imageStarts(rateNum = data.rateNum)
        }
    }

}

@ExperimentalCoilApi
@Composable
fun cardTopSites(
    navegarDetalleSitio: () -> Unit,
    title:String,
    subtitle:String,
    rateNum: Int,
    iconSelected: ImageVector,
    iconUnSelected: ImageVector,
    imageUrl:String?=null,
    @DrawableRes imageRes: Int?=null
) {
    var checkFavoriteIcon by remember {
        mutableStateOf(false)
    }

    Card(
        shape = RoundedCornerShape(Utils.roundedCornerCardsGlobal.dp),
        elevation = Utils.elavationCard.dp,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .width(250.dp)
            .clickable(
                enabled = true,
                onClickLabel = "Detalle",
                onClick = { navegarDetalleSitio() }
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(250.dp)
                    .height(185.dp)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = imageUrl,
                        builder = {
                            imageRes?.let {
                                error(it)
                            }
                        }
                    ),
                    contentDescription = "Imagen atracción",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Icon(
                    imageVector = if (checkFavoriteIcon) iconSelected else iconUnSelected,
                    contentDescription = "Icono de favorito",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clcik favorito",
                            onClick = { checkFavoriteIcon = !checkFavoriteIcon }
                        )
                        .align(Alignment.TopEnd)
                        .padding(end = 20.dp, top = 20.dp)
                )
            }

            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 5.dp),
            )
            Text(
                text = subtitle,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                color = GrayLightText
            )

            imageStarts(rateNum = rateNum)
        }
    }
}

@Composable
fun imageStarts(rateNum: Int) {
    Image(
        painter = if (rateNum == 1) painterResource(id = R.drawable.unastarsicons) else if (rateNum == 2) painterResource(
            id = R.drawable.dosstarsicons
        ) else if (rateNum == 3) painterResource(id = R.drawable.tresstarsicons) else if (rateNum == 4) painterResource(
            id = R.drawable.cuatrostarsicons
        ) else if (rateNum == 5) painterResource(id = R.drawable.__stars_icons) else painterResource(
            id = R.drawable.cerostarsicons
        ),
        contentDescription = "Ranking",
        modifier = Modifier
            .width(100.dp)
            .padding(bottom = 10.dp)
    )
}

@Composable
private fun cardLastSearch(
    title:String,
    canton:String,
    imageUrl:String? = null,
    @DrawableRes imageRes:Int?=null
) {
    var checkFavoriteIcon by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(
                vertical = 10.dp
            )
    ) {
        Card(
            shape = RoundedCornerShape(Utils.roundedCornerCardsSmall.dp),
            elevation = Utils.elavationCard.dp,
        ) {
            Box(
                modifier = Modifier
                    .width(125.dp)
                    .height(210.dp)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = imageUrl,
                        builder = {
                            imageRes?.let {
                                error(it)
                            }
                        }
                    ),
                    contentDescription = "Imagen atracción",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                    )
                Icon(
                    imageVector = if (checkFavoriteIcon) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Icono de favorito",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clcik favorito",
                            onClick = { checkFavoriteIcon = !checkFavoriteIcon }
                        )
                        .align(Alignment.TopEnd)
                        .padding(20.dp)
                )
            }
        }

        Text(
            text = title,
            modifier = Modifier
                .width(125.dp)
                .padding(horizontal = 20.dp, vertical = 5.dp),
            textAlign = TextAlign.Center,
        )
        Text(
            text = canton,
            modifier = Modifier
                .width(125.dp)
                .padding(horizontal = 20.dp, vertical = 5.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun spacerBottomGlobal() {
    Spacer(modifier = Modifier.height(90.dp))
}
