package com.mataecheverry.project_ravelry.ui.pantalles

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mataecheverry.project_ravelry.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaSearch(){

    var query by remember { mutableStateOf("") }
    var active by remember {mutableStateOf(false)}
    var selectedOption by remember { mutableStateOf("") }
    val context = LocalContext.current
    val area = rememberCoroutineScope()


    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(0XFFF3F4F0))
            .padding(top = 24.dp)
    ){
        SearchBar(query = query,
            onQueryChange = {query = it} ,
            onSearch = {
                Toast.makeText(context, query, Toast.LENGTH_SHORT).show() } ,
            active = active,
            onActiveChange = {active = it},
            modifier = Modifier
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp)),
            placeholder = {query = "Search" },
            leadingIcon = { IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menú"
                ) } },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Menú"
                    ) } },
            shape = RectangleShape,
            shadowElevation = 15.dp,


        ) {
            //aquí anirà el contingut de la cerca

        }
        Divider(Modifier.padding(24.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ){
            SearchButton("Knitting", R.drawable.knitting, onClick = {})
            Spacer(Modifier.padding(vertical = 10.dp))
            SearchButton("Crochet", R.drawable.crochet, onClick = {})
            Spacer(Modifier.padding(vertical = 10.dp))
            SearchButton("Knitting + Crochet", R.drawable.sheepcraft, onClick = {})
        }

    }
}

@Composable
fun SearchButton(craft: String, icon: Int, onClick: () -> Unit){
    ElevatedButton(onClick = {  },
        colors = ButtonDefaults.elevatedButtonColors(
            contentColor = Color(0XFF19444D),
            containerColor = Color(0XFFF3F4F0)),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 5.dp, hoveredElevation = 15.dp),
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(15.dp)),
    )
    {
        Text(craft,
            fontSize = 16.sp,
            modifier = Modifier.weight(4f),
            softWrap = false
        )
        Spacer(modifier = Modifier.weight(4f))
        Image(painter = painterResource(id = icon),
            contentDescription = "Icon of the related craft",
            modifier = Modifier
                .size(20.dp)
                .weight(1f)
        )

    }
}