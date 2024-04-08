package domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(
    val label: String,
    val icon: ImageVector
) {
    HomeScreen(
        label = "Home",
        icon = Icons.Filled.Home
    ),
    PatientScreen(
        label = "Patients",
        icon = Icons.Filled.Face
    ),

    InsuranceScreen(
        label="Insurance",
        icon = Icons.Default.Star
    )

}
