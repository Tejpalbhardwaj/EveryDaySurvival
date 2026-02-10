package com.example.everydaysurvival.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.everydaysurvival.R
import com.example.everydaysurvival.ui.theme.bgColor

@Preview(showBackground = true)
@Composable
fun CateGoriesScreen() {
    var query by remember {
        mutableStateOf("")
    }
    val category = listOf<String>("Coding Hacks", "Tech Hacks", "Fitness Hack", "Finance Hack")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(10.dp)
    ) {
        TextField(
            value = query,
            onValueChange = { it ->
                query = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            enabled = true,
            placeholder = { Text("Search Any Hacks") },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search_normal),
                    contentDescription = "Search Icon"
                )
            },
        )
        LazyColumn(modifier = Modifier
            .fillMaxSize()
        ) {
            items(category) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(top=12.dp)
                        .clip(RoundedCornerShape(13.dp))
                ) {
                    Image(
                        painter = painterResource(R.drawable.coding_ex),
                        contentDescription = "coding",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                    ) {

                        Text(
                            text = it,
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.TopStart)
                        )
                    }
                }
            }

        }
    }
}


