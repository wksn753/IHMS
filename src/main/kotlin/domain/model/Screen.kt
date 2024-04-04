package domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
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

    BillingAndRecords(
        label = "Billing And Records",
        icon = Icons.Filled.Create
    ),

}
