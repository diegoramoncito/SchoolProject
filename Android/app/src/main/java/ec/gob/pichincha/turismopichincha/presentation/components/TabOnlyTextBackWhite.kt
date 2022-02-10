package ec.gob.pichincha.turismopichincha.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ec.gob.pichincha.turismopichincha.navigation.Screen
import ec.gob.pichincha.turismopichincha.ui.theme.BlackText
import ec.gob.pichincha.turismopichincha.ui.theme.GrayDarkText


@Composable
fun TabOnlyTextBackWhite(screens: List<Screen>, navController: NavHostController) {
    var state by remember { mutableStateOf(0) }
    Column {
        ScrollableTabRow(
            selectedTabIndex = state,
            modifier = Modifier.background(Color.White).padding(top = 20.dp),
            backgroundColor = Color.Transparent,
        ) {
            screens.forEachIndexed { index, screen ->
                Tab(
                    text = { Text(screen.title) },
                    selected = state == index,
                    onClick = {
                        state = index
                        navController.navigate(screen.Route)
                    },
                    modifier = Modifier.background(Color.White),
                    unselectedContentColor = GrayDarkText,
                    selectedContentColor = BlackText,
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Scrolling text tab ${state + 1} selected",
            style = MaterialTheme.typography.body1
        )
    }
}
