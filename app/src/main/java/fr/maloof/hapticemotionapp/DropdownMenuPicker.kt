package fr.maloof.hapticemotionapp.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun DropdownMenuPicker(
    range: List<Int>,
    selected: Int,
    onValueChange: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(text = selected.toString().padStart(2, '0'))
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            range.forEach { value ->
                DropdownMenuItem(
                    text = { Text(value.toString().padStart(2, '0')) },
                    onClick = {
                        onValueChange(value)
                        expanded = false
                    }
                )
            }
        }
    }
}


