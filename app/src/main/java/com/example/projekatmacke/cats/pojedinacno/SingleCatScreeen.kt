package com.example.projekatmacke.cats.pojedinacno

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.SubcomposeAsyncImage


fun NavGraphBuilder.oneCat(
    route: String,
    arguments: List<NamedNavArgument>,
    onClose: () -> Unit,
) = composable(
    route = route,
    arguments = arguments,
){
    val catId = it.arguments?.getString("catId")
        ?: throw IllegalArgumentException("catId required")

    val oneCatViewModel = viewModel<OneCatViewModel>(
        factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return OneCatViewModel(catId = catId) as T
            }
        }
    )
    val state = oneCatViewModel.state.collectAsState()
    OneCatListScreen(
        state = state.value,
        onClose = onClose,
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneCatListScreen(
    state: OneCatContract.OneCatState,
    onClose: () -> Unit,
) {
    val context = LocalContext.current
    Scaffold (
       topBar = {
           MediumTopAppBar(
               title = {
                   state.cat?.name?.let {
                       Text(
                           text = it,
                           fontSize = 32.sp,
                           fontWeight = FontWeight.Bold,
                           textAlign = TextAlign.Center,
                           style = TextStyle(color = Color.DarkGray),
                           modifier = Modifier
                               .padding(vertical = 16.dp)
                               .fillMaxWidth()
                       )
                   }
               },
              navigationIcon = {
                  AppIconButton(
                      imageVector = Icons.Default.ArrowBack,
                      onClick = onClose,
                  )

              },
               colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = Color.White
               ),
           )
        },

        content = {paddingValues ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.LightGray)
                .verticalScroll(rememberScrollState())
            ) {

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        SubcomposeAsyncImage(
                            modifier =Modifier
                                .fillMaxWidth(),
                            model = state.imageUrl,
                            contentDescription = null)
                        state.cat?.let { cat ->
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("[Naziv]: ")
                                }
                                append(cat.name)

                            },
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(10.dp) )

                        if(cat.alt_names != null){
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("[Alternativni naziv]: ")
                                    }
                                    append(cat.alt_names)

                                },
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Spacer(modifier = Modifier.height(10.dp) )
                        }

                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("[Opis]: ")
                                }
                                append(cat.description)

                            },
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(10.dp) )

                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("[Zamlja porekla]: ")
                                }
                                append(cat.origin)

                            },
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(10.dp) )

                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("[Temperament]: ")
                                }
                                append(cat.temperament)

                            },
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(10.dp) )

                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("[Zivotni vek]: ")
                                }
                                append(cat.life_span + " godina.")

                            },
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(10.dp) )

                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("[Retkost]: ")
                                }
                                if(cat.rare == 1)
                                    append("da")
                                else
                                    append("ne")
                            },
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(10.dp) )

                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("[Tezina]: ")
                                }
                                append(cat.weight.metric + " kila.")
                            },
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(10.dp) )
                        Text(
                            text = "[Adaptibilnost]",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp) )
                        ProgresBar(initialProgress = cat.adaptability.toFloat())
                        Spacer(modifier = Modifier.height(10.dp) )
                        Text(
                            text = "[Socialni sa drugim zivotinjama]",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp) )
                        ProgresBar(initialProgress = cat.dog_friendly.toFloat())

                        Spacer(modifier = Modifier.height(10.dp) )
                        Text(
                            text = "[Inteligencija]",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp) )
                        ProgresBar(initialProgress = cat.intelligence.toFloat())

                        Spacer(modifier = Modifier.height(10.dp) )
                        Text(
                            text = "[Energija]",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp) )
                        ProgresBar(initialProgress = cat.energy_level.toFloat())

                        Spacer(modifier = Modifier.height(10.dp) )
                        Text(
                            text = "[Vole strance]",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp) )
                        ProgresBar(initialProgress = cat.stranger_friendly.toFloat())


                        Spacer(modifier = Modifier.height(10.dp) )
                            Button(
                                onClick = {
                                    val url = state.cat.wikipedia_url
                                    url?.let {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                        context.startActivity(intent)
                                    }
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Open Wikipedia")
                            }
                    }
                }
            }
        }

    )
}

@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint,
        )
    }
}


@Composable
fun ProgresBar(initialProgress: Float) {
    var currentProgress by remember { mutableStateOf(initialProgress * 0.2F) }

    currentProgress = currentProgress.coerceIn(0f, 5f)

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

            LinearProgressIndicator(
                progress =  currentProgress ,
                modifier = Modifier.fillMaxHeight(0.2f).fillMaxWidth(0.8f),
            )

    }
}

