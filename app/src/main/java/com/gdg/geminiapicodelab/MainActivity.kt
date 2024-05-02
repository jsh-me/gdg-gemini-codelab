package com.gdg.geminiapicodelab

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Precision
import com.gdg.geminiapicodelab.ui.theme.GeminicodelabTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminicodelabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: PhotoReasoningViewModel = viewModel(factory = GenerativeViewModelFactory)
                    val photoReasoningUiState by viewModel.uiState.collectAsState()

                    val coroutineScope = rememberCoroutineScope()
                    val imageRequestBuilder = ImageRequest.Builder(LocalContext.current)
                    val imageLoader = ImageLoader.Builder(LocalContext.current).build()


                    PhotoReasoningScreen(
                        uiState = photoReasoningUiState,
                        onReasonClicked = { inputText, selectedItems ->
                            coroutineScope.launch {
                                val bitmaps = selectedItems.mapNotNull {
                                    val imageRequest = imageRequestBuilder
                                        .data(it)
                                        // Scale the image down to 768px for faster uploads
                                        .size(size = 768)
                                        .precision(Precision.EXACT)
                                        .build()
                                    try {
                                        val result = imageLoader.execute(imageRequest)
                                        if (result is SuccessResult) {
                                            return@mapNotNull (result.drawable as BitmapDrawable).bitmap
                                        } else {
                                            return@mapNotNull null
                                        }
                                    } catch (e: Exception) {
                                        return@mapNotNull null
                                    }
                                }
                                viewModel.reason(inputText, bitmaps)
                            }
                        }
                    )
                }
            }
        }
    }
}