package com.api.gesco.gui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.api.gesco.R
import com.api.gesco.model.MyViewModel
import com.api.gesco.service.response.gridHour.GridHourResponse
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GridHourScreen(navController: NavController, viewModel: MyViewModel, context: Context) {
    val grid by viewModel.grid.collectAsState()
    val semana = listOf(
        "Segunda-feira",
        "Terça-feira",
        "Quarta-feira",
        "Quinta-feira",
        "Sexta-feira"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Para cada dia da semana
        semana.forEach { dia ->
            item {
                var visible by remember { mutableStateOf(false) }
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFFF9F45C), Color(0xFFFFB600)),
                            ),
                        )
                        .height(80.dp)
                        .clickable { visible = !visible }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFFF9F45C), Color(0xFFFFB600)),
                                ),
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = dia,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.azul)
                        )
                    }
                }
                // Visibilidade animada do conteúdo de horários
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(initialAlpha = 0.8f),
                    exit = fadeOut(animationSpec = tween(durationMillis = 50))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFF3B4CCA), Color(0xFF1A2A6C))
                                ),
                            )
                            .padding(20.dp)
                    ) {
                        // Filtra e organiza os horários
                        val horariosFiltrados = grid.body
                            .filter { it.dia == dia }
                            .map { LocalTime.parse(it.hora) }
                            .sorted()

                        horariosFiltrados.forEach { hora ->
                            // Exibe os itens filtrados
                            grid.body.filter { it.hora.substring(0, 5) == hora.toString() && it.dia == dia }
                                .forEach { item ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Text(
                                            text = "$hora",
                                            modifier = Modifier.padding(horizontal = 50.dp),
                                            color = colorResource(R.color.amarelo),
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Black
                                        )

                                        Spacer(Modifier.width(100.dp))

                                        Column {
                                            Text(
                                                text = item.disciplina,
                                                color = colorResource(R.color.amarelo),
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Black
                                            )
                                            Spacer(Modifier.height(5.dp))
                                            Text(
                                                text = item.professor,
                                                color = colorResource(R.color.amarelo),
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Black
                                            )
                                        }
                                    }
                                    Spacer(Modifier.height(5.dp))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(2.dp)
                                            .background(Color.White)
                                    )
                                    Spacer(Modifier.height(5.dp))
                                }
                        }
                    }
                }
            }
        }
    }
}
