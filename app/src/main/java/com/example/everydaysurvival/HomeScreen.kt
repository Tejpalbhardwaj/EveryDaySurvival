package com.example.everydaysurvival

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.everydaysurvival.components.PostCard
import com.example.everydaysurvival.model.Post
import com.example.everydaysurvival.ui.theme.bgColor

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun HomeScreen(){
    val posts = listOf(
        Post(R.drawable.coding_ex, "Charge Your Phone 2x Faster With This Hack"),
        Post(R.drawable.coding_ex, "This Habit Lowers Stress In Under Just 50 Seconds"),
        Post(R.drawable.coding_ex, "You Can Grow Your Money With This Simple Hack"),
        Post(R.drawable.coding_ex, "Do This When You Can't Concentrate"),
        Post(R.drawable.coding_ex, "Charge Your Phone 2x Faster With This Hack"),
        Post(R.drawable.coding_ex, "This Habit Lowers Stress In Under Just 50 Seconds"),
        Post(R.drawable.coding_ex, "You Can Grow Your Money With This Simple Hack"),
        Post(R.drawable.coding_ex, "Do This When You Can't Concentrate")
    )
    
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize().statusBarsPadding()
            .background(bgColor),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "Trending Hacks",
                fontFamily = FontFamily(Font(R.font.jetbrainsmono_bold)),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(posts) { item ->
            PostCard(item)
        }
    }
}