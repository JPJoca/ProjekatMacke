package com.example.projekatmacke.cats.lista

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.cats(
    route: String,
    onUserClick: (String) -> Unit,
) = composable(
    route = route
){
    val catListViewModel = viewModel<CatListViewModel>()

    val state = catListViewModel.state.collectAsState()
    CatListScreen(
        state = state.value,
        eventPublisher = {catListViewModel.setEvent(it)},
        onUserClick = onUserClick,
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(
    state: CatListContract.CatListState,
    eventPublisher: (uiEvent: CatListContract.CatListUiEvent) -> Unit,
    onUserClick: (String) -> Unit,
){

    var text by remember { mutableStateOf(TextFieldValue(""))}
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) { // Dodatni red za tekst bar
                Text(
                    text = "Welcome to Cats App",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = TextStyle(color = Color.DarkGray),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = text,
                        onValueChange = { novaVrednost ->
                            text = novaVrednost
                            eventPublisher(
                                CatListContract.CatListUiEvent.SearchQueryChanged(
                                    novaVrednost.text
                                )
                            )
                        },
                        label = { Text(text = "Search") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                    )

                    IconButton(
                        onClick = {
                            text = TextFieldValue("")
                            eventPublisher(
                            CatListContract.CatListUiEvent.SearchQueryChanged(
                               "")) },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear text"
                        )
                    }

                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        },
        content = { paddingValues ->
            Spacer(modifier = Modifier.height(33.dp))
            if (state.loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = paddingValues,
                ) {

                        items(
                            items = if(!state.isSearchMode)state.cats else state.filtredCats,
                            key = { it.id }
                        )
                    { cat ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .padding(bottom = 16.dp)
                                .clickable {  onUserClick(cat.id)   },
                        ) {
                            Column() {
                                Text(
                                    text = cat.name,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                if (cat.alt_names != null) {
                                    Text(

                                        text = cat.alt_names,
                                        color = Color.DarkGray,
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 20.sp
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                                Text(
//
                                    text = cat.description + "\n" ,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontSize = 16.sp
                                )
                                Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {

                                    AssistChip(
                                        onClick = { },
                                        label = { Text(cat.temperament[0]) },
                                        leadingIcon = {

                                        }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    AssistChip(
                                        onClick = {  },
                                        label = { Text(cat.temperament[1]) },
                                        leadingIcon = {

                                        }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))

                                    AssistChip(
                                        onClick = {  },
                                        label = { Text(cat.temperament[2]) },
                                        leadingIcon = {

                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

