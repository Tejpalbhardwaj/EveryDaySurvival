package com.example.everydaysurvival.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.everydaysurvival.PostViewModel
import com.example.everydaysurvival.R
import com.example.everydaysurvival.components.PostCard
import com.example.everydaysurvival.ui.theme.bgColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedPostsScreen(
    navController: NavController,
    postViewModel: PostViewModel,
    modifier: Modifier = Modifier
) {

    val savedPosts = postViewModel.savedPosts.collectAsState()

    Column(modifier = modifier
        .fillMaxSize()
        .background(bgColor)) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            Text(
                text = "Saved Posts",
                fontFamily = FontFamily(Font(R.font.jetbrainsmono_bold)),
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        if (savedPosts.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Saved Posts Yet",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.spacegrotesk_medium)),
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .background(bgColor),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(savedPosts.value) { post ->
                    PostCard(
                        post = post,
                        isSaved = true,
                        onSaveClick = { clickedPost ->
                            // Remove from saved posts when clicking save button again
                            postViewModel.removeSavedPost(clickedPost)
                        }
                    )
                }
            }
        }
    }
}