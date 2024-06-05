package com.example.hw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hw2.ui.theme.HW2Theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hw2.Affirmation



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HW2Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "affirmationList") {
                    composable("affirmationList") {
                        AffirmationsApp(navController = navController)
                    }
                    composable(
                        route = "otherActivity/{stringResourceId}/{stringResourceIdSecond}/{imageResourceId}",
                        arguments = listOf(
                            navArgument("stringResourceId") { type = NavType.IntType },
                            navArgument("stringResourceIdSecond") { type = NavType.IntType },
                            navArgument("imageResourceId") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val affirmationStringResourceId = backStackEntry.arguments?.getInt("stringResourceId") ?: 0
                        val affirmationStringResourceIdSecond = backStackEntry.arguments?.getInt("stringResourceIdSecond") ?: 0
                        val affirmationImageResourceId = backStackEntry.arguments?.getInt("imageResourceId") ?: 0

                        val affirmation = Affirmation(
                            affirmationStringResourceId,
                            affirmationStringResourceIdSecond,
                            affirmationImageResourceId
                        )

                        OtherActivityContent(
                            affirmation = affirmation,
                            onBackClick = { navController.popBackStack() },
                            navController = navController 
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun OtherActivityContent(
    affirmation: Affirmation,
    onBackClick: () -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "返回",
            color = Color.Blue,
            modifier = Modifier
                .clickable { onBackClick() }
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(affirmation.imageResourceId),
            contentDescription = stringResource(affirmation.stringResourceIdSecond),
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(affirmation.stringResourceIdSecond),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("mapPage")
        }) {
            Text(text = "地图")
        }
    }
}


@Composable
fun AffirmationsApp(navController: NavController) {
    val affirmations = DataSource().loadAffirmations()
    AffirmationList(
        affirmationList = affirmations,
        onAffirmationClick = { affirmation ->

            navController.navigate("otherActivity/${affirmation.stringResourceId}/${affirmation.stringResourceIdSecond}/${affirmation.imageResourceId}")
        },
        modifier = Modifier,

    )
}
@Composable
fun AffirmationList(
    affirmationList: List<Affirmation>,
    onAffirmationClick: (Affirmation) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onAffirmationClick(affirmation) }
            )
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HW2Theme {
        AffirmationCard(Affirmation(R.string.affirmation1, R.string.affirmation11,R.drawable.image1))
    }
}

data class Affirmation(
    @StringRes val stringResourceId: Int,
    @StringRes val stringResourceIdSecond: Int,
    @DrawableRes val imageResourceId: Int
)

class DataSource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf(
            Affirmation(R.string.affirmation1,R.string.affirmation11, R.drawable.image1),
            Affirmation(R.string.affirmation2,R.string.affirmation22, R.drawable.image2),
            Affirmation(R.string.affirmation3,R.string.affirmation33, R.drawable.image3),
            Affirmation(R.string.affirmation4,R.string.affirmation44, R.drawable.image4),
            Affirmation(R.string.affirmation5,R.string.affirmation55, R.drawable.image5),
            Affirmation(R.string.affirmation6,R.string.affirmation66, R.drawable.image6),
            Affirmation(R.string.affirmation7,R.string.affirmation77, R.drawable.image7),
            Affirmation(R.string.affirmation8,R.string.affirmation88, R.drawable.image8),
            Affirmation(R.string.affirmation9,R.string.affirmation99, R.drawable.image9),
            Affirmation(R.string.affirmation10,R.string.affirmation110, R.drawable.image10)
        )
    }
}





