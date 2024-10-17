package com.api.gesco.gui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.api.gesco.R
import com.api.gesco.model.MyViewModel
import com.api.gesco.service.config.SessionManager.Companion.USER_TOKEN
import java.util.Objects

@Composable
fun HomeScreen(navController: NavController, viewModel: MyViewModel, context: Context){

    val aluno by viewModel.aluno.collectAsState()

    data class Item(
        val image: Painter,
        val name :String,
        val navigation: String
    )
    var categorias = arrayOf(
        Item(image = painterResource(R.drawable.alarm), name = "Horário", navigation = "gridHour"),
        Item(image = painterResource(R.drawable.atividade), name = "Atividades", navigation = "gridHour"),
        Item(image = painterResource(R.drawable.calendar), name = "Frequencia", navigation = "discipline"),
        Item(image = painterResource(R.drawable.events), name = "Eventos", navigation = "events")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFF9F45C), Color(0xFFFFB600)),
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                // Título e saudação
                Column {
                    Text(
                        text = "Olá,",
                        color = colorResource(R.color.azul),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = aluno.nome,
                        modifier = Modifier.padding(start = 10.dp),
                        color = colorResource(R.color.azul),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold

                    )
                }
                Spacer(Modifier.height(10.dp))
            }
        }

        Spacer(Modifier.height(10.dp))
        Text(
            text = "Explore",
            modifier = Modifier.padding(start = 10.dp),
            color = colorResource(R.color.azul),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp),
            horizontalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            items(categorias){
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable {
                            if (it.name == "Horário"){
                                viewModel.fetchGrid(context, aluno.id_turma)
                            }else if(it.name == "Eventos"){
                                viewModel.fetchEvents(context, aluno.id_escola)
                            }else if(it.name == "Frequencia"){
                                viewModel.fetchDisciplines(context, aluno.id)
                            }
                            navController.navigate(it.navigation)
                                   },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFFF9F45C), Color(0xFFFFB600)),
                                ),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = it.image, contentDescription = "",
                            tint = colorResource(R.color.azul)
                        )
                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = it.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = colorResource(R.color.azul)

                        )

                    }

                }
            }
        }
    }
}