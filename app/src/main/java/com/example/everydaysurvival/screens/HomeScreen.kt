package com.example.everydaysurvival.screens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.everydaysurvival.PostViewModel
import com.example.everydaysurvival.R
import com.example.everydaysurvival.components.PostCard
import com.example.everydaysurvival.model.Post
import com.example.everydaysurvival.navigation.BottomNavItem
import com.example.everydaysurvival.ui.theme.bgColor


@Composable
fun HomeScreen(
    navController: NavController,
    postViewModel: PostViewModel,
    modifier: Modifier = Modifier
) {
    val posts = listOf(
        Post(0, R.drawable.coding_ex, "Charge Your Phone 2x Faster With This Hack"),
        Post(1, R.drawable.coding_ex, "This Habit Lowers Stress In Under Just 50 Seconds"),
        Post(2, R.drawable.coding_ex, "You Can Grow Your Money With This Simple Hack"),
        Post(3, R.drawable.coding_ex, "Do This When You Can't Concentrate"),
        Post(4, R.drawable.coding_ex, "Charge Your Phone 2x Faster With This Hack"),
        Post(5, R.drawable.coding_ex, "This Habit Lowers Stress In Under Just 50 Seconds"),
        Post(6, R.drawable.coding_ex, "You Can Grow Your Money With This Simple Hack"),
        Post(7, R.drawable.coding_ex, "Do This When You Can't Concentrate")
    )

    val savedPosts = postViewModel.savedPosts.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .background(bgColor),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "Trending Hacks",
                fontFamily = FontFamily(Font(R.font.jetbrainsmono_bold)),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(posts) { item ->
            val isSaved = savedPosts.value.any { it.id == item.id }
            PostCard(
                post = item,
                isSaved = isSaved,
                onSaveClick = { post ->
                    if (isSaved) {
                        postViewModel.removeSavedPost(post)
                    } else {
                        navController.navigate(BottomNavItem.SavedPosts.route) {
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }

                        }
                        postViewModel.addSavedPost(post)
                    }
                }
            )
        }
    }
}