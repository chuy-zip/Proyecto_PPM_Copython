package com.example.copython.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.copython.ui.theme.ui.theme.Blue10


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseExampleLayout(navController: NavController, courseToken: String?){
    val coursesList = arrayListOf(
        "https://sonrietuexistes555.wixsite.com/copythonapp",
        "https://sonrietuexistes555.wixsite.com/copythonapp/curso-2",
        "https://sonrietuexistes555.wixsite.com/copythonapp/curso-3",
        "https://sonrietuexistes555.wixsite.com/copythonapp/curso-4",
        "https://sonrietuexistes555.wixsite.com/copythonapp/curso-5",
        "https://sonrietuexistes555.wixsite.com/copythonapp/curso-6",
        "https://sonrietuexistes555.wixsite.com/copythonapp/curso-7"
    )
    var courseTk: Int? = Integer.parseInt(courseToken)

    if (courseTk != null) {
        if (courseTk>=coursesList.size){
            courseTk = 0
        }
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Blue10,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        "Hello World",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Localized description",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(2.dp)
                                .size(60.dp)

                        )
                    }
                },

            )
        }

    ){ innerPadding ->
        WebContent(coursesList[courseTk!!],innerPadding)
    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        ScrollableTextsColumn()
//    }

}

@Composable
fun WebContent(urlWeb: String, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ){}
    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(urlWeb)
        }
    }, update = {
        it.loadUrl(urlWeb)
    })
}