@file:OptIn(ExperimentalGlideComposeApi::class)

package com.jashwant.movieapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.jashwant.movieapplication.models.MovieListItem
import com.jashwant.movieapplication.ui.theme.Itembackground
import com.jashwant.movieapplication.ui.theme.MovieApplicationTheme
import com.jashwant.movieapplication.viewmodels.MainViewModel

var TAG = "Jashwantmovie"
class MainActivity : ComponentActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var apiService: ApiService
    lateinit var repository: MovieRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        apiService= RetrofitBuilder.getapiService()
        repository = MovieRepository(apiService)
        viewModel = ViewModelProvider(this,MainViewModel.Factory(repository)).get(MainViewModel::class.java)

        setContent {
            MovieApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MovieListView(
                        viewModel=viewModel,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun MovieListView(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,

) {
    val movielist by viewModel.movieList.observeAsState()
    var movielist2= emptyList<MovieListItem>()
    if(movielist!=null){
        movielist2= movielist as List<MovieListItem>
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 150.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalItemSpacing = 20.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)

        ) {
        items(movielist2){
            item-> MovieListItemView(item.title, modifier = Modifier,item=item)
        }

    }
}

@Composable
fun MovieListItemView(name: String, modifier: Modifier = Modifier, item: MovieListItem) {
    Card(
        modifier= Modifier
            .padding(3.dp)
            .background(Color.Transparent)
        ,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp,Color.Blue),
        elevation = CardDefaults.cardElevation(1.dp)

    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Itembackground)
        ){
            GlideImage(
                model = item.image_portrait,
                contentDescription = "Test",
                modifier = Modifier
                    .size(90.dp)
                    .padding(2.dp)
            )
            Text(
                text = "${item.title}",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieApplicationTheme {
        //MovieListView("Android", arr)
    }
}