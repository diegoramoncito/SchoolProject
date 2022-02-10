package ec.gob.pichincha.turismopichincha.presentation.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import ec.gob.pichincha.turismopichincha.R
import ec.gob.pichincha.turismopichincha.model.Recomendado
import ec.gob.pichincha.turismopichincha.navigation.Screen
import ec.gob.pichincha.turismopichincha.presentation.components.TabOnlyTextBackWhite
import ec.gob.pichincha.turismopichincha.presentation.components.buttomGlobal
import ec.gob.pichincha.turismopichincha.presentation.utilities.Utils
import ec.gob.pichincha.turismopichincha.ui.theme.GrayBackgroundComponents
import ec.gob.pichincha.turismopichincha.ui.theme.GrayLightText
import ec.gob.pichincha.turismopichincha.viewModel.DestinoViewModel

data class dataWithIcon(
    val painter: Painter,
    val descripcion: String,
)

data class dataLink(
    val description: String,
    val link: String,
    val painter: Painter
)

//@Preview
//@Composable(){
//    ScreenDetalleSitio(idDetalleSitio = 1, navController = )
//}


//@Preview
@Composable
fun ScreenDetalleSitio(
    idDetalleSitio: String,
    navController: NavHostController,
    viewModel: DestinoViewModel,
    idx:Int
) {
    topImage(viewModel.recomendados[idx].fotos.getOrNull(0))
    cardMother(navController,viewModel.recomendados[idx])
    Text(text = idDetalleSitio)

    spacerBottomGlobal()
}

//@Preview
@Composable
private fun topImage(
    imageUrl:String?
) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                error(R.drawable.cotopaxi___mejia)
            }
        ),
        contentDescription = "Imagen atracción",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    )
}

@Composable
private fun cardMother(
    navController: NavHostController,
    recomendado: Recomendado
) {
    Card(
        shape = RoundedCornerShape(topEnd = 55.dp, topStart = 55.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 150.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 20.dp, end = 20.dp)
        ) {
            item {
                //Header
                headerSite(recomendado.titulo,recomendado.subtitulo)

                //estrellas
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    imageStarts(rateNum = recomendado.calificacion)
                }

                //parrafo
                Text(
                    text = recomendado.descripcion,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                //Grupo de iconos
                groupIndicatorIcons()

                //TAB CODIGO
                //Pruebas tab fotos
                tabFotos(recomendado.fotos)
                //tabActividades
                val arte = dataWithIcon(
                    painterResource(id = R.drawable.arte_y_arquitectura),
                    "Arte y arquitectura"
                )
                val gastronomía = dataWithIcon(
                    painterResource(id = R.drawable.gastronomia),
                    "Gastronomía"
                )
                val ciclismo = dataWithIcon(
                    painterResource(id = R.drawable.ciclismo),
                    "Ciclismo"
                )
                val senderismo = dataWithIcon(
                    painterResource(id = R.drawable.senderismo),
                    "Senderismo"
                )
                val alquilerDeBotes = dataWithIcon(
                    painterResource(id = R.drawable.alquiler_de_botes),
                    "Alquiler de botes"
                )
                tabActividades(
                    data = listOf(
                        arte,
                        gastronomía,
                        ciclismo,
                        senderismo,
                        alquilerDeBotes
                    )
                )
                val alojamiento = dataWithIcon(
                    painterResource(id = R.drawable.alojamiento),
                    "Alojamiento"
                )
                val alimentación = dataWithIcon(
                    painterResource(id = R.drawable.gastronomia),
                    "Alimentación"
                )
                val parqueo = dataWithIcon(
                    painterResource(id = R.drawable.parqueo),
                    "Parqueo",
                )
                val bares = dataWithIcon(
                    painterResource(id = R.drawable.bares),
                    "Bares",
                )
                val tripAdvisor = dataLink(
                    "TripAdvisor",
                    "https://www.tripadvisor.com.ar/",
                    painter = painterResource(id = R.drawable.booking)
                )
                val booking = dataLink(
                    "Booking.com",
                    "https://www.booking.com/index.es.html?aid=376374;label=esrow-OtlvhU2CXhSVxek50Z_17wS410489931081:pl:ta:p1:p22.563.000:ac:ap:neg:fi:tikwd-65526620:lp9069516:li:dec:dm:ppccp=UmFuZG9tSVYkc2RlIyh9YcUSe6BbHz0Ad_yDShFFSHQ;ws=&gclid=CjwKCAiA4veMBhAMEiwAU4XRr4IC9ffNt7u5G3tlMqExxZ7PcsjiFHT73BcPjmNCvtdo8ZCyqIK7ERoC3oAQAvD_BwE",
                    painter = painterResource(id = R.drawable.booking)
                )
                val wikiloc = dataLink(
                    "Wikiloc",
                    "https://es.wikiloc.com/",
                    painter = painterResource(id = R.drawable.wikiloc)
                )
                tabServicios(
                    data = listOf(
                        alojamiento,
                        alimentación,
                        parqueo,
                        bares
                    ),
                    listOf(tripAdvisor, booking, wikiloc)
                )
                val telefono = dataWithIcon(
                    painterResource(id = R.drawable.telefono),
                    "0999999999"
                )
                val direccion = dataWithIcon(
                    painterResource(id = R.drawable.mapa_ruta),
                    "Ruta 56 - km 49"
                )
                tabContacto(data = listOf(telefono,direccion))

                spacerBottomGlobal()
            }

        }
    }
}

@Composable
private fun headerSite(title: String, description: String) {
    //title
    subTitle(text = title, verticalPadding = 0)

    //description
    Text(
        text = description,
        textAlign = TextAlign.Center,
        color = GrayLightText
    )
}

@Composable
private fun groupIndicatorIcons() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        subGroupIndicatorIcons(
            painterResource(id = R.drawable.icono_temperatura),
            "20°",
            "Temperatura"
        )
        subGroupIndicatorIcons(
            painterResource(id = R.drawable.ic_icono_dificultad),
            "Baja",
            "Dificultad"
        )
        subGroupIndicatorIcons(
            painterResource(id = R.drawable.icono_presupuesto),
            "20$",
            "Presupuesto"
        )
    }
}

@Composable
fun subGroupIndicatorIcons(painter: Painter, value: String, category: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter,
            contentDescription = "Icon",
            modifier = Modifier.size(Utils.sizeIcon.dp)
        )
        Spacer(modifier = Modifier.width(3.dp))
        Column() {
            Text(text = value)
            Text(
                text = category,
                color = Color.LightGray,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun tabFotos(urlsFotos:List<String>) {
    LazyRow() {
        items(items = urlsFotos) { urlFoto ->
            cardFotos(url = urlFoto)
        }
    }
}

@Composable
fun cardFotos(url: String) {
    Card(
        shape = RoundedCornerShape(Utils.roundedCornerCardsGlobal.dp),
        elevation = Utils.elavationCard.dp,
        modifier = Modifier
            .size(150.dp)
            .padding(top = 20.dp, end = 20.dp)
    ) {
        coilImage(url = url)
    }
}

@Composable
fun coilImage(url: String) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            error(R.drawable.ic_baseline_error_outline_24)
        }
    )
    val state = painter.state
    if (state is ImagePainter.State.Loading) {
        CircularProgressIndicator()
    }

    Image(
        painter = painter,
        contentDescription = "Imagen destino",
        contentScale = ContentScale.Crop
    )
}


@Composable
fun tabActividades(data: List<dataWithIcon>) {
    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        listIconWithDescription(data)
    }
}

@Composable
fun listIconWithDescription(data: List<dataWithIcon>) {
    data.forEach { registro ->
        Row(
            modifier = Modifier.padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = registro.painter,
                contentDescription = registro.descripcion,
                modifier = Modifier.size(Utils.sizeIconSmall.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = registro.descripcion)
        }
    }
}

@Composable
fun tabServicios(data: List<dataWithIcon>, dataOfLinks: List<dataLink>) {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        listIconWithDescription(data)

        //grupo de iconos de aplicaciones externas

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            dataOfLinks.forEach{registro->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = registro.painter,
                        contentDescription = registro.description,
                        modifier = Modifier.size(Utils.sizeIcon.dp)
                    )
                    Text(text = registro.description)
                }
            }
        }
    }
}

@Composable
fun tabContacto(data: List<dataWithIcon>) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        listIconWithDescription(data)

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Envía tus comemtarios",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )

        TextField(
            shape = RoundedCornerShape(25.dp),
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    text = "Escribe tus comentarios",
                    style = TextStyle(color = Color.LightGray)
                )
            },
            singleLine = false,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = GrayBackgroundComponents
            ),
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(0.8f)
                .height(150.dp)
        )

        buttomGlobal(texto = "ENVIAR")
    }

}

//@Preview
@Composable
fun tabactividadesPreview() {
    val arte = dataWithIcon(
        painterResource(id = R.drawable.arte_y_arquitectura),
        "Arte y arquitectura"
    )
    val gastronomía = dataWithIcon(
        painterResource(id = R.drawable.gastronomia),
        "Gastronomía"
    )
    val ciclismo = dataWithIcon(
        painterResource(id = R.drawable.ciclismo),
        "Ciclismo"
    )
    val senderismo = dataWithIcon(
        painterResource(id = R.drawable.senderismo),
        "Senderismo"
    )
    val alquilerDeBotes = dataWithIcon(
        painterResource(id = R.drawable.alquiler_de_botes),
        "Alquiler de botes"
    )
    tabActividades(
        data = listOf(
            arte,
            gastronomía,
            ciclismo,
            senderismo,
            alquilerDeBotes
        )
    )
}

