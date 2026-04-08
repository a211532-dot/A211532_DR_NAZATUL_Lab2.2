package com.example.a211532_dr_nazatul_lab22

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val DarkBackground = Color(0xFF121E14)
val CardBackground = Color(0xFF1E2E22)
val AccentGreen = Color(0xFF4CAF50)
val TextGray = Color(0xFFAAAAAA)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreenLayout()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenLayout() {


    var searchQuery by remember { mutableStateOf("") }

    var displayedDestination by remember { mutableStateOf("Home") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {


        // HEADER

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2E4F35))
                .padding(top = 40.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = Color.White
                    )

                    Text(
                        text = "GoSMART",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )

                    // Spacer
                    Box(modifier = Modifier.width(24.dp))
                }

                // textfield & button

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // textfield
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Where do you want to go?", color = Color.Gray) },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(


                        )
                    )

                    // butto  n
                    Button(
                        onClick = {

                            if (searchQuery.isNotBlank()) {
                                displayedDestination = searchQuery
                            }
                        },
                        modifier = Modifier.height(56.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = AccentGreen),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }

        // BODY

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
        ) {

            // --- DYNAMIC DESTINATION CARD ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardBackground)
                    .padding(16.dp)
            ) {
                Column {
                    Text(text = "Searched Place", color = TextGray, fontSize = 12.sp)

                    // ASSIGNMENT REQUIREMENT: Dynamic UI Update
                    // This Text uses the 'displayedDestination' variable instead of a hardcoded string
                    Text(
                        text = displayedDestination,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    // The time info was successfully removed per your request!
                }
            }

            // dots
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = ". . .", color = TextGray, fontSize = 16.sp)
            }

            // FAVOURITES
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Favourites", color = TextGray, fontSize = 14.sp)
                Text(text = "Add", color = AccentGreen, fontSize = 14.sp)
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Location",
                    tint = AccentGreen
                )
                Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                    Text(text = "Wawasan LRT Station", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = TextGray
                )
            }
        }


        // NAVIGATION BAR

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackground)
                .padding(top = 12.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            NavigationItem(
                imageVector = Icons.Default.List,
                label = "Directions",
                tint = AccentGreen
            )

            NavigationItem(
                imageVector = Icons.Default.LocationOn,
                label = "Stations",
                tint = TextGray
            )

            NavigationItem(
                imageVector = Icons.Default.List,
                label = "Lines",
                tint = TextGray
            )
        }
    }
}

@Composable
fun NavigationItem(imageVector: androidx.compose.ui.graphics.vector.ImageVector, label: String, tint: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = imageVector,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
        Text(text = label, color = tint, fontSize = 12.sp)
    }
}