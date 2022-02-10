package ec.gob.pichincha.turismopichincha.presentation.Screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import ec.gob.pichincha.turismopichincha.presentation.components.buttomGlobal
import ec.gob.pichincha.turismopichincha.presentation.utilities.Utils
import ec.gob.pichincha.turismopichincha.ui.theme.GrayBackgroundComponents
import ec.gob.pichincha.turismopichincha.ui.theme.GrayDarkText

@Composable
fun ScreenBusqueda() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Utils.horizontalPaddingGlobal.dp),
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = Utils.vertivalPaddingGlobal.dp))

            textFieldSeccion()

            //Categorías
            subTitle(text = "Categorías", verticalPadding = 10)
            ChipSectionWithIcon(
                chips = listOf("Volcanes", "Cascadas", "Ríos", "Museos"),
                iconChips = listOf(
                    Icons.Default.ShoppingCart,
                    Icons.Default.Build,
                    Icons.Default.Email,
                    Icons.Default.Home
                )
            )

            //Radio de busqueda
            subTitle(text = "Radio de búsqueda", verticalPadding = 10)
            ChipSection(chips = listOf("2km", "10km", "15km", "25km"))

            subTitle(text = "Cantón", verticalPadding = 10)
            dropDownMenu(listOf("Quito", "Cayambe", "Mejía", "Pedro Moncayo", "Puerto Quito", "Pedro Vicente Maldonado"), textLabelIn = "Selecciona el cantón")

            subTitle(text = "Parroquia", verticalPadding = 10)
            dropDownMenu(suggestions = listOf("Quito", "Cayambe", "Mejía", "Pedro Moncayo", "Puerto Quito", "Pedro Vicente Maldonado"), textLabelIn = "Selecciona la parroqui")

            buttomGlobal("Buscar")

            spacerBottomGlobal()
        }
    }
}

@Composable
fun ChipSectionWithIcon(
    chips: List<String>,
    iconChips: List<ImageVector>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow() {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .width(150.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) MaterialTheme.colors.primary
                        else GrayBackgroundComponents
                    )
                    .padding(15.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(imageVector = iconChips[it], contentDescription = "Icono de la categoría")
//                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = chips[it], color = if (selectedChipIndex == it) Color.White
                        else GrayDarkText
                    )
                }
            }
        }
    }
}

@Composable
fun ChipSection(
    chips: List<String>,
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow() {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .width(75.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) MaterialTheme.colors.primary
                        else GrayBackgroundComponents
                    )
                    .padding(15.dp)
            ) {
                Text(
                    text = chips[it], color = if (selectedChipIndex == it) Color.White
                    else GrayDarkText
                )
            }
        }
    }
}


@Composable
fun dropDownMenu(suggestions: List<String>, textLabelIn: String) {

    var expanded by remember { mutableStateOf(false) }
//    val suggestions = listOf("Kotlin", "Java", "Dart", "Python")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(horizontal = 20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text(textLabelIn)},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }

}

