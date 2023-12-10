package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                LemonadeApp()
            }
        }
    }
}


@Composable
fun LemonadeSqueeze(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    var currentTouchCount = 1
    var touchCount = 0
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when(step) {
            1 -> {
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = { step++ }) {
                        Image(
                            painter = painterResource(id = R.drawable.lemon_tree),
                            "Lemon tree"
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.lemon_tree_description),
                        fontSize = 18.sp
                    )
                }
            }
            2 -> {
                touchCount = (2..4).random()
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        if (currentTouchCount != touchCount) {
                            currentTouchCount++
                        }
                        else {
                            currentTouchCount = 1
                            step++
                        }
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.lemon_squeeze),
                            contentDescription = stringResource(id = R.string.lemon_description)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.tap_instruction_for_squeeze),
                        fontSize = 18.sp
                    )
                }
            }
            3 -> {
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        step++
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.lemon_drink),
                            contentDescription = stringResource(id = R.string.glass_of_lemonade_description)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.tap_instruction_for_lemonade),
                        fontSize = 18.sp
                    )
                }
            }
            else -> {
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        step = 1
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.lemon_restart),
                            contentDescription = stringResource(id = R.string.empty_glass_description)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.tap_instruction_for_empty_glass),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        LemonadeSqueeze(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    }
}