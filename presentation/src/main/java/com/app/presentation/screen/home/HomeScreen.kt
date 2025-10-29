package com.app.presentation.screen.home

import android.graphics.Color
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.design_system.theme.Theme
import com.app.presentation.R
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsState().value
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Theme.colors.background.defaultScreen)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(R.drawable.horizental_logo), "")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable {
                            // Go to notification screen
                        }
                ) {
                    Image(
                        painter = painterResource(R.drawable.notification_wisal),
                        contentDescription = "Notifications",
                        modifier = Modifier.size(24.dp)
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 4.dp, y = (-4).dp)
                            .size(16.dp)
                            .background(Theme.colors.stroke.error, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "3", color = Theme.colors.additional.white,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                MenuWithDropdown()
            }
        }
        Text(
            text = stringResource(R.string.posts),
            style = Theme.textStyle.bold.copy(fontSize = 20.sp),
            color = Theme.colors.shade.primary,
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp),
        )
        PostList(state.posts)
    }
    AnimatedVisibility(state.isLoading) {
        Dialog(
            onDismissRequest = { },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        Theme.colors.additional.white,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                CircularProgressIndicator(color = Theme.colors.button.primary)
            }
        }
    }
}

@Composable
fun PostList(posts: List<PostUiModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(items = posts) { post ->
            PostCard(post = post)
        }
    }
}


@Composable
fun MenuWithDropdown() {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.wrapContentSize(Alignment.TopStart)
    ) {
        // Menu icon
        Image(
            painter = painterResource(R.drawable.menu),
            contentDescription = "Menu",
            modifier = Modifier
                .clickable { expanded = true }
        )

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.profile)) },
                onClick = {
                    expanded = false
                    // Handle Profile click
                }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.logout)) },
                onClick = {
                    expanded = false
                    // Handle Logout click
                }
            )
        }
    }
}


@Preview()
@Composable()
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}