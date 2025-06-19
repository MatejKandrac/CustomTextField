package com.kandrac.matej.customtextfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kandrac.matej.customtextfield.textfield.PasswordInput

// TODO:
// Check inner padding for input field
// Implement styles for password view

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDemoContent()
        }
    }
}

@Composable
fun AppDemoContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(SpacingSmall)
        ) {
            var text by remember { mutableStateOf(TextFieldValue("")) }
            val isPasswordCorrect by remember { mutableStateOf(false) }

            PasswordInput(
                value = text,
                onValueChange = { text = it },
                onValidChanged = {}
            )
            Button(
                onClick = {},
                enabled = isPasswordCorrect,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(TextInputRadius),
            ) {
                Text(stringResource(R.string.continue_text))
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppContentPreview() {
    AppDemoContent()
}