package com.api.gesco.gui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.api.gesco.R
import com.api.gesco.model.MyViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FrequencyScreen(navController: NavController, viewModel: MyViewModel, context: Context){
    val frequency by viewModel.frequency.collectAsState()
    val frequencyData by viewModel.frequencyData.collectAsState()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var geral by remember { mutableIntStateOf(0) }

    LaunchedEffect(frequencyData) {
        if (frequencyData.total != 0){
            geral = ((frequencyData.presente.toFloat() / frequencyData.total.toFloat()) * 100).toInt()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                        .height(100.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.verde))
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Presenças",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )

                        Text(
                            text = "${frequencyData.presente}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(100.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.vermelho))

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Faltas",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )

                        Text(
                            text = "${frequencyData.ausente}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),

            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.amarelo))

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Frequência Geral",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )

                        Text(
                            text = "$geral%",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(30.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            items(frequency.body){
                var day = LocalDate.parse(it.dia).format(formatter)
                val teacher = it.professor.split(" ")
                val cor = if (it.presenca == "PRESENTE") colorResource(R.color.verde) else colorResource(R.color.vermelho);
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(0),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.amarelo))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = cor),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,

                    ) {
                        Text(
                            text = day.take(5),
                            modifier = Modifier.padding(start = 10.dp),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = if (teacher.size == 2) "${teacher[0]} ${teacher[1]}" else teacher[0],
                            modifier = Modifier.padding(end = 30.dp),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}