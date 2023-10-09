package component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

class Input {
    @Composable
    fun Dropdown(
        name: String,
        property: String,
        options: List<String>,
        details: Map<String, String>,
        setDetails: (Map<String, String>) -> Unit
    ){
        var expandedState by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .clickable { expandedState = !expandedState }
                .width(280.dp)
                .wrapContentHeight(Alignment.CenterVertically)
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
            ,
            contentAlignment = Alignment.Center
        ){
            Text(
                text = name,
                modifier = Modifier.align(Alignment.TopStart)
            )

            Text(
                text = details[property]!!,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(top = 24.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .absoluteOffset { IntOffset(-14, 52) }
                ,
                contentAlignment = Alignment.BottomCenter
            ) {
                DropdownMenu(
                    expanded = expandedState,
                    onDismissRequest = { expandedState = false },
                    modifier = Modifier
                        .width(280.dp)
                ) {
                    options.forEach { item ->
                        DropdownMenuItem(
                            onClick = {
                                setDetails(details.plus(property to item))
                                expandedState = false
                            }
                        ) {
                            Text(item)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun IntegerField(
        name: String,
        property: String,
        details: Map<String, String>,
        setDetails: (Map<String, String>) -> Unit
    ) {
        TextField(
            value = details[property]!!,
            onValueChange = { text ->
                if (text.matches(Regex("^[0-9]+$"))) {
                    setDetails(details.plus(property to text))
                }
            },
            label = { Text(name) },
        )
    }

    @Composable
    fun DoubleField(
        name: String,
        property: String,
        details: Map<String, String>,
        setDetails: (Map<String, String>) -> Unit
    ) {
        TextField(
            value = details[property]!!,
            onValueChange = { text ->
                if (text.count { it == '.' } <= 1 && text.matches(Regex("^[^.a-z][0-9]*\\.?[0-9]*\$"))) {
                    setDetails(details.plus(property to text))
                }
            },
            label = { Text(name) },
        )
    }
}