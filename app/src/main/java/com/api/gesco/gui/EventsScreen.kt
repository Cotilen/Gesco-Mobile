package com.api.gesco.gui

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.api.gesco.R
import com.api.gesco.model.MyViewModel
import com.api.gesco.service.response.events.EventsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventsScreen(navController: NavController, viewModel: MyViewModel,context: Context) {
    val events by viewModel.events.collectAsState()
    val aluno by viewModel.aluno.collectAsState()

    var selectedMonth by rememberSaveable {
        mutableIntStateOf(LocalDate.now().monthValue)
    }

    LaunchedEffect(Unit) {
        viewModel.fetchEvents(context, aluno.id_escola)
    }

    val year: Long = 12L - YearMonth.now().monthValue
    val lastMonthOfYear = YearMonth.now().plusMonths(year)

// Cria uma lista de datas do mês atual até o último mês do ano
    val dateRange = generateSequence(YearMonth.now()) { it.plusMonths(1) }
        .takeWhile { it <= lastMonthOfYear }
        .map { it.atDay(1) } // Primeiro dia de cada mês
        .toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
    ) {

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ) {
            items(dateRange.size) { index ->

                val monthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale("pt", "BR"))
                val monthName = dateRange[index].format(monthFormatter).capitalize()

                Button(
                    modifier = Modifier
                        .size(135.dp, 40.dp)
                        .padding(start = 4.5.dp),
                    colors = if (dateRange[index].monthValue == selectedMonth) ButtonDefaults.buttonColors(
                        colorResource(R.color.amarelo)
                    ) else ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(20.dp),
                    border = if (dateRange[index].monthValue == selectedMonth) BorderStroke(
                        2.dp,
                        Color.Transparent
                    ) else BorderStroke(2.1.dp, colorResource(R.color.azul)),
                    onClick = {
                        selectedMonth =
                            dateRange[index].monthValue // Atualiza o mês selecionado quando o botão é clicado
                    }
                ) {
                    Text(
                        text = monthName,
                        color = colorResource(R.color.azul),
                        fontWeight = FontWeight(900),
                        fontSize = 14.sp
                    )
                }
            }
        }


        Spacer(Modifier.height(40.dp))

        fun filterConsultsByCurrentMonth(consults: List<EventsResponse>): List<EventsResponse> {
            val currentMonth = LocalDate.now().monthValue
            return consults.filter {
                LocalDate.parse(
                    it.dia
                ).monthValue == selectedMonth
            }
        }

        val filteredEvents = filterConsultsByCurrentMonth(events.body.eventos)

        if (filteredEvents.isNotEmpty()){
            LazyColumn() {
                items(filteredEvents) { evento ->
                    val day = evento.dia.split("-")

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(
                            modifier = Modifier.size(width = 50.dp, height = 50.dp),
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(colorResource(R.color.amarelo)),
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = day[2],
                                    color = colorResource(R.color.azul),
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }
                        Card(
                            modifier = Modifier
                                .size(width = 315.dp, height = 50.dp)
                                .padding(horizontal = 4.dp),
                            colors = CardDefaults.cardColors(Color.White),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(width = 3.dp, colorResource(R.color.azul))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${evento.horario.take(5)}h - ${evento.nome}",
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 16.dp)
                                )
                            }
                        }
                    }


                }
            }
        }else{
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(  brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFF9F45C), Color(0xFFFFB600)),
                    )),
                horizontalArrangement = Arrangement.Center,

                ) {       Text(
                text = stringResource(R.string.noFoundEvents),
                color = colorResource(R.color.azul),
                fontSize = 30.sp,
                fontWeight = FontWeight.Black
            )}
        }

    }
}
