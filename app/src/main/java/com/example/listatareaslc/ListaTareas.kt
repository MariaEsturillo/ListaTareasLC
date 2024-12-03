package com.example.listatareaslc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListaTareas(modifier : Modifier){
    val context = LocalContext.current

    val tareas = remember {
        mutableStateListOf(
            Tarea(icono = Icons.Default.CheckCircle, titulo = "Tarea 1"),
            Tarea(icono = Icons.Default.Create, titulo = "Tarea 2"),
            Tarea(icono = Icons.Default.CheckCircle, titulo = "Tarea 3"),
            Tarea(icono = Icons.Default.Create, titulo = "Tarea 4"),
        )
    }

    LazyColumn (modifier = Modifier.fillMaxSize(),
        ){
        item{
        Text("Lista de tareas" , fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(20.dp,40.dp,0.dp,30.dp))
        }
            items(tareas){
                ItemTarea(tarea = it) { tareaCompletada ->

                    val newIcon = if (tareaCompletada.icono == Icons.Default.Create) {
                        Icons.Default.CheckCircle
                    } else {
                        Icons.Default.Create
                    }
                    val index = tareas.indexOf(tareaCompletada)
                    if (index != -1) {
                        tareas[index] = tareaCompletada.copy(icono = newIcon)
                    }

                }

            }
    }
}

@Composable
fun ItemTarea(tarea :Tarea, onItemSelected :(Tarea) -> Unit){

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.fillMaxWidth()
            .clickable { onItemSelected(tarea) }
            .padding(10.dp)
    ) { Row(
        horizontalArrangement = Arrangement.spacedBy(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ){
        Icon(
            imageVector = tarea.icono,
            contentDescription = "icono",
            modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp),
           tint = if(tarea.icono.equals(Icons.Default.CheckCircle))
            Color.Green else Color.Red
        )
        Text(tarea.titulo)
        Box{
            BotonCompletarTarea(tarea, onItemSelected)
        }


    } }



}



@Composable
fun BotonCompletarTarea(tarea : Tarea, onItemSelected: (Tarea) -> Unit){
    Button(onClick={ onItemSelected(tarea)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2cb990))
    ){
        Text("Completar")
    }

}