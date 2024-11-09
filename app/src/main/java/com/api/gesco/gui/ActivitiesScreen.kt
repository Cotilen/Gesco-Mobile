package com.api.gesco.gui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun ActivitiesScreen(navController: NavController, viewModel: MyViewModel, context: Context){
    val aluno by viewModel.aluno.collectAsState()
    val activities by viewModel.activities.collectAsState()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    LaunchedEffect(Unit) {
        viewModel.fetchActivities(context, aluno.id_turma)
    }

    Column(
        modifier = Modifier.fillMaxSize().background(colorResource(R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (activities.body.isNotEmpty()){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                items(activities.body){
                    var day = LocalDate.parse(it.data_atividade).format(formatter)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(20),
                        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.amarelo))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 20.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,

                                    ) {
                                    Text(
                                        text ="Professor: " + it.professor,
                                        modifier = Modifier.padding(start = 10.dp),
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,

                                    ) {
                                    Text(
                                        text = "Tipo: "+ it.tipo,
                                        modifier = Modifier.padding(start = 10.dp),
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,

                                    ) {
                                    Text(
                                        text = "Data: $day",
                                        modifier = Modifier.padding(start = 10.dp),
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,

                                    ) {
                                    Text(
                                        text = "Atividade: "+ it.nome,
                                        modifier = Modifier.padding(start = 10.dp),
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.arrow),
                                    contentDescription = ""
                                )
                            }
                        }

                    }
                    Spacer(Modifier.height(5.dp))
                }
            }
        }
            else{
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(  brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFF9F45C), Color(0xFFFFB600)),
                        )),
                    horizontalArrangement = Arrangement.Center,

                ) {       Text(
                    text = stringResource(R.string.noFoundAct),
                    color = colorResource(R.color.azul),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black
                )}
            }
    }
}