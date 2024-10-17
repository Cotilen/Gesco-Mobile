package com.api.gesco.gui

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.api.gesco.R
import com.api.gesco.model.MyViewModel

@Composable
fun DisciplineScreen(navController: NavController, viewModel: MyViewModel, context: Context){
    val discipline by viewModel.discipline.collectAsState()
    val aluno by viewModel.aluno.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .background(colorResource(R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn() {
            items(discipline.body){
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(20.dp)
                        .clickable {
                            viewModel.fetchFrequency(context, aluno.id, it.id)
                            navController.navigate("frequency")
                        },
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(2.dp, color = colorResource(R.color.azul))
                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = it.nome,
                            color = colorResource(R.color.azul),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                }
            }
        }

    }
}