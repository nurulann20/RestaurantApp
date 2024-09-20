package com.example.restaurantapp

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantapp.ui.theme.RestaurantAPpTheme

@Composable
fun RestaurantsScreen(){
    val viewModel: RestaurantViewModel = viewModel()
//    val state: MutableState<List<Restaurant>> =
//        remember {
//            mutableStateOf(viewModel.getRestaurants())
//        }
    Column(){
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
            Text(text = "Nurul Annisa Murnastiti")
            Text(text = "235150209111008")
        }
        LazyColumn(contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)) {
            items(viewModel.state.value){
                    restaurant -> RestaurantItem(restaurant) { id ->
                        viewModel.toggleFavorite(id)
                        //val restaurants = state.value.toMutableList()
                        //val itemIndex = restaurants.indexOfFirst { it.id == id }
                        //val item = restaurants[itemIndex]
                        //restaurants[itemIndex] =
                            //item.copy(isFavorite = !item.isFavorite)
                        //state.value = restaurants
            }
            }
        }
    }

//    Column(Modifier.verticalScroll(rememberScrollState())) {
//        dummyRestaurants.forEach{restaurant -> RestaurantItem(restaurant)}
//    }
}

@Composable
fun RestaurantItem(item: Restaurant, onClick: (id: Int) -> Unit) {
    val favoriteState = remember {
        mutableStateOf(false)    }
    val icon = if (favoriteState.value)
        Icons.Filled.Favorite
    else
        Icons.Filled.FavoriteBorder
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            RestaurantIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            RestaurantDetails(item.title,
                item.description,Modifier.weight(0.85f))
            RestaurantIcon(icon, Modifier.weight(0.15f)) {
                favoriteState.value =
                    !favoriteState.value
                onClick(item.id)
            }
        }
    }
}

@Composable
private fun RestaurantIcon(icon: ImageVector, modifier: Modifier, onClick: () -> Unit = {}) {
    Image(imageVector = icon, contentDescription = "Restaurant Icon", modifier = modifier
        .padding(8.dp)
        .clickable { onClick() })
}

@Composable
private fun RestaurantDetails(title: String, description: String, modifier: Modifier){
    Column(modifier = modifier){
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    RestaurantAPpTheme {
        RestaurantsScreen()
    }
}

@Composable
private fun FavoriteIcon(icon: ImageVector, modifier: Modifier, onClick: () -> Unit) {
    val favoriteState = remember {
        mutableStateOf(false) }
    val icon = if (favoriteState.value)
        Icons.Filled.Favorite
    else
        Icons.Filled.FavoriteBorder
    Image(
        imageVector = icon,
        contentDescription = "Favorite restaurant icon",
        modifier = modifier
            .padding(8.dp)
            .clickable { favoriteState.value = !favoriteState.value })
}