package ec.gob.pichincha.turismopichincha.presentation.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ec.gob.pichincha.turismopichincha.R

@Composable
fun ScreenContacto() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            subTitle(text = "Contáctanos",30)

            Image(
                painter = painterResource(id = R.drawable.logoprefectura),
                contentDescription = "LogoPrefectura",
                modifier = Modifier.padding(vertical = 20.dp)
            )

            //parrafo
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, purus sem consequat. Odio sodales magna aliquet eleifend senectus sem posuere per aenean, nam ad nunc magnis congue dignissim taciti fames luctus, maecenas velit torquent pretium tincidunt dictum vestibulum pellentesque. Mi gravida",
                style = MaterialTheme.typography.body1,
                color = Color.Gray,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 50.dp)
            )

            iconoWithTextGroup()

            Spacer(modifier = Modifier.height(100.dp))

        }
    }
}

@Composable
fun iconoWithTextGroup() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(200.dp)
    ) {
        iconoWithText(icon = Icons.Default.Phone, description = "(593 2) 394 6760")
        iconoWithText(icon = Icons.Default.Email, description = "corre@gmail.com")
        iconoWithText(
            icon = Icons.Default.Place,
            description = "Edificio Gobierno de Pichincha Manuel larrea N13 -45 y Antomia Ante"
        )
        iconoWithText(icon = Icons.Default.MailOutline, description = "dejanos tus comentarios")
    }
}

@Composable
fun iconoWithText(icon: ImageVector, description: String) {
    Row(
        modifier = Modifier.width(250.dp)
    ) {
        Image(imageVector = icon, contentDescription = "description del icono")
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = description)
    }
}